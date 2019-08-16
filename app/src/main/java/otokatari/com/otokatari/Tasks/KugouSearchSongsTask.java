package otokatari.com.otokatari.Tasks;

import okhttp3.OkHttpClient;
import org.json.JSONArray;
import org.json.JSONObject;
import otokatari.com.otokatari.InfrastructureExtension.TasksExtensions.CustomPostExecuteAsyncTask;
import otokatari.com.otokatari.InfrastructureExtension.TasksExtensions.TaskPostExecuteWrapper;
import otokatari.com.otokatari.Model.s.Response.KugouSearchSongsResponse;
import otokatari.com.otokatari.User.APIDocs;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;
import static otokatari.com.otokatari.Utils.AppUtils.GetRequest;

public class KugouSearchSongsTask extends CustomPostExecuteAsyncTask<String, Void, List<KugouSearchSongsResponse>> {
    private OkHttpClient okHttpClient;
    List<KugouSearchSongsResponse> kugouSearchSongsResponses=new ArrayList<>();

    public KugouSearchSongsTask(TaskPostExecuteWrapper<List<KugouSearchSongsResponse>> DoInPostExecute) {
        super(DoInPostExecute);
    }

    @Override
    protected List<KugouSearchSongsResponse> doInBackground(String... IDs) {
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

    public List<KugouSearchSongsResponse> parseJSONWithJSONObject(String jsonData) {
        try {
            JSONObject jsonObject = new JSONObject(jsonData);
            JSONArray songs = jsonObject.getJSONObject("data").getJSONArray("lists");
            int SongsArrayLength = songs.length();

            for (int i = 0; i < SongsArrayLength; i++) {
                JSONObject eachSong = songs.getJSONObject(i);
               String SongName=eachSong.getString("SongName");
               String FileHash=eachSong.getString("FileHash");
               String FileName=eachSong.getString("FileName");
//               JSONArray Grp=eachSong.getJSONArray("Grp");
//               String SongName=Grp.getJSONObject(0).getString("SongName");
//               String FileHash=Grp.getJSONObject(0).getString("FileHash");
//               String FileName=Grp.getJSONObject(0).getString("FileName");
               KugouSearchSongsResponse kugouSearchSongsResponse1=new KugouSearchSongsResponse(SongName,FileHash,FileName);
               kugouSearchSongsResponses.add(kugouSearchSongsResponse1);
           }
            return  kugouSearchSongsResponses;

        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
