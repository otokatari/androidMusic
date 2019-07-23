package otokatari.com.otokatari.Tasks;

import com.google.gson.Gson;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import otokatari.com.otokatari.InfrastructureExtension.TasksExtensions.CustomPostExecuteAsyncTask;
import otokatari.com.otokatari.InfrastructureExtension.TasksExtensions.TaskPostExecuteWrapper;
import otokatari.com.otokatari.Model.s.Response.CommonResponse;
import otokatari.com.otokatari.User.APIDocs;
import java.util.concurrent.TimeUnit;

public class DeleteMusicInPlaylistTask extends CustomPostExecuteAsyncTask<String, Void, CommonResponse> {
    private OkHttpClient okHttpClient;

    public DeleteMusicInPlaylistTask(TaskPostExecuteWrapper<CommonResponse> DoInPostExecute) {
        super(DoInPostExecute);
    }

    @Override
    protected CommonResponse doInBackground(String... IDs) {
        try {
            String VariedUrl = APIDocs.fullDeleteMusicInPlaylist;
            if (IDs.length > 0) {
            VariedUrl = VariedUrl +"playlistid="+IDs[0]+"&musicid="+IDs[1];
            }
            Request request = new Request.Builder()
                    .url(VariedUrl)
                    .build();
            Response response = okHttpClient.newCall(request).execute();
            if (response.isSuccessful()) {
                String responseData = response.body().string();
                Gson gson = new Gson();
                CommonResponse commonResponse = gson.fromJson(responseData, CommonResponse.class);
                return commonResponse;
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
