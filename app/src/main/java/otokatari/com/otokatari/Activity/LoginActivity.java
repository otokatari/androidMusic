package otokatari.com.otokatari.Activity;

import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.animation.ValueAnimator;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.preference.PreferenceManager;
import android.os.Bundle;
import android.support.constraint.ConstraintLayout;
import android.util.Log;
import android.view.View;
import android.widget.*;
import com.tencent.tauth.IUiListener;
import com.tencent.tauth.Tencent;
import com.tencent.tauth.UiError;
import org.json.JSONException;
import org.json.JSONObject;
import otokatari.com.otokatari.Application.otokatariAndroidApplication;
import otokatari.com.otokatari.Model.s.RequestInfo.LoginAccountInfo;
import otokatari.com.otokatari.Persistence.DateBaseHelper;
import otokatari.com.otokatari.R;
import otokatari.com.otokatari.Service.Common.ActivityCollector;
import otokatari.com.otokatari.Service.QQAuth.QQAuthCredentials;
import otokatari.com.otokatari.Tasks.PostLoginInfoTask;
import otokatari.com.otokatari.Utils.Animation;
import static java.lang.System.currentTimeMillis;

public class LoginActivity extends BaseActivity {

    private SharedPreferences pref;//利用SharedPreferences来保存账号密码数据
    private SharedPreferences.Editor editor1;
    private EditText accountEdit;
    private EditText passwordEdit;
    private CheckBox remember;
    private ImageView menuIcon;
    private ConstraintLayout loginLayout;
    private TextView startName;
    private Button startButton;

    //qq登录
    private Tencent mTencent;
    private QQLoginListener mListener;
    private String openID;
    public String access_token;
    private String expires;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        init();
        ActivityCollector.addAvtivity(this);
    }
    private void init()
    {
        remember=(CheckBox)findViewById(R.id.checkBox) ;
        Button login=(Button)findViewById(R.id.loginButton);
        Button register=(Button)findViewById(R.id.registerButton);
        accountEdit=(EditText)findViewById(R.id.editAccount) ;
        passwordEdit=(EditText)findViewById(R.id.editPassword) ;
        startButton = (Button)findViewById(R.id.login_start);
        startName = (TextView)findViewById(R.id.start_name);
        menuIcon = (ImageView)findViewById(R.id.menuIcon);
        loginLayout = (ConstraintLayout)findViewById(R.id.login_layout);
        final ImageView qqImage=findViewById(R.id.qqImage);

        mTencent = otokatariAndroidApplication.getQQAuthService();
        if (mListener == null) {
            mListener = new QQLoginListener();
        }

        pref= PreferenceManager.getDefaultSharedPreferences(this);
        Boolean isRemember=pref.getBoolean("remember_pass",false);//初始设置记住密码为false

        if(isRemember){
            String id=pref.getString("account","");
            String pass=pref.getString("password","");
            accountEdit.setText(id);
            passwordEdit.setText(pass);//把保存的账号和密码读取出来
            remember.setChecked(true);
        }
        startButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startAnimation();
            }
        });

        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {//点击注册跳到注册的页面
                Intent intent=new Intent(LoginActivity.this,RegisterActivity.class);
                startActivity(intent);
            }
        });

        login.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){
                accountEdit=(EditText)findViewById(R.id.editAccount);
                passwordEdit=(EditText)findViewById(R.id.editPassword);
                String userName=accountEdit.getText().toString();//获取文本框的数据
                String passWord=passwordEdit.getText().toString();
                LoginAccountInfo loginAccountInfo=new LoginAccountInfo();
                loginAccountInfo.setType(0);
                loginAccountInfo.setCredentials(passWord);
                loginAccountInfo.setIdentifier(userName);

                new PostLoginInfoTask(TaskRet -> {
                    if(TaskRet!=null) {
                       if(TaskRet.getStatusCode()==0)
                       {
                           Toast.makeText(LoginActivity.this, "登录成功", Toast.LENGTH_SHORT).show();
                           SharedPreferences.Editor editor=getSharedPreferences("LoginReturnData",MODE_PRIVATE).edit();
                           editor.putString("UserID",TaskRet.getUserID());
                           editor.putString("AccessToken"+TaskRet.getUserID(),TaskRet.getAccessToken());
                           editor.apply();
                           checkRemember(userName,passWord);
                           Intent intent=new Intent(LoginActivity.this, MainActivity.class);
                           startActivity(intent);
                       }
                       if(TaskRet.getStatusCode()==-1)
                       {
                           Toast.makeText(LoginActivity.this, "用户名或密码错误", Toast.LENGTH_SHORT).show();
                       }
                       if(TaskRet.getStatusCode()==-2)
                       {
                           Toast.makeText(LoginActivity.this,"服务器未知错误",Toast.LENGTH_SHORT).show();
                       }

                    }
                    else {
                        Toast.makeText(LoginActivity.this, "登陆失败", Toast.LENGTH_SHORT).show();//失败，弹出登陆失败
                    }
                }).execute(loginAccountInfo);
            }});

        //ClearInValidateUserAccountInfo();
        qqImage.setOnClickListener(new View.OnClickListener()
        {
            public void onClick(View v){
                if (!mTencent.isSessionValid()) {
                    mTencent.login(LoginActivity.this, "all", mListener);
                }
            }
        });
    }
    public void checkRemember(String userName,String passWord)
    {
        if(remember.isChecked()){//复选框是否被选中
            editor1=pref.edit();
            editor1.putBoolean("remember_pass",true);//把记住密码设置为true
            editor1.putString("account",userName);
            editor1.putString("password",passWord);//把密码和账号分别保存到account和password里面
        }
        else{
            if(editor1!=null)
            editor1.clear();//清空editor保存的东西
        }
        if(editor1!=null)
        editor1.apply();//启用editor
    }


//QQ

    private void ClearInValidateUserAccountInfo() {
        //既然跳到了这个页面，说明之前的登陆信息是无效的，需要清除。
        QQAuthCredentials.ClearStoredIdentity();
    }


    private class QQLoginListener implements IUiListener {
        //登陆结果回调
        @Override
        public void onComplete(Object o) { //登录成功
            parseResult(o);
            PersistUserInfo();
            PrepareToMainActivity();
        }

        @Override
        public void onError(UiError uiError) {
            //登录失败
            Log.d("LoginActivity", "QQ登陆失败，原因为" + uiError.errorCode + uiError.errorMessage);
            Toast.makeText(LoginActivity.this,"QQ登陆失败，原因为" + uiError.errorCode + uiError.errorMessage,Toast.LENGTH_SHORT).show();
        }

        @Override
        public void onCancel() {
            //取消登陆
            Log.d("LoginActivity", "用户取消了QQ登陆");
        }
    }

    private void parseResult(Object o) {
        //解析返回的Json串
        JSONObject jsonObject = (JSONObject) o;
        try {
            openID = jsonObject.getString("openid"); //用户标识
            access_token = jsonObject.getString("access_token"); //登录信息
            expires = jsonObject.getString("expires_in"); //token有效期
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    private void PersistUserInfo()//将返回的openid、access_token、expires_in三个参数保存在本地
    {
        String tokenInvalidDate = String.valueOf(currentTimeMillis() + Long.parseLong(expires) * 1000);//token的失效日期
        mTencent.setOpenId(openID);
        mTencent.setAccessToken(access_token, tokenInvalidDate);
        QQAuthCredentials.PersistIdentity(openID,access_token,tokenInvalidDate);
    }

    private void PrepareToMainActivity() {

        Intent intent=new Intent(LoginActivity.this,MainActivity.class);
        startActivity(intent);
        QQAuthCredentials.LoadUserAccountInfo();

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        Tencent.onActivityResultData(requestCode, resultCode, data, mListener);
    }

/****************************************************Animation********************************************************/

    private void startAnimation(){

        ValueAnimator translateUp = ValueAnimator.ofInt(Animation.dpToPx(100, otokatariAndroidApplication.getContext()),
                Animation.dpToPx(0, otokatariAndroidApplication.getContext()));
        //translateUp.setDuration(500);
        translateUp.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator valueAnimator) {
                int currValue = (int) valueAnimator.getAnimatedValue();
                ConstraintLayout.LayoutParams lp = (ConstraintLayout.LayoutParams) menuIcon.getLayoutParams();
                lp.topMargin = currValue;
                menuIcon.setLayoutParams(lp);
            }
        });

        startButton.setOnClickListener(null);
        loginLayout.setVisibility(View.VISIBLE);
        ValueAnimator fadeIn = ValueAnimator.ofFloat(0, 1);
        //fadeIn.setDuration(500);
        fadeIn.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator valueAnimator) {
                float currentValue = (float) valueAnimator.getAnimatedValue();
                loginLayout.setAlpha(currentValue);
            }
        });

        ValueAnimator fadeOut = ValueAnimator.ofFloat(1, 0);
        //fadeOut.setDuration(500);
        fadeOut.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator valueAnimator) {
                float currentValue = (float) valueAnimator.getAnimatedValue();
                startButton.setAlpha(currentValue);
                startName.setAlpha(currentValue);
            }

        });
        AnimatorSet set = new AnimatorSet();
        set.playTogether(
                ObjectAnimator.ofFloat(menuIcon, "scaleX", 1, 0.8f),
                ObjectAnimator.ofFloat(menuIcon, "scaleY", 1, 0.8f),
                translateUp,
                fadeIn,
                fadeOut
        );
        set.setDuration(500).start();
    }
}
