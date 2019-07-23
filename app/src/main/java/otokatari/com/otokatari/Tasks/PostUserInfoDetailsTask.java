package otokatari.com.otokatari.Tasks;

import com.google.gson.Gson;
import okhttp3.*;
import otokatari.com.otokatari.InfrastructureExtension.TasksExtensions.CustomPostExecuteAsyncTask;
import otokatari.com.otokatari.InfrastructureExtension.TasksExtensions.TaskPostExecuteWrapper;
import otokatari.com.otokatari.Model.s.RequestInfo.UserInfoDetails;
import otokatari.com.otokatari.Model.s.Response.CommonResponse;
import otokatari.com.otokatari.User.APIDocs;
import java.util.concurrent.TimeUnit;


public class PostUserInfoDetailsTask extends CustomPostExecuteAsyncTask<UserInfoDetails,Void, CommonResponse> {
    private OkHttpClient okHttpClient;
    private otokatari.com.otokatari.Application.otokatariAndroidApplication otokatariAndroidApplication;
    public PostUserInfoDetailsTask(TaskPostExecuteWrapper<CommonResponse> DoInPostExecute) {
        super(DoInPostExecute);
    }
    @Override
    public CommonResponse doInBackground(UserInfoDetails... userInfoDetails) {
        try {

            Gson gson = new Gson();
            String result = gson.toJson(userInfoDetails, UserInfoDetails.class);
            RequestBody requestBody = FormBody.create(MediaType.parse("application/json"), result);
            int id=0; //获取用户id?
            String fullUrl=APIDocs.fullUpdateUserInfo+id;
            Request request = new Request.Builder()
                    .url(fullUrl)
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
