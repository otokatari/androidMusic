package otokatari.com.otokatari.Application;

import android.app.Application;
import android.content.Context;
import android.content.IntentFilter;
import android.util.Log;
import otokatari.com.otokatari.Service.Common.AppService;
import otokatari.com.otokatari.Service.Common.NetworkStateReceiver;
import otokatari.com.otokatari.Utils.AppUtils;
import otokatari.com.otokatari.Utils.RSAUtils;
import rx.subjects.BehaviorSubject;

import javax.crypto.BadPaddingException;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;

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

    public  String RSAUtilsEncrypt(String s)
    {
        String encrypted="";
        try
        {
            BufferedReader br = new BufferedReader(new InputStreamReader(getAssets().open("otokatari-public.txt")));
            StringBuilder sb = new StringBuilder();
            String line = null;
            while ((line = br.readLine()) != null) {
                sb.append(line);
            }
            br.close();

            RSAUtils rsa = new RSAUtils(sb.toString(),null);
            encrypted=rsa.Encrypt(s);

            Log.d("MainActivity",encrypted);
        } catch (IOException | NoSuchAlgorithmException | InvalidKeySpecException e)
        {
            e.printStackTrace();
        } catch (BadPaddingException e)
        {
            e.printStackTrace();
        } catch (IllegalBlockSizeException e)
        {
            e.printStackTrace();
        } catch (NoSuchPaddingException e)
        {
            e.printStackTrace();
        } catch (InvalidKeyException e)
        {
            e.printStackTrace();
        }
        return encrypted;
    }

}
