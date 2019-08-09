package otokatari.com.otokatari.Tasks;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import okhttp3.*;
import otokatari.com.otokatari.InfrastructureExtension.TasksExtensions.CustomPostExecuteAsyncTask;
import otokatari.com.otokatari.InfrastructureExtension.TasksExtensions.TaskPostExecuteWrapper;
import otokatari.com.otokatari.Model.s.RequestInfo.UsersID;
import otokatari.com.otokatari.Model.s.Response.ProfilePrivacyResponse;
import otokatari.com.otokatari.Service.UserService.UserService;
import otokatari.com.otokatari.User.APIDocs;

import java.util.List;
import java.util.concurrent.TimeUnit;

public class GetProfilePrivacyListTask extends CustomPostExecuteAsyncTask<UsersID, Void, List<ProfilePrivacyResponse>> {
    private OkHttpClient okHttpClient;

    public GetProfilePrivacyListTask(TaskPostExecuteWrapper<List<ProfilePrivacyResponse>> DoInPostExecute) {
        super(DoInPostExecute);
    }

    @Override
    protected List<ProfilePrivacyResponse> doInBackground(UsersID... usersIDs) {
        try {
            Gson gson1 = new Gson();
            String result = gson1.toJson(usersIDs[0], UsersID.class);
            RequestBody requestBody = FormBody.create(MediaType.parse("application/json"), result);
            Request request = new Request.Builder()
                    .url(APIDocs.fullGetProfilePrivacyList)
                    .addHeader("Authorization", "Bearer " + UserService.GetAccessToken())
                    .post(requestBody)
                    .build();
            Response response = okHttpClient.newCall(request).execute();
            if (response.isSuccessful()) {
                String responseData = response.body().string();
                Gson gson = new Gson();
                List<ProfilePrivacyResponse> resp = gson.fromJson(responseData, new TypeToken<List<ProfilePrivacyResponse>>() {
                }.getType());
                return resp;
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
