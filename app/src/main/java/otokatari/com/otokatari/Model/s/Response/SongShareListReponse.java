package otokatari.com.otokatari.Model.s.Response;

public class SongShareListReponse extends  CommonResponse {
    private OuterComments Comments;

    public OuterComments getComments() {
        return Comments;
    }

    public void setComments(OuterComments comments) {
        Comments = comments;
    }
}
