package otokatari.com.otokatari.Model.s.Response;

import java.util.List;

public class MyShareComment {
    private String _id;
    private String Content;
    private int Time;
    private String[] Like;
    private String Userid;
    private String[] Photo;
    private List<replyShareComment> Comments;
    private String[] SharingLyric;

    public String getContent() {
        return Content;
    }

    public int getTime() {
        return Time;
    }

    public String get_id() {
        return _id;
    }

    public String getUserid() {
        return Userid;
    }

    public List<replyShareComment> getComments() {
        return Comments;
    }

    public String[] getLike() {
        return Like;
    }

    public String[] getPhoto() {
        return Photo;
    }

    public String[] getSharingLyric() {
        return SharingLyric;
    }

    public void setContent(String content) {
        Content = content;
    }

    public void setTime(int time) {
        Time = time;
    }

    public void set_id(String _id) {
        this._id = _id;
    }

    public void setUserid(String userid) {
        Userid = userid;
    }

    public void setComments(List<replyShareComment> comments) {
        Comments = comments;
    }

    public void setLike(String[] like) {
        Like = like;
    }

    public void setPhoto(String[] photo) {
        Photo = photo;
    }

    public void setSharingLyric(String[] sharingLyric) {
        SharingLyric = sharingLyric;
    }
}
