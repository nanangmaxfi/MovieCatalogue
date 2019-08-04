package id.nanangmaxfi.moviecatalogue.notification;

import android.app.AlarmManager;
import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.support.v4.app.NotificationCompat;
import android.support.v4.content.ContextCompat;
import android.util.Log;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Locale;

import id.nanangmaxfi.moviecatalogue.DetailActivity;
import id.nanangmaxfi.moviecatalogue.MainActivity;
import id.nanangmaxfi.moviecatalogue.R;
import id.nanangmaxfi.moviecatalogue.helper.ConfigUtils;
import id.nanangmaxfi.moviecatalogue.helper.ItemValue;
import id.nanangmaxfi.moviecatalogue.model.GetListMovie;
import id.nanangmaxfi.moviecatalogue.model.GetMovie;
import id.nanangmaxfi.moviecatalogue.rest.ApiClient;
import id.nanangmaxfi.moviecatalogue.rest.ApiInterface;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ReminderReceiver extends BroadcastReceiver {
    private final static String TAG = ReminderReceiver.class.getSimpleName();
    private ApiInterface apiInterface;
    private ConfigUtils configUtils = ConfigUtils.getInstance();

    public ReminderReceiver() {
        this.apiInterface = ApiClient.getClient().create(ApiInterface .class);
    }

    @Override
    public void onReceive(Context context, Intent intent) {
        String type = intent.getStringExtra(ItemValue.EXTRA_TYPE);;

        if (type.equals(ItemValue.DAILY_REMINDER)){
            String title = context.getResources().getString(R.string.app_name);
            String message = context.getResources().getString(R.string.message_daily_reminder);
            int notifId = ItemValue.ID_DAILY;
            showNotification(context, title, message, notifId, null);
        }
        else {
            String message = context.getResources().getString(R.string.message_release_reminder);
            int notifId = ItemValue.ID_RELEASE;
            getMovie(context, message, notifId);
        }


    }

    public void setAlarm(Context context, String type, String time, int idNotif){
        String TIME_FORMAT = "HH:mm";
        if (isDateInvalid(time, TIME_FORMAT))
            return;
        //Log.d(TAG,"set ALARM "+time);
        AlarmManager alarmManager = (AlarmManager) context.getSystemService(Context.ALARM_SERVICE);
        Intent intent = new Intent(context, ReminderReceiver.class);
        intent.putExtra(ItemValue.EXTRA_TYPE, type);

        String[] timeArray = time.split(":");

        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.HOUR_OF_DAY, Integer.parseInt(timeArray[0]));
        calendar.set(Calendar.MINUTE, Integer.parseInt(timeArray[1]));
        calendar.set(Calendar.SECOND, 0);

        PendingIntent pendingIntent = PendingIntent.getBroadcast(context, idNotif, intent, 0);
        //Log.d(TAG,alarmManager.toString());
        if (alarmManager != null){
            Log.d(TAG,"JAM "+calendar.getTime());
            Log.d(TAG,"HOUR "+calendar.getTimeInMillis());
            alarmManager.setInexactRepeating(AlarmManager.RTC_WAKEUP, calendar.getTimeInMillis(), AlarmManager.INTERVAL_DAY, pendingIntent);
        }
    }

    public boolean isDateInvalid(String date, String format) {
        try {
            DateFormat df = new SimpleDateFormat(format, Locale.getDefault());
            df.setLenient(false);
            df.parse(date);
            return false;
        } catch (ParseException e) {
            return true;
        }
    }

    private void showNotification(Context context, String title, String message, int notifId, String idMovie){
        String CHANNEL_ID = "Channel_1";
        String CHANNEL_NAME = "Reminder channel";

        PendingIntent pendingIntent = null;
        if (notifId == ItemValue.ID_DAILY){
            Intent intent = new Intent(context, MainActivity.class);
            pendingIntent = PendingIntent.getActivity(context,0,intent,0);
        }
        else if(notifId == ItemValue.ID_RELEASE){
            Intent intent = new Intent(context, DetailActivity.class);
            intent.putExtra(DetailActivity.EXTRA_MOVIE, idMovie);
            pendingIntent = PendingIntent.getActivity(context,0,intent,0);
        }

        NotificationManager notificationManager = (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);
        NotificationCompat.Builder builder = new NotificationCompat.Builder(context, CHANNEL_ID)
                .setSmallIcon(R.drawable.ic_movie)
                .setContentTitle(title)
                .setContentText(message)
                .setContentIntent(pendingIntent)
                .setColor(ContextCompat.getColor(context, android.R.color.transparent))
                .setVibrate(new long[]{1000,1000,1000,1000,1000})
                .setGroup(ItemValue.GROUP_KEY_REMINDER)
                .setAutoCancel(true);

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O){
            NotificationChannel channel = new NotificationChannel(CHANNEL_ID, CHANNEL_NAME, NotificationManager.IMPORTANCE_DEFAULT);
            channel.enableVibration(true);
            channel.setVibrationPattern(new long[]{1000, 1000, 1000, 1000, 1000});
            builder.setChannelId(CHANNEL_ID);
            if (notificationManager != null){
                notificationManager.createNotificationChannel(channel);
            }
        }

        Notification notification = builder.build();
        if (notificationManager != null){
            notificationManager.notify(notifId, notification);
        }

    }

    public void cancelAlarm(Context context, String type){
        AlarmManager alarmManager = (AlarmManager)context.getSystemService(Context.ALARM_SERVICE);
        Intent intent = new Intent(context, ReminderReceiver.class);

        int reqCode = type.equalsIgnoreCase(ItemValue.DAILY_REMINDER) ? ItemValue.ID_DAILY : ItemValue.ID_RELEASE;
        PendingIntent pendingIntent = PendingIntent.getBroadcast(context, reqCode, intent, 0);
        pendingIntent.cancel();

        if (alarmManager != null){
            alarmManager.cancel(pendingIntent);
        }
    }

    private void getMovie(final Context context, final String message, final int notifId){
        final String releaseDate = configUtils.getDate();

        Call<GetListMovie> movieCall = apiInterface.getListReleaseMovie(releaseDate);
        movieCall.enqueue(new Callback<GetListMovie>() {
            @Override
            public void onResponse(Call<GetListMovie> call, Response<GetListMovie> response) {
                Log.d(TAG, "Success load list release movie");
                ArrayList<GetMovie> movies = response.body().getListMovie();
                for (GetMovie movie:movies) {
                    if (releaseDate.equals(movie.getReleaseDate())){
                        String title = movie.getTitle();
                        String id = movie.getId();
                        showNotification(context, title, title+" "+message, notifId, id);
                        break;
                    }
                }
            }

            @Override
            public void onFailure(Call<GetListMovie> call, Throwable t) {
                Log.e(TAG, t.getMessage());
            }
        });
    }
}
