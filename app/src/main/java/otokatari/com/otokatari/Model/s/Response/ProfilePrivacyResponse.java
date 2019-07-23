package otokatari.com.otokatari.Model.s.Response;

public class ProfilePrivacyResponse {
    private String Userid;
    private byte Sex;
    private byte Country;
    private byte City;
    private byte Birthday;
    private byte Moment;
    private byte Playlists;

    public String getUserid() {
        return Userid;
    }

    public byte getSex() {
        return Sex;
    }

    public byte getBirthday() {
        return Birthday;
    }

    public byte getCity() {
        return City;
    }

    public byte getCountry() {
        return Country;
    }

    public byte getMoment() {
        return Moment;
    }

    public byte getPlaylists() {
        return Playlists;
    }

    public void setUserid(String userid) {
        Userid = userid;
    }

    public void setBirthday(byte birthday) {
        Birthday = birthday;
    }

    public void setCity(byte city) {
        City = city;
    }

    public void setCountry(byte country) {
        Country = country;
    }

    public void setSex(byte sex) {
        Sex = sex;
    }

    public void setMoment(byte moment) {
        Moment = moment;
    }

    public void setPlaylists(byte playlists) {
        Playlists = playlists;
    }
}
