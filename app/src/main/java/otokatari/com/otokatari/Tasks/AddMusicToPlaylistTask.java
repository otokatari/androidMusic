package otokatari.com.otokatari.Tasks;

import com.google.gson.Gson;
import okhttp3.*;
import otokatari.com.otokatari.InfrastructureExtension.TasksExtensions.CustomPostExecuteAsyncTask;
import otokatari.com.otokatari.InfrastructureExtension.TasksExtensions.TaskPostExecuteWrapper;
import otokatari.com.otokatari.Model.s.RequestInfo.Songs;
import otokatari.com.otokatari.Model.s.RequestInfo.SongsWithPlaylistID;
import otokatari.com.otokatari.Model.s.Response.CommonResponse;
import otokatari.com.otokatari.User.APIDocs;
import java.util.concurrent.TimeUnit;

public class AddMusicToPlaylistTask extends CustomPostExecuteAsyncTask<SongsWithPlaylistID,Void, CommonResponse> {
    private OkHttpClient okHttpClient;
    public AddMusicToPlaylistTask(TaskPostExecuteWrapper<CommonResponse> DoInPostExecute) {
        super(DoInPostExecute);
    }
    @Override
    public CommonResponse doInBackground(SongsWithPlaylistID ... songsWithPlaylistIDS) {
        try {
            Gson gson = new Gson();
            String result = gson.toJson(songsWithPlaylistIDS, Songs.class);
            RequestBody requestBody = FormBody.create(MediaType.parse("application/json"), result);
            String validUrl=APIDocs.fullAddMusicToPlaylist+songsWithPlaylistIDS[0].getPlaylistid();
            Request request = new Request.Builder()
                    .url(validUrl)
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
