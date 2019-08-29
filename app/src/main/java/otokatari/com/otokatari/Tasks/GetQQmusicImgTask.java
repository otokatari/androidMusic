package otokatari.com.otokatari.Tasks;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import otokatari.com.otokatari.InfrastructureExtension.TasksExtensions.CustomPostExecuteAsyncTask;
import otokatari.com.otokatari.InfrastructureExtension.TasksExtensions.TaskPostExecuteWrapper;
import java.io.InputStream;
import java.util.concurrent.TimeUnit;

public class GetQQmusicImgTask extends CustomPostExecuteAsyncTask<String, Void, InputStream> {
    private OkHttpClient okHttpClient;

    public GetQQmusicImgTask(TaskPostExecuteWrapper<InputStream> DoInPostExecute) {
        super(DoInPostExecute);
    }

    @Override
    protected InputStream doInBackground(String... IDs) {
        try {
            String VariedUrl = "https://y.gtimg.cn/music/photo_new/T002R300x300M000"+IDs[0]+".jpg?max_age=2592000";

                OkHttpClient client = new OkHttpClient();
                Request request = new Request.Builder()
                        .url(VariedUrl)
                        .addHeader("Referer","https://y.qq.com/n/yqq/song/"+IDs[1]+"_num.html")
                        .build();

                Response response = client.newCall(request).execute();
                if(response.isSuccessful()) {
                   InputStream responseData = response.body().byteStream();
                   return responseData;
                }
            return null;
        }
        catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
    @Override
    protected void onPreExecute() {
        super.onPreExecute();
        okHttpClient = new OkHttpClient.Builder().connectTimeout(4500, TimeUnit.MILLISECONDS).build();
    }

}

