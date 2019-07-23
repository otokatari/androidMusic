package otokatari.com.otokatari.Model.s.RequestInfo;

public class CommentsShare {
    private String Content;
    private String Userid;
    private String ReplyTo;

    public String getUserid() {
        return Userid;
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

    public void setContent(String content) {
        Content = content;
    }

    public void setReplyTo(String replyTo) {
        ReplyTo = replyTo;
    }
}
