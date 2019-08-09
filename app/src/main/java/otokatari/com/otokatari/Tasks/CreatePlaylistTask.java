package otokatari.com.otokatari.Tasks;

import android.util.Log;
import com.google.gson.Gson;
import okhttp3.*;
import otokatari.com.otokatari.InfrastructureExtension.TasksExtensions.CustomPostExecuteAsyncTask;
import otokatari.com.otokatari.InfrastructureExtension.TasksExtensions.TaskPostExecuteWrapper;
import otokatari.com.otokatari.Model.s.RequestInfo.CreatePlaylist;
import otokatari.com.otokatari.Model.s.Response.CreatePlaylistResponse;
import otokatari.com.otokatari.Service.UserService.UserService;
import otokatari.com.otokatari.User.APIDocs;
import java.util.concurrent.TimeUnit;

public class CreatePlaylistTask extends CustomPostExecuteAsyncTask<CreatePlaylist,Void, CreatePlaylistResponse> {
    private OkHttpClient okHttpClient;
    public CreatePlaylistTask(TaskPostExecuteWrapper<CreatePlaylistResponse> DoInPostExecute) {
        super(DoInPostExecute);
    }
    @Override
    public CreatePlaylistResponse doInBackground(CreatePlaylist ... createPlaylists) {
        try {
            Gson gson = new Gson();
            String result = gson.toJson(createPlaylists[0], CreatePlaylist.class);
            RequestBody requestBody = FormBody.create(MediaType.parse("application/json"), result);
            Request request = new Request.Builder()
                    .url(APIDocs.fullCreatePlaylist)
                    .addHeader("Authorization","Bearer "+ UserService.GetAccessToken())
                    .post(requestBody)
                    .build();
            Response response = okHttpClient.newCall(request).execute();
            if (response.isSuccessful()) {
                String responseData = response.body().string();
                Log.d("MainActivity",responseData);
                if(responseData.contains("PlaylistId")) {
                    CreatePlaylistResponse resp = gson.fromJson(responseData, CreatePlaylistResponse.class);
                    return resp;
                }
               else {
                   CreatePlaylistResponse createPlaylistResponse=new CreatePlaylistResponse();
                   createPlaylistResponse.setPlaylistid("");
                   createPlaylistResponse.setStatusCode(-1);
                   return createPlaylistResponse;
                }
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
