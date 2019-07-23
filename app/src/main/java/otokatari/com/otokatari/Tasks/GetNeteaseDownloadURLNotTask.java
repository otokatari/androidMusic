package otokatari.com.otokatari.Tasks;

import org.json.JSONArray;
import org.json.JSONObject;
import otokatari.com.otokatari.User.APIDocs;

import static otokatari.com.otokatari.Utils.AppUtils.GetRequest;

public class GetNeteaseDownloadURLNotTask {

    public void sendRequestWithOkHttp(String id) {
        String VariedUrl = APIDocs.fullNeteaseGetDownloadAddress;
        VariedUrl = VariedUrl + id + "&br=" + 320000;
        parseJSONWithJSONObject(GetRequest(VariedUrl));
    }

    public void parseJSONWithJSONObject(String jsonData) {
        try {
            JSONObject jsonObject = new JSONObject(jsonData);
            JSONArray data = jsonObject.getJSONArray("data");
            int dataLength = data.length();

            for (int i = 0; i < dataLength; i++) {
                JSONObject eachSong = data.getJSONObject(i);
                int id = eachSong.getInt("id");
                String url = eachSong.getString("url");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
