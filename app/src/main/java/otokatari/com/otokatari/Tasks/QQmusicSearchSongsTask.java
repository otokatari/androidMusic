package otokatari.com.otokatari.Tasks;

import okhttp3.OkHttpClient;
import org.json.JSONArray;
import org.json.JSONObject;
import otokatari.com.otokatari.InfrastructureExtension.TasksExtensions.CustomPostExecuteAsyncTask;
import otokatari.com.otokatari.InfrastructureExtension.TasksExtensions.TaskPostExecuteWrapper;
import otokatari.com.otokatari.Model.s.Response.KugouSearchSongsResponse;
import otokatari.com.otokatari.Model.s.Response.QQmusicSearchSongsResponse;
import otokatari.com.otokatari.User.APIDocs;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;
import static otokatari.com.otokatari.Utils.AppUtils.GetRequest;

public class QQmusicSearchSongsTask extends CustomPostExecuteAsyncTask<String, Void, List<QQmusicSearchSongsResponse>> {
    private OkHttpClient okHttpClient;
    List<QQmusicSearchSongsResponse> qQmusicSearchSongsResponses =new ArrayList<>();

    public QQmusicSearchSongsTask(TaskPostExecuteWrapper<List<QQmusicSearchSongsResponse>> DoInPostExecute) {
        super(DoInPostExecute);
    }

    @Override
    protected List<QQmusicSearchSongsResponse> doInBackground(String... IDs) {
        try {
            String VariedUrl = APIDocs.fullQQmusicSearchSongs;
            VariedUrl = VariedUrl + IDs[0] + "&page=" + 10 + "&zhida=false&perpage=" + 50;
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

    public List<QQmusicSearchSongsResponse> parseJSONWithJSONObject(String jsonData) {
        try {
            JSONObject jsonObject = new JSONObject(jsonData);
            JSONObject data = jsonObject.getJSONObject("data");
            JSONObject song = data.getJSONObject("song");
            JSONArray list = song.getJSONArray("list");
            int listLength = list.length();

            for (int i = 0; i < listLength; i++) {
                JSONObject eachSong = list.getJSONObject(i);
                String albummid=eachSong.getString("albummid");
                String songmid = eachSong.getString("songmid");
                String songid=eachSong.getString("songid");
                String songname = eachSong.getString("songname");
                JSONArray singer=eachSong.getJSONArray("singer");
                String singerName=singer.getJSONObject(0).getString("name");
                QQmusicSearchSongsResponse qQmusicSearchSongsResponse=new QQmusicSearchSongsResponse(songname,singerName,songmid,songid,albummid);
               qQmusicSearchSongsResponses.add(qQmusicSearchSongsResponse);
            }
            return qQmusicSearchSongsResponses;
        }
        catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}



