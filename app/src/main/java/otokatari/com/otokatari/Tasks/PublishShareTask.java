package otokatari.com.otokatari.Tasks;

import com.google.gson.Gson;
import okhttp3.*;
import otokatari.com.otokatari.InfrastructureExtension.TasksExtensions.CustomPostExecuteAsyncTask;
import otokatari.com.otokatari.InfrastructureExtension.TasksExtensions.TaskPostExecuteWrapper;
import otokatari.com.otokatari.Model.s.RequestInfo.PublishShare;
import otokatari.com.otokatari.Model.s.RequestInfo.PublishShareWithoutMusicid;
import otokatari.com.otokatari.Model.s.Response.CommonResponse;
import otokatari.com.otokatari.User.APIDocs;
import java.util.concurrent.TimeUnit;

public class PublishShareTask  extends CustomPostExecuteAsyncTask<PublishShare, Void, CommonResponse> {
    private OkHttpClient okHttpClient;
    private otokatari.com.otokatari.Application.otokatariAndroidApplication otokatariAndroidApplication;

    public PublishShareTask(TaskPostExecuteWrapper<CommonResponse> DoInPostExecute) {
        super(DoInPostExecute);

    }

    @Override
    public CommonResponse doInBackground(PublishShare ... publishShare) {
        try {
            String URL=APIDocs.fullPublishShare+publishShare[0].getMusicid();
            PublishShareWithoutMusicid publishShareWithoutMusicid=new PublishShareWithoutMusicid();
            publishShareWithoutMusicid=publishShare[0].getPublishShareWithoutMusicid();

            Gson gson = new Gson();
            String result = gson.toJson(publishShareWithoutMusicid, PublishShareWithoutMusicid.class);
            RequestBody requestBody = FormBody.create(MediaType.parse("application/json"), result);
            Request request = new Request.Builder()
                    .url(URL)
                    .post(requestBody)
                    .build();
            Response response = okHttpClient.newCall(request).execute();

            if (response.isSuccessful()) {
                String responseData = response.body().string();
               CommonResponse res = gson.fromJson(responseData, CommonResponse.class);
                return res;
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
