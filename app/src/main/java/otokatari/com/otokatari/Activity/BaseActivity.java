package otokatari.com.otokatari.Activity;

import android.content.*;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import otokatari.com.otokatari.Service.Common.ActivityCollector;

public class BaseActivity extends AppCompatActivity {
    Intent aa=getIntent();
    private Focreof receiver;
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ActivityCollector.addAvtivity(this);
    }
    protected void onResume(){
        super.onResume();
        IntentFilter intentFilter=new IntentFilter();
        intentFilter.addAction("com.example.office");
        receiver=new Focreof();
        registerReceiver(receiver,intentFilter);
    }
    protected void onPause(){
        super.onPause();
        if(receiver!=null){
            unregisterReceiver(receiver);
            receiver=null;
        }
    }
    protected void onDestroy(){
        super.onDestroy();
        ActivityCollector.removeActivity(this);
    }

    class Focreof extends BroadcastReceiver {
        public void onReceive(final Context context, final Intent intent){

            AlertDialog.Builder builder=new AlertDialog.Builder(context);
            builder.setTitle("警告");
            builder.setMessage("Dear:"+intent.getStringExtra("t")+"! You are forced to be offline. Please try to login again.");
            builder.setPositiveButton("OK",new DialogInterface.OnClickListener(){
                public void onClick(DialogInterface dialog, int which){
                    ActivityCollector.finishAll();
                    Intent intent=new Intent(context,MainActivity.class);
                    context.startActivity(intent);
                }
            });
            builder.show();
        }
    }
}



