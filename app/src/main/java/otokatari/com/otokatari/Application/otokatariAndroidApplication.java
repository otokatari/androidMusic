package otokatari.com.otokatari.Application;

import android.app.Application;
import android.content.Context;
import android.content.IntentFilter;
import android.util.Log;
import com.tencent.tauth.Tencent;
import otokatari.com.otokatari.Service.Common.AppService;
import otokatari.com.otokatari.Service.Common.NetworkStateReceiver;
import otokatari.com.otokatari.Service.QQAuth.QQAuthCredentials;
import otokatari.com.otokatari.Service.UserService.UserService;
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
    //Define global services
    // 3rd-party services.
    private static Tencent QQAuthService;
    private static String SB;
    //our app services.
    private static UserService userService;
    private static AppService appService;
    //Define global variables

    private static BehaviorSubject<Boolean> CurrentNetworkStatus;

    private NetworkStateReceiver networkStateReceiver;
    @Override
    public void onCreate()
    {
        super.onCreate();
        context = getApplicationContext();
        CurrentNetworkStatus = BehaviorSubject.create(AppUtils.IfAppIsRunning(context));
        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction("android.net.conn.CONNECTIVITY_CHANGE");
        networkStateReceiver = new NetworkStateReceiver();
        registerReceiver(networkStateReceiver,intentFilter);
        getPublicKey();
        QQAuthService = Tencent.createInstance(QQAuthCredentials.APPID,context);

        //加载此App自己的服务.
        userService = new UserService();
        appService = new AppService();

    }

    @Override
    public void onTerminate()
    {
        super.onTerminate();
        unregisterReceiver(networkStateReceiver);
    }

    public static Context getContext()
    {
        return context;
    }

    public static Tencent getQQAuthService()
    {
        return QQAuthService;
    }

    public static UserService getUserService() {
        return userService;
    }

    public static AppService getAppService() {
        return appService;
    }

    public static void ReloadBeforeLogin() {

    }

    public static void ReloadAfterLogin()
    {
        //这里放置一些在完成全部Login操作之后需要执行的语句.
        //((CustomCredentialProvider)credentialProvider).LoadAccessTokenAndUserID();
        //UserService.LoadUserProfile();
    }

    public static BehaviorSubject<Boolean> GetCurrentNetworkStatusObservable()
    {
        return CurrentNetworkStatus;
    }

    public  void getPublicKey()
    {
        try
        {
            BufferedReader br = new BufferedReader(new InputStreamReader(getAssets().open("otokatari-public.txt")));
            StringBuilder sb = new StringBuilder();
            String line = null;
            while ((line = br.readLine()) != null) {
                sb.append(line);
            }
            SB=sb.toString();
            br.close();
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
    }

    public static String RSAUtilsEncrypt(String s)
    {
        String encrypted="";
        try
        {
            RSAUtils rsa = new RSAUtils(SB,null);
            encrypted=rsa.Encrypt(s);
            Log.d("MainActivity",encrypted);
        } catch ( NoSuchAlgorithmException | InvalidKeySpecException e)
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
