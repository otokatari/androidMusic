package otokatari.com.otokatari.Application;

import android.app.Application;
import android.content.Context;
import android.content.IntentFilter;
import otokatari.com.otokatari.Service.Common.AppService;
import otokatari.com.otokatari.Service.Common.NetworkStateReceiver;
import otokatari.com.otokatari.Utils.AppUtils;
import rx.subjects.BehaviorSubject;

public class otokatariAndroidApplication extends Application {

    // Define Application-wide context
    private static Context context;
    private static AppService appService;
    private static BehaviorSubject<Boolean> CurrentNetworkStatus;

    private NetworkStateReceiver networkStateReceiver;

    @Override
    public void onCreate()
    {
        super.onCreate();
        context = getApplicationContext();
        CurrentNetworkStatus = BehaviorSubject.create(false);

        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction("android.net.conn.CONNECTIVITY_CHANGE");
        networkStateReceiver = new NetworkStateReceiver();
        registerReceiver(networkStateReceiver, intentFilter);
    }
    public static Context getContext()
    {
        return context;
    }
    @Override
    public void onTerminate()
    {
        super.onTerminate();
        unregisterReceiver(networkStateReceiver);
    }
    public static AppService getAppService() {
        return appService;
    }

    public static BehaviorSubject<Boolean> GetCurrentNetworkStatusObservable()
    {
        return CurrentNetworkStatus;
    }
}
