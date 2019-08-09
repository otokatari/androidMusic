package otokatari.com.otokatari.Tasks;

import com.google.gson.Gson;
import okhttp3.*;
import otokatari.com.otokatari.InfrastructureExtension.TasksExtensions.CustomPostExecuteAsyncTask;
import otokatari.com.otokatari.InfrastructureExtension.TasksExtensions.TaskPostExecuteWrapper;
import otokatari.com.otokatari.Model.s.RequestInfo.CommentsShare;
import otokatari.com.otokatari.Model.s.RequestInfo.CommentsShareWithID;
import otokatari.com.otokatari.Model.s.Response.CommonResponse;
import otokatari.com.otokatari.Service.UserService.UserService;
import otokatari.com.otokatari.User.APIDocs;
import java.util.concurrent.TimeUnit;

public class CommentsShareTask  extends CustomPostExecuteAsyncTask<CommentsShareWithID, Void, CommonResponse> {
    private OkHttpClient okHttpClient;

    public CommentsShareTask(TaskPostExecuteWrapper<CommonResponse> DoInPostExecute) {
        super(DoInPostExecute);

    }

    @Override
    public CommonResponse doInBackground(CommentsShareWithID... commentsShareWithIDS) {
        try {
            String URL = APIDocs.fullCommentShare + commentsShareWithIDS[0].getMusicid() + "&commentid=" + commentsShareWithIDS[0].getCommentid();
            CommentsShare commentsShare = new CommentsShare();
            commentsShare = commentsShareWithIDS[0].getCommentsShare();

            Gson gson = new Gson();
            String result = gson.toJson(commentsShare, CommentsShare.class);

            RequestBody requestBody = FormBody.create(MediaType.parse("application/json"), result);
            Request request = new Request.Builder()
                    .url(URL)
                    .addHeader("Authorization","Bearer "+ UserService.GetAccessToken())
                    .post(requestBody)
                    .build();
            Response response = okHttpClient.newCall(request).execute();

            if (response.isSuccessful()) {
                String responseData = response.body().string();
                CommonResponse res = gson.fromJson(responseData, CommonResponse.class);
                return res;
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }


            @Override
            protected void onPreExecute () {
                super.onPreExecute();
                okHttpClient = new OkHttpClient.Builder().connectTimeout(4500, TimeUnit.MILLISECONDS).build();
            }
}

