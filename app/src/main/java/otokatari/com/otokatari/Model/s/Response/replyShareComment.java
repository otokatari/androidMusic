package otokatari.com.otokatari.Model.s.Response;

public class replyShareComment {
    private String _id;
    private String Content;
    private int Time;
    private String Userid;
    private String ReplyTo;

    public String getUserid() {
        return Userid;
    }

    public String get_id() {
        return _id;
    }

    public int getTime() {
        return Time;
    }

    public String getContent() {
        return Content;
    }

    public String getReplyTo() {
        return ReplyTo;
    }

    public void setUserid(String userid) {
        Userid = userid;
    }

    public void set_id(String _id) {
        this._id = _id;
    }

    public void setTime(int time) {
        Time = time;
    }

    public void setContent(String content) {
        Content = content;
    }

    public void setReplyTo(String replyTo) {
        ReplyTo = replyTo;
    }
}
