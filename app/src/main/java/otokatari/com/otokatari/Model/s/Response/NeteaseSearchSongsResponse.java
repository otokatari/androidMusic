package otokatari.com.otokatari.Model.s.Response;

public class NeteaseSearchSongsResponse {
    private String id;
    private String song_name;
    private String author_name;

    public NeteaseSearchSongsResponse(String id,String song_name,String author_name)
    {
        this.author_name=author_name;
        this.id=id;
        this.song_name=song_name;
    }
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getSong_name() {
        return song_name;
    }

    public void setSong_name(String song_name) {
        this.song_name = song_name;
    }

    public String getAuthor_name() {
        return author_name;
    }

    public void setAuthor_name(String author_name) {
        this.author_name = author_name;
    }
}
