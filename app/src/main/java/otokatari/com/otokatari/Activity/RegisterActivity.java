package otokatari.com.otokatari.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import otokatari.com.otokatari.Model.s.RequestInfo.LoginAccountInfo;
import otokatari.com.otokatari.R;
import otokatari.com.otokatari.Service.Common.ActivityCollector;
import otokatari.com.otokatari.Tasks.PostRegisterInfoTask;
import static otokatari.com.otokatari.Utils.AppUtils.isPhoneNum;

public class RegisterActivity extends BaseActivity {

    private EditText phoneAccount;
    private EditText password1;
    private EditText password2;
    private Button registerButton;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        ActivityCollector.addAvtivity(this);
        registerButton=(Button)findViewById(R.id.registerPageButton);

        registerButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                phoneAccount=(EditText)findViewById(R.id.editText1);
                password1=(EditText)findViewById(R.id.editText2) ;
                password2=(EditText)findViewById(R.id.editText3);
                String newAccount =phoneAccount.getText().toString();
                String password=password1.getText().toString();
                String passwordAgain=password2.getText().toString();

                if(!isPhoneNum(newAccount))
                {
                    Toast.makeText(RegisterActivity.this,"请输入正确格式的手机号",Toast.LENGTH_SHORT).show();
                }
                else if (!password.equals(passwordAgain)) {
                    Toast.makeText(RegisterActivity.this, "两次输入的密码不一致,请重新输入", Toast.LENGTH_SHORT).show();
                }
                else {
                    LoginAccountInfo loginAccountInfo = new LoginAccountInfo();
                    loginAccountInfo.setIdentifier(newAccount);
                    loginAccountInfo.setCredentials(password);
                    loginAccountInfo.setType(0);
                    new PostRegisterInfoTask(TaskRet -> {
                        if (TaskRet != null) {
                            if (TaskRet.getStatusCode() == 0) {
                                Toast.makeText(RegisterActivity.this, "注册成功,即将返回登录界面", Toast.LENGTH_SHORT).show();
                                Intent intent = new Intent(RegisterActivity.this, LoginActivity.class);
                                startActivity(intent);
                            }
                            if (TaskRet.getStatusCode() == -1) {
                                Toast.makeText(RegisterActivity.this, "该账号已被注册", Toast.LENGTH_SHORT).show();
                            }
                            if (TaskRet.getStatusCode() == -2) {
                                Toast.makeText(RegisterActivity.this, "服务器未知错误", Toast.LENGTH_SHORT).show();
                            }
                        } else
                            Log.d("RegisterActivity", "gg");
                    }).execute(loginAccountInfo);
                }
            }
        });
    }
}
