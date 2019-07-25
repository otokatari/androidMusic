package otokatari.com.otokatari.Activity;

import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import otokatari.com.otokatari.Persistence.DateBaseHelper;
import otokatari.com.otokatari.R;

public class RegisterActivity extends BaseActivity {

    private  DateBaseHelper dbhelper;
    private EditText phoneAccount;
    private EditText password1;
    private EditText password2;
    private Button registerButton;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        dbhelper=new DateBaseHelper(this,"Users.db",null,1);
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
                if (CheckIsDataAlreadyInDBorNot(newAccount)) {
                    Toast.makeText(RegisterActivity.this,"该用户名已被注册，注册失败",Toast.LENGTH_SHORT).show();
                }
                else if (!password.equals(passwordAgain))
                    Toast.makeText(RegisterActivity.this,"两次输入的密码不一致",Toast.LENGTH_SHORT).show();
                else {
                    if (register(newAccount, password)) {
                        Toast.makeText(RegisterActivity.this, "恭喜您 注册成功，即将返回登录界面", Toast.LENGTH_SHORT).show();
                        Intent intent=new Intent(RegisterActivity.this,LoginActivity.class);
                        startActivity(intent);
                    }}
            }
        });
    }
    //向数据库插入数据
    public boolean register(String username,String password){
        SQLiteDatabase db= dbhelper.getWritableDatabase();
        ContentValues values=new ContentValues();
        values.put("id",username);
        values.put("password",password);
        db.insert("userData",null,values);
        db.close();
        //db.execSQL("insert into userData (name,password) values (?,?)",new String[]{username,password});
        return true;
    }
    //检验用户名是否已存在
    public boolean CheckIsDataAlreadyInDBorNot(String value){
        SQLiteDatabase db=dbhelper.getWritableDatabase();
        String Query = "Select * from userData where id =?";
        Cursor cursor = db.rawQuery(Query,new String[] { value });
        if (cursor.getCount()>0){
            cursor.close();
            return  true;
        }
        cursor.close();
        return false;
    }

}
