package otokatari.com.otokatari.Tasks;

import okhttp3.OkHttpClient;
import org.json.JSONObject;
import otokatari.com.otokatari.InfrastructureExtension.TasksExtensions.CustomPostExecuteAsyncTask;
import otokatari.com.otokatari.InfrastructureExtension.TasksExtensions.TaskPostExecuteWrapper;
import otokatari.com.otokatari.User.APIDocs;

import java.util.concurrent.TimeUnit;

import static otokatari.com.otokatari.Utils.AppUtils.GetRequest;

public class GetNeteaseImgTask extends CustomPostExecuteAsyncTask<String ,Void, String> {
    private OkHttpClient okHttpClient;
    public GetNeteaseImgTask (TaskPostExecuteWrapper<String> DoInPostExecute) {
        super(DoInPostExecute);
    }

    @Override
    protected String  doInBackground(String... IDs) {
        try {
            String VariedUrl = APIDocs.fullGetNeteaseImg;
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
    public String parseJSONWithJSONObject(String jsonData) {
        try {
            JSONObject jsonObject = new JSONObject(jsonData);
            JSONObject songs=jsonObject.getJSONArray("songs").getJSONObject(0);
            JSONObject al=songs.getJSONObject("al");
            String picUrl=al.getString("picUrl");
            return picUrl;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
