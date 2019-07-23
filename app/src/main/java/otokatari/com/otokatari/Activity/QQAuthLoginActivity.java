//package otokatari.com.otokatari.Activity;
//
//import android.content.Intent;
//import android.os.Bundle;
//import android.util.Log;
//import android.view.View;
//import android.widget.*;
//import com.tencent.tauth.IUiListener;
//import com.tencent.tauth.Tencent;
//import com.tencent.tauth.UiError;
//import org.json.JSONException;
//import org.json.JSONObject;
//import otokatari.com.otokatari.Application.otokatariAndroidApplication;
//import otokatari.com.otokatari.R;
//import otokatari.com.otokatari.Service.QQAuth.QQAuthCredentials;
//import static java.lang.System.currentTimeMillis;
//
//public class QQAuthLoginActivity extends BaseActivity{
//
//    private Tencent mTencent;
//    private QQLoginListener mListener;
//    private String openID;
//    public String access_token;
//    private String expires;
//
//
//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_qqauth_login);
//        init();
//        ClearInValidateUserAccountInfo();
//    }
//
//    private void ClearInValidateUserAccountInfo() {
//        //既然跳到了这个页面，说明之前的登陆信息是无效的，需要清除。
//        QQAuthCredentials.ClearStoredIdentity();
//
//    }
//
//    private void init() {
//
//        mTencent = otokatariAndroidApplication.getQQAuthService();
//        if (mListener == null) {
//            mListener = new QQLoginListener();
//        }
//
//    }
//
//    private void QQLogin() {
//        if (!mTencent.isSessionValid()) {
//
//            mTencent.login(this, "all", mListener);
//        }
//    }
//
//    private class QQLoginListener implements IUiListener {
//        //登陆结果回调
//        @Override
//        public void onComplete(Object o) { //登录成功
//            parseResult(o);
//            PersistUserInfo();
//            PrepareToMainActivity();
//            IsLogining.setVisibility(View.GONE);
//        }
//
//        @Override
//        public void onError(UiError uiError) {
//            //登录失败
//            IsLogining.setVisibility(View.GONE);
//            Log.d("QQAuthLoginActivity", "QQ登陆失败，原因为" + uiError.errorCode + uiError.errorMessage);
//            Toast.makeText(QQAuthLoginActivity.this,"QQ登陆失败，原因为" + uiError.errorCode + uiError.errorMessage,Toast.LENGTH_SHORT).show();
//        }
//
//        @Override
//        public void onCancel() {
//            //取消登陆
//            IsLogining.setVisibility(View.GONE);
//            Log.d("QQAuthLoginActivity", "用户取消了QQ登陆");
//        }
//    }
//
//    private void parseResult(Object o) {
//        //解析返回的Json串
//        JSONObject jsonObject = (JSONObject) o;
//        try {
//            openID = jsonObject.getString("openid"); //用户标识
//            access_token = jsonObject.getString("access_token"); //登录信息
//            expires = jsonObject.getString("expires_in"); //token有效期
//        } catch (JSONException e) {
//            e.printStackTrace();
//        }
//    }
//
//    private void PersistUserInfo()//将返回的openid、access_token、expires_in三个参数保存在本地
//    {
//        String tokenInvalidDate = String.valueOf(currentTimeMillis() + Long.parseLong(expires) * 1000);//token的失效日期
//        mTencent.setOpenId(openID);
//        mTencent.setAccessToken(access_token, tokenInvalidDate);
//        QQAuthCredentials.PersistIdentity(openID,access_token,tokenInvalidDate);
//    }
//
//    private void PrepareToMainActivity() {
//        SplashActivity.GoToMainActivityHandler.sendEmptyMessageDelayed(SplashActivity.OVERTIME_GOTO_MAINACTIVITY,10000);
//        QQAuthCredentials.LoadUserAccountInfo();
//    }
//
//    @Override
//    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
//        IsLogining.setVisibility(View.VISIBLE);
//        Tencent.onActivityResultData(requestCode, resultCode, data, mListener);
//    }
//}
