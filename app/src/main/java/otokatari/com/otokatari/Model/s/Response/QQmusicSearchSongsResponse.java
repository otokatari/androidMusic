package otokatari.com.otokatari.Model.s.Response;


public class QQmusicSearchSongsResponse {
    private String songName;
    private String singerName;
    private String songmid;
    private String songid;
    private String albummid;
    public QQmusicSearchSongsResponse(String songName,String singerName,String songmid,String songid,String albummid)
    {
        this.albummid=albummid;
        this.singerName=singerName;
        this.songid=songid;
        this.songName=songName;
        this.songmid=songmid;
    }

    public String getSongid() {
        return songid;
    }

    public void setSongid(String songid) {
        this.songid = songid;
    }

    public String getAlbumid() {
        return albummid;
    }

    public void setAlbumid(String albummid) {
        this.albummid = albummid;
    }

    public String getSingerName() {
        return singerName;
    }

    public void setSingerName(String singerName) {
        this.singerName = singerName;
    }

    public String getSongmid() {
        return songmid;
    }

    public void setSongmid(String songmid) {
        this.songmid = songmid;
    }

    public String getSongName() {
        return songName;
    }

    public void setSongName(String songName) {
        this.songName = songName;
    }
}
