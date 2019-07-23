package otokatari.com.otokatari.Tasks;

import org.json.JSONArray;
import org.json.JSONObject;
import otokatari.com.otokatari.User.APIDocs;
import static otokatari.com.otokatari.Utils.AppUtils.GetRequest;

public class GetQQmusicVkey {
    public void sendRequestWithOkHttp(String songmid) {
        String VariedUrl = APIDocs.fullGetQQmusicVkey;
        VariedUrl = VariedUrl + songmid;
        parseJSONWithJSONObject(GetRequest(VariedUrl));
    }

    public void parseJSONWithJSONObject(String jsonData) {
        try {
            JSONObject jsonObject = new JSONObject(jsonData);
            JSONObject data=jsonObject.getJSONObject("data");
            JSONArray items=data.getJSONArray("items");
            int length = items.length();

            for (int i = 0; i < length; i++) {
                JSONObject eachSong = items.getJSONObject(i);
                String vkey=eachSong.getString("vkey");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
