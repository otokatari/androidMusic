package otokatari.com.otokatari.Tasks;

import com.google.gson.Gson;
import okhttp3.*;
import otokatari.com.otokatari.Application.otokatariAndroidApplication;
import otokatari.com.otokatari.InfrastructureExtension.TasksExtensions.CustomPostExecuteAsyncTask;
import otokatari.com.otokatari.InfrastructureExtension.TasksExtensions.TaskPostExecuteWrapper;
import otokatari.com.otokatari.Model.s.RequestLoginAccountInfo.LoginAccountInfo;
import otokatari.com.otokatari.Model.s.Response.CommonResponse;
import otokatari.com.otokatari.User.APIDocs;

import java.util.concurrent.TimeUnit;

import static otokatari.com.otokatari.Utils.HMACSHA256Utils.sha256_HMAC;

public class PostRegisterInfoAsyncTask extends CustomPostExecuteAsyncTask<String,Void, CommonResponse> {
    private OkHttpClient okHttpClient;
    private otokatari.com.otokatari.Application.otokatariAndroidApplication otokatariAndroidApplication;
    public PostRegisterInfoAsyncTask(TaskPostExecuteWrapper<CommonResponse> DoInPostExecute) {
        super(DoInPostExecute);
    }
    @Override
    public CommonResponse doInBackground(String... information) {
        try {
            LoginAccountInfo info = new LoginAccountInfo();
            info.setIdentifier(information[0]);
            String afterEncryption =otokatariAndroidApplication.RSAUtilsEncrypt(sha256_HMAC(information[1]));
            info.setCredentials(afterEncryption);
            info.setType(Integer.valueOf(information[2]));
            Gson gson = new Gson();
            String result = gson.toJson(info, LoginAccountInfo.class);
            RequestBody requestBody = FormBody.create(MediaType.parse("application/json"), result);
            Request request = new Request.Builder()
                    .url(APIDocs.fullRegister)
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



