package id.nanangmaxfi.moviecatalogue;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.MenuItem;
import android.widget.CompoundButton;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import id.nanangmaxfi.moviecatalogue.helper.ItemValue;
import id.nanangmaxfi.moviecatalogue.helper.SettingPreference;
import id.nanangmaxfi.moviecatalogue.notification.ReminderReceiver;

public class SettingActivity extends AppCompatActivity {
    private final static String TAG = SettingActivity.class.getSimpleName();
    private Switch swRelease, swDaily;
    private ReminderReceiver reminderReceiver;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_setting);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setElevation(0);
            getSupportActionBar().setDisplayShowTitleEnabled(false);
        }

        TextView tlbTitle = findViewById(R.id.toolbar_title);
        tlbTitle.setText(getString(R.string.setting));

        swRelease = findViewById(R.id.sw_release);
        swDaily = findViewById(R.id.sw_daily);
        reminderReceiver = new ReminderReceiver();

        swDaily.setChecked(SettingPreference.getBool(getApplicationContext(),ItemValue.DAILY_REMINDER));
        swRelease.setChecked(SettingPreference.getBool(getApplicationContext(),ItemValue.RELEASE_REMINDER));

        swDaily.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked){
                    SettingPreference.setBool(getApplicationContext(),ItemValue.DAILY_REMINDER,true);
                    Log.d(TAG, "Daily is checked");
                    reminderReceiver.setAlarm(getApplicationContext(), ItemValue.DAILY_REMINDER, ItemValue.TIME_DAILY_REMINDER, ItemValue.ID_DAILY);
                    showToast(getString(R.string.daily_reminder)+" "+getString(R.string.active));
                }
                else {
                    SettingPreference.setBool(getApplicationContext(),ItemValue.DAILY_REMINDER,false);
                    Log.d(TAG, "Daily is unchecked");
                    reminderReceiver.cancelAlarm(getApplicationContext(), ItemValue.DAILY_REMINDER);
                    showToast(getString(R.string.daily_reminder)+" "+getString(R.string.deactive));
                }
            }
        });


        swRelease.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked){
                    SettingPreference.setBool(getApplicationContext(),ItemValue.RELEASE_REMINDER,true);
                    Log.d(TAG, "Release is checked");
                    reminderReceiver.setAlarm(getApplicationContext(), ItemValue.RELEASE_REMINDER, ItemValue.TIME_RELEASE_REMINDER, ItemValue.ID_RELEASE);
                    showToast(getString(R.string.release_reminder)+" "+getString(R.string.active));
                }
                else {
                    SettingPreference.setBool(getApplicationContext(),ItemValue.RELEASE_REMINDER,false);
                    Log.d(TAG, "Release is unchecked");
                    reminderReceiver.cancelAlarm(getApplicationContext(), ItemValue.RELEASE_REMINDER);
                    showToast(getString(R.string.release_reminder)+" "+getString(R.string.deactive));
                }
            }
        });
    }


    private void showToast(String message){
        Toast.makeText(getApplicationContext(), message,Toast.LENGTH_SHORT).show();
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home){
            finish();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
