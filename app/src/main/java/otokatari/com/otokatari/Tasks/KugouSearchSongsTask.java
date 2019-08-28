package otokatari.com.otokatari.Tasks;

import okhttp3.OkHttpClient;
import org.json.JSONArray;
import org.json.JSONObject;
import otokatari.com.otokatari.InfrastructureExtension.TasksExtensions.CustomPostExecuteAsyncTask;
import otokatari.com.otokatari.InfrastructureExtension.TasksExtensions.TaskPostExecuteWrapper;
import otokatari.com.otokatari.Model.s.Response.Kugou_QQmusicSearchSongsResponse;
import otokatari.com.otokatari.User.APIDocs;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;
import static otokatari.com.otokatari.Utils.AppUtils.GetRequest;

public class KugouSearchSongsTask extends CustomPostExecuteAsyncTask<String, Void, List<Kugou_QQmusicSearchSongsResponse>> {
    private OkHttpClient okHttpClient;
    List<Kugou_QQmusicSearchSongsResponse> kugouQQmusicSearchSongsRespons =new ArrayList<>();

    public KugouSearchSongsTask(TaskPostExecuteWrapper<List<Kugou_QQmusicSearchSongsResponse>> DoInPostExecute) {
        super(DoInPostExecute);
    }

    @Override
    protected List<Kugou_QQmusicSearchSongsResponse> doInBackground(String... IDs) {
        try {
            String VariedUrl = APIDocs.fullKugouSearchSongs;
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

    public List<Kugou_QQmusicSearchSongsResponse> parseJSONWithJSONObject(String jsonData) {
        try {
            JSONObject jsonObject = new JSONObject(jsonData);
            JSONArray songs = jsonObject.getJSONObject("data").getJSONArray("lists");
            int SongsArrayLength = songs.length();

            for (int i = 0; i < SongsArrayLength; i++) {
                JSONObject eachSong = songs.getJSONObject(i);
               String SongName=eachSong.getString("SongName");
               String FileHash=eachSong.getString("FileHash");
               String FileName=eachSong.getString("FileName");
               Kugou_QQmusicSearchSongsResponse kugouQQmusicSearchSongsResponse1 =new Kugou_QQmusicSearchSongsResponse(SongName,FileHash,FileName,null);
               kugouQQmusicSearchSongsRespons.add(kugouQQmusicSearchSongsResponse1);
           }
            return kugouQQmusicSearchSongsRespons;

        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
