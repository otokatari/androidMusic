package otokatari.com.otokatari.Activity;

import android.os.Bundle;
import otokatari.com.otokatari.R;
import otokatari.com.otokatari.Tasks.PostLoginInfoTask;

public class MainActivity extends BaseActivity
{

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
     //   test();
    }
//    public void test()
//    {
//        new PostLoginInfoTask(TaskRet -> {
//            if(TaskRet!=null) {
//
//            }
//
//        }).execute();
//    }
}
