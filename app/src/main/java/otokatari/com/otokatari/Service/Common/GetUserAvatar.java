package otokatari.com.otokatari.Service.Common;

import android.content.Context;
import otokatari.com.otokatari.User.APIDocs;
import java.io.*;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

public class GetUserAvatar {

    //把从服务器获得图片的输入流InputStream写到本地
    public static void saveImage(InputStream inputStream,Context context) {
        byte[] data = new byte[1024];
        int len = 0;
        FileOutputStream fileOutputStream = null;
        try {
            fileOutputStream =context.openFileOutput("avatar", Context.MODE_PRIVATE);
            while ((len = inputStream.read(data)) != -1) {
                fileOutputStream.write(data, 0, len);
            }
        }
        catch (IOException e) {
            e.printStackTrace();
        }
        finally {
            if (inputStream != null) {
                try {
                    inputStream.close();
                }
                catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if (fileOutputStream != null) {
                try {
                    fileOutputStream.close();
                }
                catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
    // 从服务器获得一个输入流(从服务器获得一个image输入流)
    public static InputStream getInputStream(String avatar) {
        InputStream inputStream = null;
        HttpURLConnection httpURLConnection = null;
        String fullAvatarURL= APIDocs.fullGetUserHeadphoto+avatar;
        try {
            URL url = new URL(fullAvatarURL);
            httpURLConnection = (HttpURLConnection) url.openConnection();
// 设置网络连接超时时间
            httpURLConnection.setConnectTimeout(3000);
// 设置应用程序要从网络连接读取数据
            httpURLConnection.setDoInput(true);
            httpURLConnection.setRequestMethod("GET");

            int responseCode = httpURLConnection.getResponseCode();
            if (responseCode == 200) {
// 从服务器返回一个输入流
                inputStream = httpURLConnection.getInputStream();
            }
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return inputStream;
    }

    public static void getUserAvatar(String avatar,Context context) {
// 从服务器端获得图片，保存到本地
        saveImage(getInputStream(avatar),context);
    }
}