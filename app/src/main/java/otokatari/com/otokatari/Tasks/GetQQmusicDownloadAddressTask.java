package otokatari.com.otokatari.Tasks;

import android.util.Log;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import otokatari.com.otokatari.InfrastructureExtension.TasksExtensions.CustomPostExecuteAsyncTask;
import otokatari.com.otokatari.InfrastructureExtension.TasksExtensions.TaskPostExecuteWrapper;
import otokatari.com.otokatari.User.APIDocs;
import java.io.InputStream;
import java.util.concurrent.TimeUnit;

public class GetQQmusicDownloadAddressTask  extends CustomPostExecuteAsyncTask<String, Void, InputStream> {
    private OkHttpClient okHttpClient;

    public GetQQmusicDownloadAddressTask(TaskPostExecuteWrapper<InputStream> DoInPostExecute) {
        super(DoInPostExecute);
    }

    @Override
    protected InputStream doInBackground(String... IDs) {
        try {
            String VariedUrl = APIDocs.fullQQmusicGetDownloadAddress+IDs[0]+".m4a?guid=5448538077&vkey="+IDs[1]
                    +"&uin=0&fromtag=66";
                OkHttpClient client = new OkHttpClient();
                Request request = new Request.Builder()
                        .url(VariedUrl)
                        .build();
                Response response = client.newCall(request).execute();
                if(response.isSuccessful()) {
                 return response.body().byteStream();
                }

            return null;
        }
        catch (Exception e) {
            e.printStackTrace();
            Log.d("MainActivity","wsl");
        }
        return null;
    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
        okHttpClient = new OkHttpClient.Builder().connectTimeout(4500, TimeUnit.MILLISECONDS).build();
    }
}
