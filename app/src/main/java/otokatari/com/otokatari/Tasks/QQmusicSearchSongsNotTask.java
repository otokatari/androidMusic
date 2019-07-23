package otokatari.com.otokatari.Tasks;

import org.json.JSONArray;
import org.json.JSONObject;
import otokatari.com.otokatari.User.APIDocs;
import static otokatari.com.otokatari.Utils.AppUtils.GetRequest;

public class QQmusicSearchSongsNotTask {
    public void sendRequestWithOkHttp(String query) {
        String VariedUrl = APIDocs.fullQQmusicSearchSongs;
        VariedUrl = VariedUrl + query + "&page=" + 10 + "&zhida=false&perpage=" + 50;
        parseJSONWithJSONObject(GetRequest(VariedUrl));
    }

    public void parseJSONWithJSONObject(String jsonData) {
        try {
            JSONObject jsonObject = new JSONObject(jsonData);
            JSONObject data=jsonObject.getJSONObject("data");
            JSONObject song=data.getJSONObject("song");
            JSONArray list=song.getJSONArray("list");
            int listLength = list.length();

            for (int i = 0; i < listLength; i++) {
                JSONObject eachSong = list.getJSONObject(i);
               String songmid=eachSong.getString("songmid");
               String songname=eachSong.getString("songname");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
