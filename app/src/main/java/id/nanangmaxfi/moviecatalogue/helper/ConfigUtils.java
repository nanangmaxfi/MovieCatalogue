package id.nanangmaxfi.moviecatalogue.helper;

import android.util.Log;

import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

public class ConfigUtils {
    private static ConfigUtils instance;

    public static ConfigUtils getInstance(){
        if (instance == null)
            instance = new ConfigUtils();
        return instance;
    }

    //format currency
    public String formatCurrency(String amount){
        NumberFormat formatter = new DecimalFormat("#,###");
        return formatter.format(Double.parseDouble(amount));
    }

    //format date
    public String formatDate(String date){
        try {
            SimpleDateFormat spf = new SimpleDateFormat("yyyy-MM-dd", Locale.US);
            Date newDate = spf.parse(date);
            spf = new SimpleDateFormat("dd MMM yyyy", Locale.US);
            date = spf.format(newDate);
        }
        catch (Exception e){
            Log.e("Date", e.getMessage());
        }
        return date;
    }

    //get currently time
    public String getDate(){
        Date date = Calendar.getInstance().getTime();
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd", Locale.US);
        return dateFormat.format(date);
    }
}
