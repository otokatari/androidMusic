package otokatari.com.otokatari.Model.s.RequestInfo;

import java.util.List;

public class Playlist {
    private String _id;
    private String Userid;
    private boolean Favourite;
    private String Name;
    private int CreateTime;
    private List<Songs> songs;

    public String getUserid() {
        return Userid;
    }

    public String getName() {
        return Name;
    }

    public int getCreateTime() {
        return CreateTime;
    }

    public List<Songs> getSongs() {
        return songs;
    }

    public String get_id() {
        return _id;
    }

    public boolean getFavourite()
    {
        return Favourite;
    }

    public void setUserid(String userid) {
        Userid = userid;
    }

    public void setName(String name) {
        Name = name;
    }

    public void set_id(String _id) {
        this._id = _id;
    }

    public void setCreateTime(int createTime) {
        CreateTime = createTime;
    }

    public void setFavourite(boolean favourite) {
        Favourite = favourite;
    }

    public void setSongs(List<Songs> songs) {
        this.songs = songs;
    }
}
