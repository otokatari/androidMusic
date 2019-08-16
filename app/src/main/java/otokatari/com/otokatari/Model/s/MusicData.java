package otokatari.com.otokatari.Model.s;

import java.io.Serializable;

public class MusicData implements Serializable {
    /*音乐资源id*/
    private String mFileName;
    /*专辑图片id*/
    private int mMusicPicRes;
    /*音乐名称*/
    private String mMusicName;
    /*作者*/
    private String mMusicAuthor;

    public MusicData(String mFileName, int mMusicPicRes, String mMusicName, String mMusicAuthor) {
        this.mFileName = mFileName;
        this.mMusicPicRes = mMusicPicRes;
        this.mMusicName = mMusicName;
        this.mMusicAuthor = mMusicAuthor;
    }

    public String getFileName() {
        return mFileName;
    }

    public int getMusicPicRes() {
        return mMusicPicRes;
    }

    public String getMusicName() {
        return mMusicName;
    }

    public String getMusicAuthor() {
        return mMusicAuthor;
    }
}
