package otokatari.com.otokatari.Tasks;

import okhttp3.OkHttpClient;
import org.json.JSONArray;
import org.json.JSONObject;
import otokatari.com.otokatari.InfrastructureExtension.TasksExtensions.CustomPostExecuteAsyncTask;
import otokatari.com.otokatari.InfrastructureExtension.TasksExtensions.TaskPostExecuteWrapper;
import otokatari.com.otokatari.Model.s.Response.NeteaseSearchSongsResponse;
import otokatari.com.otokatari.User.APIDocs;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;
import static otokatari.com.otokatari.Utils.AppUtils.GetRequest;

public class NeteaseSearchSongsTask extends CustomPostExecuteAsyncTask<String, Void, List<NeteaseSearchSongsResponse>> {
    private OkHttpClient okHttpClient;

    List<NeteaseSearchSongsResponse> neteaseSearchSongsResponses=new ArrayList<>();
    public NeteaseSearchSongsTask(TaskPostExecuteWrapper<List<NeteaseSearchSongsResponse>> DoInPostExecute) {
        super(DoInPostExecute);
    }

    @Override
    protected List<NeteaseSearchSongsResponse> doInBackground(String... IDs) {
        try {
            String VariedUrl = APIDocs.fullNeteaseSearchSongs;
            VariedUrl = VariedUrl + IDs[0];
            return parseJSONWithJSONObject(GetRequest(VariedUrl));
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

    public List<NeteaseSearchSongsResponse>parseJSONWithJSONObject(String jsonData) {
        try {
            JSONObject jsonObject = new JSONObject(jsonData);
            JSONArray songs = jsonObject.getJSONObject("result").getJSONArray("songs");
            int SongsArrayLength = songs.length();

            for (int i = 0; i < SongsArrayLength; i++) {
                JSONObject eachSong = songs.getJSONObject(i);
                String id = eachSong.getString("id");
                String songName = eachSong.getString("name");
                JSONArray singers = eachSong.getJSONArray("artists");
                String singerName = singers.getJSONObject(0).getString("name");
               NeteaseSearchSongsResponse neteaseSearchSongsResponse=new NeteaseSearchSongsResponse(id,songName,singerName);
               neteaseSearchSongsResponses.add(neteaseSearchSongsResponse);
            }
            return neteaseSearchSongsResponses;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}




