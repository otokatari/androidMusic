package otokatari.com.otokatari.Model.s.RequestInfo;

public class SimpleMusic {
    private String Musicid;
    private String Name;
    private String Platform;
    private String Singerid;
    private String Singername;
    private String Albumname;
    private String Albumid;

    public String getAlbumid() {
        return Albumid;
    }

    public String getAlbumname() {
        return Albumname;
    }

    public String getMusicid() {
        return Musicid;
    }

    public String getName() {
        return Name;
    }

    public String getPlatform() {
        return Platform;
    }

    public String getSingerid() {
        return Singerid;
    }

    public String getSingername() {
        return Singername;
    }

    public void setAlbumid(String albumid) {
        Albumid = albumid;
    }

    public void setAlbumname(String albumname) {
        Albumname = albumname;
    }

    public void setMusicid(String musicid) {
        Musicid = musicid;
    }

    public void setName(String name) {
        Name = name;
    }

    public void setPlatform(String platform) {
        Platform = platform;
    }

    public void setSingerid(String singerid) {
        Singerid = singerid;
    }

    public void setSingername(String singername) {
        Singername = singername;
    }
}
