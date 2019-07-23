package otokatari.com.otokatari.Model.s.RequestInfo;

public class CommentsShareWithID {
    private String musicid;
    private String commentid;
    private CommentsShare commentsShare;

    public String getMusicid() {
        return musicid;
    }

    public CommentsShare getCommentsShare() {
        return commentsShare;
    }

    public String getCommentid() {
        return commentid;
    }

    public void setMusicid(String musicid) {
        this.musicid = musicid;
    }

    public void setCommentid(String commentid) {
        this.commentid = commentid;
    }

    public void setCommentsShare(CommentsShare commentsShare) {
        this.commentsShare = commentsShare;
    }
}
