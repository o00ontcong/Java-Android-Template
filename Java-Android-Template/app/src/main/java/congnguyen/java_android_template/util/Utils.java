package congnguyen.java_android_template.util;

import android.content.Context;
import android.content.SharedPreferences;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

import congnguyen.java_android_template.common.Constants;
import congnguyen.java_android_template.common.GlobalApp;
import congnguyen.java_android_template.view.CoolToast;

public class Utils {
    public static void savePreference(String key, String value) {
        SharedPreferences.Editor editor = GlobalApp.getInstance().getSharedPreferences(
                Constants.SHARED_PREFERENCES_FILE_NAME, GlobalApp.MODE_PRIVATE).edit();
        editor.putString(key, value);
        editor.apply();
    }


    public static String getStringValue(String key) {
        SharedPreferences settings = GlobalApp.getInstance().getSharedPreferences(
                Constants.SHARED_PREFERENCES_FILE_NAME, GlobalApp.MODE_PRIVATE);
        return settings.getString(key, Constants.BLANK);
    }

    public static String getToken() {
        SharedPreferences settings = GlobalApp.getInstance().getSharedPreferences(
                Constants.SHARED_PREFERENCES_FILE_NAME, GlobalApp.MODE_PRIVATE);
        return settings.getString(Constants.PREF_CURRENT_TOKEN, Constants.BLANK);
    }
    public static void showCoolToast(Context context, String string, int style, int duration) {
        CoolToast coolToast = new CoolToast(context);
        coolToast.make(string, style, duration);
    }
}
