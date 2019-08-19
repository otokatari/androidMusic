package otokatari.com.otokatari.Model.s.Response;

public class KugouGetDownloadAddResponse {
    private String play_url;
    private String lyrics;
    public KugouGetDownloadAddResponse(String play_url,String lyrics)
    {
        this.lyrics=lyrics;
        this.play_url=play_url;
    }

    public String getLyrics() {
        return lyrics;
    }

    public void setLyrics(String lyrics) {
        this.lyrics = lyrics;
    }

    public void setPlay_url(String play_url) {
        this.play_url = play_url;
    }

    public String getPlay_url() {
        return play_url;
    }
}
