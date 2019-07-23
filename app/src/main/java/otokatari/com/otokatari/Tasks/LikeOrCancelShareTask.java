package otokatari.com.otokatari.Tasks;

import com.google.gson.Gson;
import okhttp3.*;
import otokatari.com.otokatari.InfrastructureExtension.TasksExtensions.CustomPostExecuteAsyncTask;
import otokatari.com.otokatari.InfrastructureExtension.TasksExtensions.TaskPostExecuteWrapper;
import otokatari.com.otokatari.Model.s.RequestInfo.LikeOrCancelShare;
import otokatari.com.otokatari.Model.s.Response.CommonResponse;
import otokatari.com.otokatari.User.APIDocs;
import java.util.concurrent.TimeUnit;

public class LikeOrCancelShareTask  extends CustomPostExecuteAsyncTask<LikeOrCancelShare, Void, CommonResponse> {
    private OkHttpClient okHttpClient;
    public LikeOrCancelShareTask(TaskPostExecuteWrapper<CommonResponse> DoInPostExecute) {
        super(DoInPostExecute);
    }

    @Override
    protected CommonResponse doInBackground(LikeOrCancelShare ... likeOrCancelShares) {
        try {
            String URL=APIDocs.fullLikeOrCancelShare+likeOrCancelShares[0].getLike()+"&musicid="+
                    likeOrCancelShares[0].getMusicid()+"&commentid="+likeOrCancelShares[0].getCommentid();
            Request request = new Request.Builder()
                    .url(URL)
                    .build();
            Response response = okHttpClient.newCall(request).execute();
            if (response.isSuccessful()){
                String responseData = response.body().string();
                Gson gson = new Gson();
                CommonResponse resp = gson.fromJson(responseData,CommonResponse.class);
                return resp;
            }
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
}
