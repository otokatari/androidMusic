package otokatari.com.otokatari.Tasks;

import com.google.gson.Gson;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import otokatari.com.otokatari.InfrastructureExtension.TasksExtensions.CustomPostExecuteAsyncTask;
import otokatari.com.otokatari.InfrastructureExtension.TasksExtensions.TaskPostExecuteWrapper;
import otokatari.com.otokatari.Model.s.Response.SongShareListReponse;
import otokatari.com.otokatari.User.APIDocs;
import java.util.concurrent.TimeUnit;

public class GetSongShareListTask  extends CustomPostExecuteAsyncTask<String, Void, SongShareListReponse> {
    private OkHttpClient okHttpClient;

    public GetSongShareListTask(TaskPostExecuteWrapper<SongShareListReponse> DoInPostExecute) {
        super(DoInPostExecute);
    }

    @Override
    protected SongShareListReponse doInBackground(String... IDs) {
        try {
            String VariedUrl = APIDocs.fullGetSongSharelist;
            VariedUrl = VariedUrl + IDs[0];
            Request request = new Request.Builder()
                    .url(VariedUrl)
                    .build();
            Response response = okHttpClient.newCall(request).execute();
            if (response.isSuccessful()) {
                String responseData = response.body().string();
                Gson gson = new Gson();
                SongShareListReponse songShareListReponse = gson.fromJson(responseData, SongShareListReponse.class);
                return songShareListReponse;
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
