package otokatari.com.otokatari.Tasks;

import com.google.gson.Gson;
import okhttp3.*;
import otokatari.com.otokatari.InfrastructureExtension.TasksExtensions.CustomPostExecuteAsyncTask;
import otokatari.com.otokatari.InfrastructureExtension.TasksExtensions.TaskPostExecuteWrapper;
import otokatari.com.otokatari.Model.s.RequestInfo.UploadPlayBehavior;
import otokatari.com.otokatari.Model.s.Response.CommonResponse;
import otokatari.com.otokatari.User.APIDocs;
import java.util.concurrent.TimeUnit;

public class PostPlayBehaviorTask extends CustomPostExecuteAsyncTask<UploadPlayBehavior,Void, CommonResponse> {
    private OkHttpClient okHttpClient;
    public PostPlayBehaviorTask(TaskPostExecuteWrapper<CommonResponse> DoInPostExecute) {
        super(DoInPostExecute);
    }
    @Override
    public CommonResponse doInBackground(UploadPlayBehavior ... uploadPlayBehaviors) {
        try {

            Gson gson = new Gson();
            String result = gson.toJson(uploadPlayBehaviors[0], UploadPlayBehavior.class);
            RequestBody requestBody = FormBody.create(MediaType.parse("application/json"), result);
            Request request = new Request.Builder()
                    .url(APIDocs.fullUploadPlayBehavior)
                    .addHeader("Authorization","Bearer eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJodHRwOi8vc2NoZW1hcy54bWxzb2FwLm9yZy93cy8yMDA1LzA1L2lkZW50aXR5L2NsYWltcy9uYW1lIjoiODk0NTQyODAyMDA2ODM1MiIsImV4cCI6MTU2NDA2Mzc3MSwiaXNzIjoiT1RPS0FSQVJJLUlTU1VFUiIsImF1ZCI6Ik9UT0tBUkFSSS1BVURJRU5DRSJ9.sRV7QDH8KppMtDfHvtH-8KrvtH6EbUpJPMjeU0ZGGx4")
                    .post(requestBody)
                    .build();
            Response response = okHttpClient.newCall(request).execute();
            if (response.isSuccessful()) {
                String responseData = response.body().string();
                CommonResponse resp = gson.fromJson(responseData, CommonResponse.class);
                return resp;
            }
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
