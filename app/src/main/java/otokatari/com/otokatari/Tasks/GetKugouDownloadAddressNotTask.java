package otokatari.com.otokatari.Tasks;

import org.json.JSONArray;
import org.json.JSONObject;
import otokatari.com.otokatari.User.APIDocs;
import static otokatari.com.otokatari.Utils.AppUtils.GetRequest;

public class GetKugouDownloadAddressNotTask  {
    public void sendRequestWithOkHttp(String FileHash) {
        String VariedUrl = APIDocs.fullKugouGetDownloadAddress;
        VariedUrl = VariedUrl+FileHash;
        parseJSONWithJSONObject(GetRequest(VariedUrl));
    }

    public void parseJSONWithJSONObject(String jsonData) {
        try {
            JSONObject jsonObject = new JSONObject(jsonData);
            JSONObject data = jsonObject.getJSONObject("data");
            String video_id=data.getString("video_id");
            String author_name=data.getString("author_name");
            String song_name=data.getString("song_name");
            String play_url=data.getString("play_url");

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
