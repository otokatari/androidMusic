package otokatari.com.otokatari.Activity;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.media.MediaPlayer;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;
import com.jakewharton.rxbinding.view.RxView;
import com.jakewharton.rxbinding.widget.RxCheckedTextView;
import com.jakewharton.rxbinding.widget.RxCompoundButton;
import com.jakewharton.rxbinding.widget.RxTextView;
import otokatari.com.otokatari.Application.otokatariAndroidApplication;
import otokatari.com.otokatari.R;
import rx.Observable;
import rx.functions.Action1;
import rx.observers.Observers;

import java.io.IOException;

public class playMusicActivity extends BaseActivity implements View.OnClickListener {
    private MediaPlayer mediaPlayer;
    private boolean prepared = false;
    private boolean preparing = false;
    private Button play;
   private String play_url;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_play_music);
        play = (Button) findViewById(R.id.button1);
        Button pause = (Button) findViewById(R.id.button2);
        Button stop = (Button) findViewById(R.id.button3);
        play.setOnClickListener(this);
        pause.setOnClickListener(this);
        stop.setOnClickListener(this);

        Intent intent=getIntent();
        play_url=intent.getStringExtra("play_url");

        if (ContextCompat.checkSelfPermission(playMusicActivity.this, Manifest.permission.ACCESS_NETWORK_STATE) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(playMusicActivity.this, new String[]{
                    Manifest.permission.ACCESS_NETWORK_STATE
            }, 1);
        }
    }
    private void initMediaPlayer(String play_url) {
        try {
            if (mediaPlayer == null) {
                // do object instance init.
                mediaPlayer = new MediaPlayer();
                mediaPlayer.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
                    @Override
                    public void onPrepared(MediaPlayer mediaPlayer) {
                        mediaPlayer.start();
                        prepared = true;
                        preparing = false;
                    }
                });


            }
            mediaPlayer.setDataSource(play_url);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        switch (requestCode) {
            case 1:
                if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {

                } else {
                    Toast.makeText(this, "没有申请网络权限", Toast.LENGTH_SHORT).show();
                }
                break;
            default:
        }
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.button1:
                initMediaPlayer(play_url);
                if(!mediaPlayer.isPlaying()) {
                    if(prepared) mediaPlayer.start();
                    else if(!preparing) {
                        mediaPlayer.prepareAsync();
                        preparing = true;
                    }
                    else Toast.makeText(this,"正在缓冲中...",Toast.LENGTH_SHORT).show();
                }
                break;

            case R.id.button2:
                if (mediaPlayer.isPlaying()) {
                    mediaPlayer.pause();
                }
                break;
            case R.id.button3:
                mediaPlayer.reset();
                prepared = false;
                initMediaPlayer(play_url);
                break;
            default:
                break;
        }
    }
    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (mediaPlayer != null) {
            mediaPlayer.stop();
            mediaPlayer.release();
        }
    }
}
