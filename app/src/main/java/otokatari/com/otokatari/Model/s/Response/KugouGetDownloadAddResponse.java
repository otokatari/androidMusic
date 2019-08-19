package otokatari.com.otokatari.Model.s.Response;

public class KugouGetDownloadAddResponse {
    private String play_url;
    private String lyrics;
    private String img;
    private String author_name;
    private String song_name;
    public KugouGetDownloadAddResponse(String play_url,String lyrics,String img,String author_name,String song_name)
    {
        this.lyrics=lyrics;
        this.play_url=play_url;
        this.img=img;
        this.author_name=author_name;
        this.song_name=song_name;
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

    public String getAuthor_name() {
        return author_name;
    }

    public void setAuthor_name(String author_name) {
        this.author_name = author_name;
    }

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }

    public String getSong_name() {
        return song_name;
    }

    public void setSong_name(String song_name) {
        this.song_name = song_name;
    }
}
