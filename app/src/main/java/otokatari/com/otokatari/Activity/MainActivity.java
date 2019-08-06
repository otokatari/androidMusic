package otokatari.com.otokatari.Activity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import otokatari.com.otokatari.Model.s.RequestInfo.LoginAccountInfo;
import otokatari.com.otokatari.R;
import otokatari.com.otokatari.Service.UserService.UserService;
import otokatari.com.otokatari.Tasks.*;


public class MainActivity extends BaseActivity
{

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //init();
        test();
    }
    private void init(){
        Button search = findViewById(R.id.search);
        search.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //temporary
                Intent intent=new Intent(MainActivity.this, TempSearchAcitvity.class);
                startActivity(intent);
            }
        });
    }

    public void test()
    {
        LoginAccountInfo loginAccountInfo=new LoginAccountInfo();
        loginAccountInfo.setIdentifier("15521331111");
        loginAccountInfo.setCredentials("1234");
        loginAccountInfo.setType(0);

//        SimpleMusic simpleMusic=new SimpleMusic();
//        simpleMusic.setAlbumid("1");
//        simpleMusic.setAlbumname("12");
//        simpleMusic.setMusicid("123");
//        simpleMusic.setName("zoom");
//        simpleMusic.setPlatform("kugou");
//        simpleMusic.setSingerid("1");
//        simpleMusic.setSingername("zj");
//        UploadPlayBehavior uploadPlayBehavior=new UploadPlayBehavior();
//        uploadPlayBehavior.setUserid("1");
//        uploadPlayBehavior.setTime(2123);
//        uploadPlayBehavior.setMusic(simpleMusic);
//        uploadPlayBehavior.setIsinplaylist(false);
//        uploadPlayBehavior.setBehaviour("like");

        new PostLoginInfoTask (TaskRet -> {
        if(TaskRet!=null) {
            Log.d("MainActivity", String.valueOf(TaskRet.getStatusCode()));
            SharedPreferences.Editor editor=getSharedPreferences("LoginReturnData",MODE_PRIVATE).edit();
            editor.putString("UserID",TaskRet.getUserID());
            editor.putString("AccessToken"+TaskRet.getUserID(),TaskRet.getAccessToken());
        }
        else
            Log.d("MainActivity","gg");
    }).execute(loginAccountInfo);


        new LogoutTask (TaskRet -> {
            if(TaskRet!=null) {
                Log.d("MainActivity", String.valueOf(TaskRet.getStatusCode()));
            }
            else
                Log.d("MainActivity","gg");
        }).execute();

        new GetUserInfoDetailsTask (TaskRet -> {
            if(TaskRet!=null) {
                Log.d("MainActivity", String.valueOf(TaskRet.getStatusCode()));
            }
            else
                Log.d("MainActivity","gg");
        }).execute(UserService.GetUserID());

   }
}
