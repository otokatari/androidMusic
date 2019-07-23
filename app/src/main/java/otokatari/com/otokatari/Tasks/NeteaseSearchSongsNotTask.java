package otokatari.com.otokatari.Tasks;

import org.json.JSONArray;
import org.json.JSONObject;
import otokatari.com.otokatari.User.APIDocs;
import static otokatari.com.otokatari.Utils.AppUtils.GetRequest;

public class NeteaseSearchSongsNotTask {

    public void sendRequestWithOkHttp(String keyword) {
        String VariedUrl = APIDocs.fullNeteaseSearchSongs;
        VariedUrl = VariedUrl + keyword + "&limit=" + 100 + "&offest=" + 0;
        parseJSONWithJSONObject(GetRequest(VariedUrl));
    }

    public void parseJSONWithJSONObject(String jsonData) {
        try {
            JSONObject jsonObject = new JSONObject(jsonData);
            JSONArray songs = jsonObject.getJSONObject("result").getJSONArray("songs");
            int SongsArrayLength = songs.length();

            for (int i = 0; i < SongsArrayLength; i++) {
                JSONObject eachSong = songs.getJSONObject(i);
                int id = eachSong.getInt("id");
                String songName = eachSong.getString("name");
                JSONArray singers = eachSong.getJSONArray("artists"); // 因为歌手可能不止一个,这里取第0个
                int singerId = singers.getJSONObject(0).getInt("id");
                String singerName = singers.getJSONObject(0).getString("name");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

