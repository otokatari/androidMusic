package otokatari.com.otokatari.Tasks;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import otokatari.com.otokatari.InfrastructureExtension.TasksExtensions.CustomPostExecuteAsyncTask;
import otokatari.com.otokatari.InfrastructureExtension.TasksExtensions.TaskPostExecuteWrapper;
import otokatari.com.otokatari.Model.s.RequestInfo.Playlist;
import otokatari.com.otokatari.Service.UserService.UserService;
import otokatari.com.otokatari.User.APIDocs;
import java.util.List;
import java.util.concurrent.TimeUnit;

public class QueryPlaylistTask extends CustomPostExecuteAsyncTask<String, Void, List<Playlist>> {
    private OkHttpClient okHttpClient;

    public QueryPlaylistTask(TaskPostExecuteWrapper<List<Playlist>> DoInPostExecute) {
        super(DoInPostExecute);
    }

    @Override
    protected List<Playlist> doInBackground(String ... strings) {
        try {
            String VariedUrl = APIDocs.fullQueryPlaylist;
            if(strings.length > 0) {
                VariedUrl = VariedUrl + strings[0];
            }
            Request request = new Request.Builder()
                    .url(VariedUrl)
                    .addHeader("Authorization","Bearer "+ UserService.GetAccessToken())
                    .build();

            Response response = okHttpClient.newCall(request).execute();
            if (response.isSuccessful()) {
                String responseData = response.body().string();
                Gson gson = new Gson();
                List<Playlist> resp = gson.fromJson(responseData, new TypeToken<List<Playlist>>() {
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
