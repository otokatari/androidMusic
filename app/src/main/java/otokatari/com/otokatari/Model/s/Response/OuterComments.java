package otokatari.com.otokatari.Model.s.Response;

public class OuterComments {
    private String _id;
    private String Musicid;
    private MyShareComment Comments;

    public String get_id() {
        return _id;
    }

    public String getMusicid() {
        return Musicid;
    }

    public MyShareComment getComments() {
        return Comments;
    }

    public void setComments(MyShareComment comments) {
        Comments = comments;
    }

    public void set_id(String _id) {
        this._id = _id;
    }

    public void setMusicid(String musicid) {
        Musicid = musicid;
    }
}
