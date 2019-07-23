package otokatari.com.otokatari.Model.s.RequestInfo;

public class SongsWithPlaylistID {
    private String Musicid;
    private String Platform;
    private String Singerid;
    private String Singername;
    private String Albumname;
    private String Albumid;
    private String playlistid;

    public String getAlbumid() {
        return Albumid;
    }

    public String getAlbumname() {
        return Albumname;
    }

    public String getMusicid() {
        return Musicid;
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

    public String getPlaylistid() {
        return playlistid;
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

    public void setPlatform(String platform) {
        Platform = platform;
    }

    public void setSingerid(String singerid) {
        Singerid = singerid;
    }

    public void setSingername(String singername) {
        Singername = singername;
    }

    public void setPlaylistid(String playlistid) {
        this.playlistid = playlistid;
    }
}
