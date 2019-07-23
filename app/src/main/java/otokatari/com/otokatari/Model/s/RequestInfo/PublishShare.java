package otokatari.com.otokatari.Model.s.RequestInfo;

public class PublishShare {
    private String musicid;
    private PublishShareWithoutMusicid publishShareWithoutMusicid;

    public String getMusicid() {
        return musicid;
    }

    public PublishShareWithoutMusicid getPublishShareWithoutMusicid() {
        return publishShareWithoutMusicid;
    }

    public void setMusicid(String musicid) {
        this.musicid = musicid;
    }

    public void setPublishShareWithoutMusicid(PublishShareWithoutMusicid publishShareWithoutMusicid) {
        this.publishShareWithoutMusicid = publishShareWithoutMusicid;
    }
}
