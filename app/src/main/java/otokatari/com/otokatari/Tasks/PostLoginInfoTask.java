package otokatari.com.otokatari.Tasks;

import com.google.gson.Gson;
import okhttp3.*;
import otokatari.com.otokatari.Application.otokatariAndroidApplication;
import otokatari.com.otokatari.InfrastructureExtension.TasksExtensions.CustomPostExecuteAsyncTask;
import otokatari.com.otokatari.InfrastructureExtension.TasksExtensions.TaskPostExecuteWrapper;
import otokatari.com.otokatari.Model.s.RequestInfo.LoginAccountInfo;
import otokatari.com.otokatari.Model.s.Response.LoginResponse;
import otokatari.com.otokatari.User.APIDocs;
import java.util.concurrent.TimeUnit;
import static otokatari.com.otokatari.Utils.HMACSHA256Utils.sha256_HMAC;

public class PostLoginInfoTask extends CustomPostExecuteAsyncTask<LoginAccountInfo, Void, LoginResponse> {
    private OkHttpClient okHttpClient;
    private otokatariAndroidApplication otokatariAndroidApplication;

    public PostLoginInfoTask(TaskPostExecuteWrapper<LoginResponse> DoInPostExecute) {
        super(DoInPostExecute);

    }

    @Override
    public LoginResponse doInBackground(LoginAccountInfo ... loginAccountInfos) {
        try {

            String afterEncryption = otokatariAndroidApplication.RSAUtilsEncrypt(sha256_HMAC(loginAccountInfos[0].getCredentials()));
            loginAccountInfos[0].setCredentials(afterEncryption);
            Gson gson = new Gson();
            String result = gson.toJson(loginAccountInfos, LoginAccountInfo.class);
            RequestBody requestBody = FormBody.create(MediaType.parse("application/json"), result);
            Request request = new Request.Builder()
                    .url(APIDocs.fullLogin)
                    .post(requestBody)
                    .build();
            Response response = okHttpClient.newCall(request).execute();

            if (response.isSuccessful()) {
                String responseData = response.body().string();
                LoginResponse resp = gson.fromJson(responseData, LoginResponse.class);
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
