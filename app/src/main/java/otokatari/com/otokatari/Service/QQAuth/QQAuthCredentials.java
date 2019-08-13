package otokatari.com.otokatari.Service.QQAuth;

import android.content.Context;
import android.content.SharedPreferences;
import android.text.TextUtils;
import android.util.Log;
import android.widget.Toast;
import com.tencent.tauth.IUiListener;
import com.tencent.tauth.Tencent;
import com.tencent.tauth.UiError;
import org.json.JSONObject;
import otokatari.com.otokatari.Application.otokatariAndroidApplication;
import otokatari.com.otokatari.Model.s.RequestInfo.LoginAccountInfo;
import otokatari.com.otokatari.Service.UserService.UserAccount;
import otokatari.com.otokatari.Service.UserService.UserService;
import otokatari.com.otokatari.Tasks.PostQQLoginInfoTask;
import otokatari.com.otokatari.Utils.AppUtils;
import otokatari.com.otokatari.Utils.HMACSHA256Utils;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import static android.content.Context.MODE_PRIVATE;

public class QQAuthCredentials
{
    public static final String APPID = "101726834";
    private static Context ctx = otokatariAndroidApplication.getContext();
    private static SharedPreferences preferences = ctx.getSharedPreferences("LoginReturnData", MODE_PRIVATE);

    public static boolean Validate()
    {
        boolean HaveUserIdentity = preferences.getBoolean("HaveStoredUserIdentity", false);
        if (HaveUserIdentity)
        {
            String openID = preferences.getString("openID", "");
            String access_token = preferences.getString("access_token", "");
            String expires = preferences.getString("expires", "");
            Tencent mTencent = otokatariAndroidApplication.getQQAuthService();
            mTencent.setOpenId(openID);
            mTencent.setAccessToken(access_token,expires);
            return mTencent.isSessionValid();
        }
        else {
            return false;
        }
    }

    public static String GetAccessToken()
    {
        //Context ctx = otokatariAndroidApplication.getContext();
        //SharedPreferences sp = ctx.getSharedPreferences("LoginReturnData", MODE_PRIVATE);
        boolean IsHaveStoredUserIdentity = preferences.getBoolean("HaveStoredUserIdentity",false);

        if(IsHaveStoredUserIdentity){
            return preferences.getString("access_token", null);
        }
        return null;
    }

    public static String GetEncryptedAccessToken() throws InvalidKeyException, NoSuchAlgorithmException {
        String ACTK = GetAccessToken();
        if(!TextUtils.isEmpty(ACTK)){
            return HMACSHA256Utils.sha256_HMAC(ACTK);
        }
        throw new IllegalArgumentException("先前持久化的AccessToken不正确，为空!");
    }

    public static void PushLoginInfoToBackend(String OpenID,String credentials) {

        LoginAccountInfo loginAccountInfo=new LoginAccountInfo();
        loginAccountInfo.setIdentifier(OpenID);
        loginAccountInfo.setCredentials(credentials);
        loginAccountInfo.setType(1);
        new PostQQLoginInfoTask((res) ->
        {
            if(AppUtils.CommonResponseOK(res)) {
                if (res.getStatusCode() != 0) {
                    Log.d("QQAuthCredentials", "上报数据给服务器出现异常!");
                }
                //将服务器返回的UserID存入UserService对象。
                //otokatariAndroidApplication.getUserService().SetUserID(res.getUserID());
                else {
                    Log.d("QQAuthCredentials", "成功上报数据给服务器.");
                    otokatariAndroidApplication.ReloadAfterLogin();
                }
            }
        }).execute(loginAccountInfo);
    }

    public static void ClearStoredIdentity() {
        SharedPreferences.Editor editSp = preferences.edit();
        editSp.putBoolean("HaveStoredUserIdentity",false);
        editSp.apply();
       // SplashActivity.GotoMainActivityEvent.reset("qq_login");
    }

    public static void PersistIdentity(String OpenID,String AccessToken,String TokenInvaildDate) {
        SharedPreferences.Editor editor = ctx.getSharedPreferences("LoginReturnData", MODE_PRIVATE).edit();
        editor.putBoolean("HaveStoredUserIdentity", true);
        editor.putString("openID", OpenID);
        editor.putString("access_token", AccessToken);
        editor.putString("expires", TokenInvaildDate);
        editor.apply();
        QQAuthCredentials.PushLoginInfoToBackend(OpenID,AccessToken);
    }

    public static void LoadUserAccountInfo() {
        UserService.LoadUserQQProfile().getUserInfo(CommonUserInfoListener);
    }

    public static IUiListener CommonUserInfoListener = new IUiListener() {
        @Override
        public void onComplete(Object o) {
            JSONObject jsonObject = (JSONObject) o;
            try {
                String name = jsonObject.getString("nickname");
                String imgUrl = jsonObject.getString("figureurl_qq_2");  //头像url
                String openID = otokatariAndroidApplication.getQQAuthService().getOpenId();
                otokatariAndroidApplication
                        .getUserService()
                        .setUserAccount(new UserAccount(name,imgUrl));

            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        @Override
        public void onError(UiError uiError) {
            Log.d("QQAuthCredentials", "获取个人信息出现异常!" + uiError.errorMessage);
            Toast.makeText(otokatariAndroidApplication.getContext(), "获取个人信息出现异常!" + uiError.errorMessage, Toast.LENGTH_SHORT).show();
        }

        @Override
        public void onCancel() {

        }
    };
}
