package otokatari.com.otokatari.Activity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;
import com.bumptech.glide.Glide;
import de.hdodenhof.circleimageview.CircleImageView;
import otokatari.com.otokatari.Application.otokatariAndroidApplication;
import otokatari.com.otokatari.Model.s.RequestInfo.*;
import otokatari.com.otokatari.R;
import otokatari.com.otokatari.Service.Common.ActivityCollector;
import otokatari.com.otokatari.Service.UserService.UserAccount;
import otokatari.com.otokatari.Service.UserService.UserService;
import otokatari.com.otokatari.Tasks.*;

import static otokatari.com.otokatari.Service.Common.ActivityCollector.finishAll;
import static otokatari.com.otokatari.Service.UserService.UserService.GetAccessToken;


public class MainActivity extends BaseActivity {


    private long exitTime = 0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        init();
        ActivityCollector.addAvtivity(this);
        // test();
    }

    private void init() {
        Button search = findViewById(R.id.search);
        CircleImageView ToolbarUserAvatar = findViewById(R.id.ToolbarUserAvatar);
        //CircleImageView localMusic=findViewById(R.id.localMusic);
        UserAccount ua = otokatariAndroidApplication.getUserService().getUserAccount();
        if (ua != null) {
            //String NickName = ua.getNickname();
            String AvatarUrl = ua.getImageUrl();
            //NickNameTextView.setText(NickName);
            Glide.with(MainActivity.this).load(AvatarUrl).into(ToolbarUserAvatar);
            //Glide.with(MainActivity.this).load(AvatarUrl).into(NavigationHeaderBigAvatar);
        }

        search.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //temporary
                Intent intent = new Intent(MainActivity.this, SearchMusicActivity.class);
                startActivity(intent);
            }
        });

//        localMusic.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Intent intent = new Intent(MainActivity.this, playUIActivity.class);
//                startActivity(intent);
//            }
//        });
    }
    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK
                && event.getAction() == KeyEvent.ACTION_DOWN) {
            if ((System.currentTimeMillis() - exitTime) > 2000) {
                Toast.makeText(getApplicationContext(), "再按一次退出程序",
                        Toast.LENGTH_SHORT).show();
                exitTime = System.currentTimeMillis();
            } else {
                finishAll();
                System.exit(0);
            }
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }


//    public void test()
//    {
//        LoginAccountInfo loginAccountInfo=new LoginAccountInfo();
//        loginAccountInfo.setIdentifier("15521331111");
//        loginAccountInfo.setCredentials("1234");
//        loginAccountInfo.setType(0);
//        new PostLoginInfoTask (TaskRet -> {
//        if(TaskRet!=null) {
//            Log.d("MainActivity", String.valueOf(TaskRet.getStatusCode()));
//            SharedPreferences.Editor editor=getSharedPreferences("LoginReturnData",MODE_PRIVATE).edit();
//            editor.putString("UserID",TaskRet.getUserID());
//            editor.putString("AccessToken"+TaskRet.getUserID(),TaskRet.getAccessToken());
//            editor.apply();
//        }
//        else
//            Log.d("MainActivity","gg");
//    }).execute(loginAccountInfo);
//   }
}
