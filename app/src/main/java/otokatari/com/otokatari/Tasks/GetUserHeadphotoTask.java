package otokatari.com.otokatari.Tasks;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import otokatari.com.otokatari.InfrastructureExtension.TasksExtensions.CustomPostExecuteAsyncTask;
import otokatari.com.otokatari.InfrastructureExtension.TasksExtensions.TaskPostExecuteWrapper;
import otokatari.com.otokatari.User.APIDocs;
import java.util.concurrent.TimeUnit;

public class GetUserHeadphotoTask extends CustomPostExecuteAsyncTask<String, Void, String> {
    private OkHttpClient okHttpClient;
    public GetUserHeadphotoTask(TaskPostExecuteWrapper<String> DoInPostExecute) {
        super(DoInPostExecute);
    }

    @Override
    protected String doInBackground(String... IDs) {
        try {
            String VariedUrl = APIDocs.fullGetUserHeadphoto;
            VariedUrl = VariedUrl + IDs[0];

            Request request = new Request.Builder()
                    .url(VariedUrl)
                    .build();
            Response response = okHttpClient.newCall(request).execute();
            if (response.isSuccessful()){
                String responseData = response.body().string();
                return responseData;
            }
        } catch (Exception e) {
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
