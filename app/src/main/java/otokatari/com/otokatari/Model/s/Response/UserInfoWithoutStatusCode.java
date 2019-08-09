package otokatari.com.otokatari.Model.s.Response;

public class UserInfoWithoutStatusCode {
    private byte Sex;
    private String NickName;
    private String Country;
    private String City;
    private String Avatar;
    private String Signature;
    private int Birthday;
    private String Userid;

    public byte getSex() {
        return Sex;
    }

    public int getBirthday() {
        return Birthday;
    }

    public String getAvatar() {
        return Avatar;
    }

    public String getCity() {
        return City;
    }

    public String getCountry() {
        return Country;
    }

    public String getNickName() {
        return NickName;
    }

    public String getSignature() {
        return Signature;
    }

    public void setAvatar(String avatar) {
        Avatar = avatar;
    }

    public void setBirthday(int birthday) {
        Birthday = birthday;
    }

    public void setCity(String city) {
        City = city;
    }

    public void setCountry(String country) {
        Country = country;
    }

    public void setNickName(String nickName) {
        NickName = nickName;
    }

    public void setSex(byte sex) {
        Sex = sex;
    }

    public void setSignature(String signature) {
        Signature = signature;
    }

    public String getUserid() {
        return Userid;
    }

    public void setUserid(String userid) {
        Userid = userid;
    }
}
