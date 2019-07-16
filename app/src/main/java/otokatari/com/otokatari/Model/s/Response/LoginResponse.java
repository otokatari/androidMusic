package otokatari.com.otokatari.Model.s.Response;

public class LoginResponse extends CommonResponse {
    private String UserID;
    private String AccessToken;
    private String ExpireTime;

    public String getAccessToken() {
        return AccessToken;
    }

    public String getExpireTime() {
        return ExpireTime;
    }

    public String getUserID() {
        return UserID;
    }

    public void setAccessToken(String accessToken) {
        AccessToken = accessToken;
    }

    public void setExpireTime(String expireTime) {
        ExpireTime = expireTime;
    }

    public void setUserID(String userID) {
        UserID = userID;
    }
}
