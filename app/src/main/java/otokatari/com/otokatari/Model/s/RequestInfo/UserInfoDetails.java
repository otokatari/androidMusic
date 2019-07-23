package otokatari.com.otokatari.Model.s.RequestInfo;

public class UserInfoDetails  {
    private int StatusCode;
    private byte Sex;
    private String NickName;
    private String Country;
    private String City;
    private String Avatar;
    private String Signature;
    private int Birthday;

    public String getSignature() {
        return Signature;
    }

    public String getNickName() {
        return NickName;
    }

    public String getCountry() {
        return Country;
    }

    public String getCity() {
        return City;
    }

    public String getAvatar() {
        return Avatar;
    }

    public int getBirthday() {
        return Birthday;
    }

    public byte getSex() {
        return Sex;
    }

    public int getStatusCode() {
        return StatusCode;
    }

    public void setSignature(String signature) {
        Signature = signature;
    }

    public void setSex(byte sex) {
        Sex = sex;
    }

    public void setNickName(String nickName) {
        NickName = nickName;
    }

    public void setCountry(String country) {
        Country = country;
    }

    public void setCity(String city) {
        City = city;
    }



    public void setAvatar(String avatar) {
        Avatar = avatar;
    }

    public void setStatusCode(int statusCode) {
        StatusCode = statusCode;
    }

    public void setBirthday(int birthday) {
        Birthday = birthday;
    }
}
