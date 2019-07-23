package otokatari.com.otokatari.Model.s.RequestInfo;

import okhttp3.internal.platform.Platform;

public class Songs {
    private String Musicid;
    private String Platform;
    private String Singerid;
    private String Singername;
    private String Albumname;
    private String Albumid;
    private int Addedtime;

    public String getSingername() {
        return Singername;
    }

    public String getSingerid() {
        return Singerid;
    }

    public String getPlatform() {
        return Platform;
    }

    public String getMusicid() {
        return Musicid;
    }

    public String getAlbumname() {
        return Albumname;
    }

    public String getAlbumid() {
        return Albumid;
    }

    public int getAddedtime() {
        return Addedtime;
    }

    public void setSingername(String singername) {
        Singername = singername;
    }

    public void setSingerid(String singerid) {
        Singerid = singerid;
    }

    public void setPlatform(String platform) {
        Platform = platform;
    }

    public void setMusicid(String musicid) {
        Musicid = musicid;
    }

    public void setAlbumname(String albumname) {
        Albumname = albumname;
    }

    public void setAlbumid(String albumid) {
        Albumid = albumid;
    }

    public void setAddedtime(int addedtime) {
        Addedtime = addedtime;
    }
}
