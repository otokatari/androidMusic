package otokatari.com.otokatari.Tasks;

import com.google.gson.Gson;
import okhttp3.*;
import otokatari.com.otokatari.InfrastructureExtension.TasksExtensions.CustomPostExecuteAsyncTask;
import otokatari.com.otokatari.InfrastructureExtension.TasksExtensions.TaskPostExecuteWrapper;
import otokatari.com.otokatari.Model.s.Response.ProfilePrivacyResponse;
import otokatari.com.otokatari.User.APIDocs;
import java.util.concurrent.TimeUnit;

public class GetProfilePrivacyTask extends CustomPostExecuteAsyncTask<String, Void, ProfilePrivacyResponse> {
    private OkHttpClient okHttpClient;

    public GetProfilePrivacyTask(TaskPostExecuteWrapper<ProfilePrivacyResponse> DoInPostExecute) {
        super(DoInPostExecute);
    }

    @Override
    protected ProfilePrivacyResponse doInBackground(String... strings) {
        try {
            String URL = APIDocs.fullProfilePrivacy + strings[0];
            Request request = new Request.Builder()
                    .url(URL)
                    .build();
            Response response = okHttpClient.newCall(request).execute();
            if (response.isSuccessful()) {
                String responseData = response.body().string();
                if(responseData.equals("null"))
                    return null;
                else {
                    Gson gson = new Gson();
                    ProfilePrivacyResponse resp = gson.fromJson(responseData, ProfilePrivacyResponse.class);
                    return resp;
                }
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
