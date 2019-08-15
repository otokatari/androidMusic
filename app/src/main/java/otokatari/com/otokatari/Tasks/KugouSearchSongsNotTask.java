package otokatari.com.otokatari.Tasks;

import org.json.JSONArray;
import org.json.JSONObject;
import otokatari.com.otokatari.Model.s.Bean;
import otokatari.com.otokatari.User.APIDocs;

import static otokatari.com.otokatari.Utils.AppUtils.GetRequest;

public class KugouSearchSongsNotTask {
    public static Bean sendRequestWithOkHttp(String keyword) {
        String VariedUrl = APIDocs.fullKugouSearchSongs;
        VariedUrl = VariedUrl + keyword;
        return parseJSONWithJSONObject(GetRequest(VariedUrl));
    }

    public static Bean parseJSONWithJSONObject(String jsonData) {
        try {
            JSONObject jsonObject = new JSONObject(jsonData);
            JSONArray songs = jsonObject.getJSONObject("data").getJSONArray("lists");
            int SongsArrayLength = songs.length();

            for (int i = 0; i < SongsArrayLength; i++) {
                JSONObject eachSong = songs.getJSONObject(i);
               String songName=eachSong.getString("SongName");
               int Audioid=eachSong.getInt("Audioid");
               JSONArray Grp=eachSong.getJSONArray("Grp");
               String FileHash=Grp.getJSONObject(0).getString("FileHash");
               return new Bean(songName,FileHash);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
