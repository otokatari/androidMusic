package otokatari.com.otokatari.Model.s;

public class Bean {
    private String songName;
    private String singer;
    private String FileHash;
    private String songid;

    public Bean(String title, String content,String FileHash,String songid) {
        this.songName = title;
        this.singer = content;
        this.FileHash=FileHash;
        this.songid=songid;
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

    public String getSongid() {
        return singer;
    }

    public void setSongid(String singer) {
        this.singer = singer;
    }
}


