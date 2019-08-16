package otokatari.com.otokatari.Activity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import com.bumptech.glide.Glide;
import de.hdodenhof.circleimageview.CircleImageView;
import otokatari.com.otokatari.Application.otokatariAndroidApplication;
import otokatari.com.otokatari.Model.s.RequestInfo.*;
import otokatari.com.otokatari.R;
import otokatari.com.otokatari.Service.UserService.UserAccount;
import otokatari.com.otokatari.Service.UserService.UserService;
import otokatari.com.otokatari.Tasks.*;

import static otokatari.com.otokatari.Service.UserService.UserService.GetAccessToken;


public class MainActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        init();
        // test();
    }

    private void init() {
        Button search = findViewById(R.id.search);
        CircleImageView ToolbarUserAvatar = findViewById(R.id.ToolbarUserAvatar);

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
