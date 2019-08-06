package otokatari.com.otokatari.Tasks;

import okhttp3.*;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class UpdateUserAvatar {
    public void updateUserAvatars()throws IOException {
        String url = "http://129.204.223.158/backend/user/profile/changeavatar";
        File file = new File("avatar.jpg");
        String ext = file.getName().split(".")[1];
        RequestBody fileBody = RequestBody.create(MediaType.parse("image/"+ext),file);
        RequestBody body = new MultipartBody.Builder()
                .setType(MultipartBody.FORM)
                .addFormDataPart("file",file.getName(),fileBody)
                .build();
        Map<String,String> headers = new HashMap<>();
        headers.put("Authorization","Bearer "+"<JWT-Token-Here>");
        Headers headerz = Headers.of(headers);
        Request request = new Request.Builder().post(body).headers(headerz).url(url).build();
        OkHttpClient client = new OkHttpClient();
        Response resp = client.newCall(request).execute();
        if(resp.isSuccessful())
        {
            System.out.println(resp.body().string());
        }
    }
}
