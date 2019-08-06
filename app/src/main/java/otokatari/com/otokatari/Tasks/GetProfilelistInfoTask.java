package otokatari.com.otokatari.Tasks;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import okhttp3.*;
import otokatari.com.otokatari.InfrastructureExtension.TasksExtensions.CustomPostExecuteAsyncTask;
import otokatari.com.otokatari.InfrastructureExtension.TasksExtensions.TaskPostExecuteWrapper;
import otokatari.com.otokatari.Model.s.RequestInfo.UsersID;
import otokatari.com.otokatari.Model.s.Response.UserInfoWithoutStatusCode;
import otokatari.com.otokatari.Service.UserService.UserService;
import otokatari.com.otokatari.User.APIDocs;
import java.util.List;
import java.util.concurrent.TimeUnit;

public class GetProfilelistInfoTask extends CustomPostExecuteAsyncTask<UsersID, Void, List<UserInfoWithoutStatusCode>> {
    private OkHttpClient okHttpClient;
    public GetProfilelistInfoTask(TaskPostExecuteWrapper<List<UserInfoWithoutStatusCode>> DoInPostExecute) {
        super(DoInPostExecute);
    }

    @Override
    protected List<UserInfoWithoutStatusCode> doInBackground(UsersID ... usersID) {
        try {
            Gson gson1 = new Gson();
            String result = gson1.toJson(usersID,UsersID.class);
            RequestBody requestBody = FormBody.create(MediaType.parse("application/json"), result);
            Request request = new Request.Builder()
                    .url(APIDocs.fullGetProfilelist)
                    .addHeader("Authorization","Bearer "+ UserService.GetAccessToken())
                    .post(requestBody)
                    .build();
            Response response = okHttpClient.newCall(request).execute();
            if (response.isSuccessful()){
                String responseData = response.body().string();
                Gson gson = new Gson();
                List<UserInfoWithoutStatusCode> resp = gson.fromJson(responseData, new TypeToken<List<UserInfoWithoutStatusCode>>(){}.getType());
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
