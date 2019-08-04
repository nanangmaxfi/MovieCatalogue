package id.nanangmaxfi.moviecatalogue.helper;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

public class SettingPreference {

    private static SharedPreferences getSharedPreference(Context context){
        return PreferenceManager.getDefaultSharedPreferences(context);
    }

    public static void setBool(Context context, String key, boolean value){
        SharedPreferences.Editor editor = getSharedPreference(context).edit();
        editor.putBoolean(key,value);
        editor.apply();
    }

    public static boolean getBool(Context context, String key){
        return getSharedPreference(context).getBoolean(key,false);
    }
}
