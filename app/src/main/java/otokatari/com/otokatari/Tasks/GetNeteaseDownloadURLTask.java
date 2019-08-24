package otokatari.com.otokatari.Tasks;

import okhttp3.OkHttpClient;
import org.json.JSONArray;
import org.json.JSONObject;
import otokatari.com.otokatari.InfrastructureExtension.TasksExtensions.CustomPostExecuteAsyncTask;
import otokatari.com.otokatari.InfrastructureExtension.TasksExtensions.TaskPostExecuteWrapper;
import otokatari.com.otokatari.Model.s.Response.KugouGetDownloadAddResponse;
import otokatari.com.otokatari.User.APIDocs;

import java.util.concurrent.TimeUnit;

import static otokatari.com.otokatari.Utils.AppUtils.GetRequest;

public class GetNeteaseDownloadURLTask extends CustomPostExecuteAsyncTask<String, Void, String> {
    private OkHttpClient okHttpClient;


    public GetNeteaseDownloadURLTask(TaskPostExecuteWrapper<String> DoInPostExecute) {
        super(DoInPostExecute);
    }

    @Override
    protected String doInBackground(String... IDs) {
        try {
            String VariedUrl = APIDocs.fullNeteaseGetDownloadAddress;
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

    public String parseJSONWithJSONObject(String jsonData) {
        try {
            JSONObject jsonObject = new JSONObject(jsonData);
            JSONArray data = jsonObject.getJSONArray("data");
                JSONObject eachSong = data.getJSONObject(0);
                String url = eachSong.getString("url");
                return url;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}

