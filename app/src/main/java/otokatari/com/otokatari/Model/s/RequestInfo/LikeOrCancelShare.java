package otokatari.com.otokatari.Model.s.RequestInfo;

public class LikeOrCancelShare {
    private boolean like;
    private String musicid;
    private String commentid;

    public String getCommentid() {
        return commentid;
    }

    public String getMusicid() {
        return musicid;
    }

    public boolean getLike()
    {
        return like;
    }

    public void setCommentid(String commentid) {
        this.commentid = commentid;
    }

    public void setMusicid(String musicid) {
        this.musicid = musicid;
    }

    public void setLike(boolean like) {
        this.like = like;
    }
}
