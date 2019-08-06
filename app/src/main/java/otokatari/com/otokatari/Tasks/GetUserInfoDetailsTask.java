package otokatari.com.otokatari.Tasks;

import com.google.gson.Gson;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import otokatari.com.otokatari.InfrastructureExtension.TasksExtensions.CustomPostExecuteAsyncTask;
import otokatari.com.otokatari.InfrastructureExtension.TasksExtensions.TaskPostExecuteWrapper;
import otokatari.com.otokatari.Model.s.Response.UserInfoDetailsResponse;
import otokatari.com.otokatari.Service.UserService.UserService;
import otokatari.com.otokatari.User.APIDocs;
import java.util.concurrent.TimeUnit;

public class GetUserInfoDetailsTask extends CustomPostExecuteAsyncTask<String, Void, UserInfoDetailsResponse> {
    private OkHttpClient okHttpClient;
    public GetUserInfoDetailsTask(TaskPostExecuteWrapper<UserInfoDetailsResponse> DoInPostExecute) {
        super(DoInPostExecute);
    }

    @Override
    protected UserInfoDetailsResponse doInBackground(String... IDs) {
        try {
            String VariedUrl = APIDocs.fullUserInfoDetails;
            if(IDs.length > 0) {
                VariedUrl = VariedUrl + IDs[0];
            }
            Request request = new Request.Builder()
                    .url(VariedUrl)
                    .addHeader("Authorization","Bearer "+ UserService.GetAccessToken())
                    .build();
            Response response = okHttpClient.newCall(request).execute();
            if (response.isSuccessful()){
                String responseData = response.body().string();
                Gson gson = new Gson();
                UserInfoDetailsResponse userInfoDetailsResponse = gson.fromJson(responseData, UserInfoDetailsResponse.class);
                return userInfoDetailsResponse;
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
