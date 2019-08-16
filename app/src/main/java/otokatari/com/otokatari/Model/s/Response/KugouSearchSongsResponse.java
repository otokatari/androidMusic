package otokatari.com.otokatari.Model.s.Response;

public class KugouSearchSongsResponse {
    private String SongName;
    private String FileHash;
    private String FileName;

   public KugouSearchSongsResponse(String SongName,String FileHash,String FileName)
    {
        this.SongName=SongName;
        this.FileHash=FileHash;
        this.SongName=FileName;
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
}
