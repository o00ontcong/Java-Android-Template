package congnguyen.java_android_template.common;

import android.app.Application;
import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

import io.reactivex.Scheduler;
import io.reactivex.schedulers.Schedulers;

public class GlobalApp  extends Application {
    protected static volatile GlobalApp mInstance = null;
    private Scheduler mScheduler;

    @Override public void onCreate() {
        super.onCreate();
        setInstance(this);
    }

    public Scheduler subscribeScheduler() {
        if (mScheduler == null) {
            mScheduler = Schedulers.io();
        }

        return mScheduler;
    }

    public boolean isNetworkConnected() {
        ConnectivityManager connectivityManager =
                (ConnectivityManager) getApplicationContext().getSystemService( Context.CONNECTIVITY_SERVICE);

        NetworkInfo networkInfo = null;
        if (connectivityManager != null) {
            networkInfo = connectivityManager.getActiveNetworkInfo();
        }

        return (networkInfo != null && networkInfo.isConnected());
    }

    public static GlobalApp getInstance() {
        if(mInstance != null) {
            return mInstance;
        }
        return null;
    }

    public static void setInstance(GlobalApp mInstance) {
        GlobalApp.mInstance = mInstance;
    }
}