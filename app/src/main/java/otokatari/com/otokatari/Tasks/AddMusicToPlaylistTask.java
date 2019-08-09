package otokatari.com.otokatari.Tasks;

import com.google.gson.Gson;
import okhttp3.*;
import otokatari.com.otokatari.InfrastructureExtension.TasksExtensions.CustomPostExecuteAsyncTask;
import otokatari.com.otokatari.InfrastructureExtension.TasksExtensions.TaskPostExecuteWrapper;
import otokatari.com.otokatari.Model.s.RequestInfo.Songs;
import otokatari.com.otokatari.Model.s.RequestInfo.SongsWithPlaylistID;
import otokatari.com.otokatari.Model.s.RequestInfo.SongsWithoutPlaylistID;
import otokatari.com.otokatari.Model.s.Response.CommonResponse;
import otokatari.com.otokatari.Service.UserService.UserService;
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
            SongsWithoutPlaylistID songsWithoutPlaylistID=new SongsWithoutPlaylistID();
            songsWithoutPlaylistID.setAlbumid(songsWithPlaylistIDS[0].getAlbumid());
            songsWithoutPlaylistID.setAlbumname(songsWithPlaylistIDS[0].getAlbumname());
            songsWithoutPlaylistID.setMusicid(songsWithPlaylistIDS[0].getMusicid());
            songsWithoutPlaylistID.setPlatform(songsWithPlaylistIDS[0].getPlatform());
            songsWithoutPlaylistID.setSingerid(songsWithPlaylistIDS[0].getSingerid());
            songsWithoutPlaylistID.setSingername(songsWithPlaylistIDS[0].getSingername());
            Gson gson = new Gson();
            String result = gson.toJson(songsWithoutPlaylistID, SongsWithoutPlaylistID.class);
            RequestBody requestBody = FormBody.create(MediaType.parse("application/json"), result);
            String validUrl=APIDocs.fullAddMusicToPlaylist+songsWithPlaylistIDS[0].getPlaylistid();
            Request request = new Request.Builder()
                    .url(validUrl)
                    .addHeader("Authorization","Bearer "+ UserService.GetAccessToken())
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
