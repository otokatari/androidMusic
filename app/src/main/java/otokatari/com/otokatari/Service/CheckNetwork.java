//package otokatari.com.otokatari.Service;
//
//import android.content.BroadcastReceiver;
//import android.content.Context;
//import android.content.Intent;
//import android.content.IntentFilter;
//import android.net.ConnectivityManager;
//import android.net.NetworkInfo;
//import android.support.v7.app.AppCompatActivity;
//import android.widget.Toast;
//
//public class CheckNetwork extends AppCompatActivity {
//
//
//
//    @Override
//    protected void onDestroy()
//    {
//        super.onDestroy();
//        unregisterReceiver(networkChangeReceiver);
//    }
//   public class NetworkChangeReceiver extends BroadcastReceiver
//    {
//        @Override
//        public void onReceive(Context context, Intent intent)
//        {
//            ConnectivityManager connectivityManager=(ConnectivityManager)getSystemService(Context.CONNECTIVITY_SERVICE);
//            NetworkInfo networkInfo=connectivityManager.getActiveNetworkInfo();
//            if(networkInfo==null||!networkInfo.isAvailable())
//            {
//                Toast.makeText(context,"无法连接到网络",Toast.LENGTH_SHORT).show();
//            }
//
//        }
//    }
//}
//
