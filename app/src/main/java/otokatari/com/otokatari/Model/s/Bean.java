package otokatari.com.otokatari.Model.s;

public class Bean {
    private String songName;
    private String singer;

    public Bean(String title, String content) {
        this.songName = title;
        this.singer = content;
    }
    public String getTitle() {
        return songName;
    }

    public void setTitle(String title) {
        this.songName = title;
    }

    public String getContent() {
        return singer;
    }

    public void setContent(String content) {
        this.singer = content;
    }

}


