package otokatari.com.otokatari.Model.s;

public class Bean {
    private String songName;
    private String singer;
    private String FileHash;

    public Bean(String title, String content,String FileHash) {
        this.songName = title;
        this.singer = content;
        this.FileHash=FileHash;
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

    public String getFileHash() {
        return FileHash;
    }

    public void setFileHash(String fileHash) {
        FileHash = fileHash;
    }
}


