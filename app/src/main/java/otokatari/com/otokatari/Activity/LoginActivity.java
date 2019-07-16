package otokatari.com.otokatari.Activity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.preference.PreferenceManager;
import android.os.Bundle;
import android.view.View;
import android.widget.*;
import otokatari.com.otokatari.Persistence.DateBaseHelper;
import otokatari.com.otokatari.R;

public class LoginActivity extends BaseActivity {

    private SharedPreferences pref;//利用sSharedPreferences来保存数据
    private SharedPreferences.Editor editor;//用来保存密码
    private DateBaseHelper dbhelp;
    private EditText accountEdit;
    private EditText passwordEdit;
    private Button login;
    private Button  register;
    private CheckBox remember;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        dbhelp=new DateBaseHelper(this,"Users.db",null,1);//数据库的初始化
        remember=(CheckBox)findViewById(R.id.checkBox) ;
        login=(Button)findViewById(R.id.loginButton);
        register=(Button)findViewById(R.id.registerButton);
        accountEdit=(EditText)findViewById(R.id.editAccount) ;
        passwordEdit=(EditText)findViewById(R.id.editPassword) ;

        pref= PreferenceManager.getDefaultSharedPreferences(this);
        Boolean isremember=pref.getBoolean("remember_pass",false);//初始设置记住密码为false
        DateBaseHelper database=new DateBaseHelper(this,"Users.db",null,1);
        if(isremember){
            int username=pref.getInt("username",0)-1;
            String id=pref.getString("account"+username,"");
            String pass=pref.getString("password"+username,"");
            accountEdit.setText(id);
            passwordEdit.setText(pass);//把保存的账号和密码读取出来
            remember.setChecked(true);
        }
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
                if (login(userName,passWord)) {
                    Toast.makeText(LoginActivity.this, "登陆成功", Toast.LENGTH_SHORT).show();
                    editor=pref.edit();
                    if(remember.isChecked()){//复选框是否被选中
                        int username=pref.getInt("username",0);
                        editor.putBoolean("remember_pass",true);//把记住密码设置为true
                        editor.putString("account"+username,userName);
                        editor.putString("password"+username,passWord);//把密码和账号分别保存到account和password里面
                        editor.putInt("username",username+1);
                    }
                    else{
                        editor.clear();//清空editor保存的东西
                    }
                    editor.apply();//启用editor
                }
                else {
                    Toast.makeText(LoginActivity.this, "登陆失败", Toast.LENGTH_SHORT).show();//失败，弹出登陆失败
                }
            }});

    }

    public boolean login(String username,String password) {//验证此账号密码是否正确
        SQLiteDatabase db = dbhelp.getWritableDatabase();
        String sql = "select * from userData where id=? and password=?";//将登录时填的账号和密码在数据库里面进行查询，如果存在该数据，则返回true，否则返回false
        Cursor cursor = db.rawQuery(sql, new String[] {username, password});
        if (cursor.moveToFirst()) {
            cursor.close();
            return true;
        }
        return false;
    }
}
