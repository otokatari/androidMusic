package otokatari.com.otokatari.Model.s.Response;

public class KugouSearchSongsResponse {
    private String SongName;
    private String FileHash;
    private String FileName;
    private String songid;

   public KugouSearchSongsResponse(String SongName, String FileHash, String FileName, String songid)
    {
        this.SongName=SongName;
        this.FileHash=FileHash;
        this.FileName=FileName;
        this.songid=songid;
    }

    public String getFileHash() {
        return FileHash;
    }

    public void setFileHash(String fileHash) {
        FileHash = fileHash;
    }

    public String getFileName() {
        return FileName;
    }

    public void setFileName(String fileName) {
        FileName = fileName;
    }

    public String getSongName() {
        return SongName;
    }

    public void setSongName(String songName) {
        this.SongName = songName;
    }

    public String getSongid() {
        return songid;
    }

    public void setSongid(String songid) {
        this.songid = songid;
    }
}
