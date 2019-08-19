package otokatari.com.otokatari.Tasks;

import okhttp3.OkHttpClient;
import org.json.JSONArray;
import org.json.JSONObject;
import otokatari.com.otokatari.InfrastructureExtension.TasksExtensions.CustomPostExecuteAsyncTask;
import otokatari.com.otokatari.InfrastructureExtension.TasksExtensions.TaskPostExecuteWrapper;
import otokatari.com.otokatari.Model.s.Response.KugouGetDownloadAddResponse;
import otokatari.com.otokatari.Model.s.Response.KugouSearchSongsResponse;
import otokatari.com.otokatari.User.APIDocs;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;
import static otokatari.com.otokatari.Utils.AppUtils.GetRequest;

public class GetKugouDownloadAddressTask extends CustomPostExecuteAsyncTask<String, Void, KugouGetDownloadAddResponse> {
private OkHttpClient okHttpClient;


public GetKugouDownloadAddressTask(TaskPostExecuteWrapper<KugouGetDownloadAddResponse> DoInPostExecute) {
        super(DoInPostExecute);
        }

@Override
protected KugouGetDownloadAddResponse doInBackground(String... IDs) {
        try {
        String VariedUrl = APIDocs.fullKugouGetDownloadAddress;
        VariedUrl = VariedUrl + IDs[0];
        return parseJSONWithJSONObject(GetRequest(VariedUrl));
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

public KugouGetDownloadAddResponse parseJSONWithJSONObject(String jsonData) {
        try {
        JSONObject jsonObject = new JSONObject(jsonData);
        JSONObject songs = jsonObject.getJSONObject("data");
        String lyrics=songs.getString("lyrics");
        String play_url=songs.getString("play_url");
        String img=songs.getString("img");
        String song_name=songs.getString("song_name");
        String author_name=songs.getString("author_name");

        KugouGetDownloadAddResponse kugouGetDownloadAddResponse=new KugouGetDownloadAddResponse(play_url,lyrics,img,author_name,song_name);

        return  kugouGetDownloadAddResponse;

        } catch (Exception e) {
        e.printStackTrace();
        }
        return null;
        }
}

