package otokatari.com.otokatari.Service.UserService;

import android.content.Context;
import android.content.SharedPreferences;
import android.util.Log;
import com.tencent.connect.UserInfo;
import com.tencent.connect.auth.QQToken;
import com.tencent.tauth.Tencent;
import otokatari.com.otokatari.Application.otokatariAndroidApplication;

import static android.content.Context.MODE_PRIVATE;

public class UserService {
    private static Context ctx;
    private UserAccount userAccount; //qq用户
    //private UserInformation MyProfile = new UserInformation();
    //private MyResponse Credentials;
    private static SharedPreferences sp;

    public UserService() {
        ctx = otokatariAndroidApplication.getContext();
       sp = ctx.getSharedPreferences("LoginReturnData",MODE_PRIVATE);
    }

//    public MyResponse getCredentials() {
//        return Credentials;
//    }

//    public UserInformation getMyProfile() {
//        return MyProfile;
//    }
//
//    public void setCredentials(MyResponse credentials) {
//        Credentials = credentials;
//    }
//
//    public void setMyProfile(UserInformation myProfile) {
//        MyProfile = myProfile;
//    }


    public UserAccount getUserAccount() {
        return userAccount;
    }

    public void setUserAccount(UserAccount userAccount) {
        this.userAccount = userAccount;
    }

//    public void SetUserID(long UserID) {
//        MyProfile.setId(UserID);
//    }
//
//    public long GetUserID() {
//        return MyProfile.getId();
//    }

    public static UserInfo LoadUserQQProfile() {
        Tencent tencent = otokatariAndroidApplication.getQQAuthService();
        QQToken qqToken = tencent.getQQToken();
        return new UserInfo(ctx, qqToken);
    }

//    public static void LoadUserProfile() {
//        new GetUserInformationTask((result) -> {
//            if (result.getStatusCode() == 0) {
//                UserService us = otokatariAndroidApplication.getUserService();
//                us.setMyProfile(result.getUserInfo());
//
//            }
//            else Log.e("UserService", "不能获取到自身账户的全部信息 : " + result.getStatusCode());
//        }).execute();
//    }

    public static String GetAlias() {
        return sp.getString("Alias",null);
    }
    public static void RemoveAlias() {
        SharedPreferences.Editor EditSp = sp.edit();
        EditSp.putBoolean("HaveSetAlias",false);
        EditSp.apply();
    }

    public static void PersistAlias(String alias) {
        SharedPreferences.Editor EditSp = sp.edit();
        EditSp.putBoolean("HaveSetAlias",true);
        EditSp.putString("Alias",alias);
        EditSp.apply();
    }
    public static String GetUserID()
    {
        return sp.getString("UserID","");
    }
    public static String GetAccessToken()
    {
        return sp.getString("AccessToken"+GetUserID(),"");
    }
}

