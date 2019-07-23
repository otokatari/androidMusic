package otokatari.com.otokatari.Model.s.RequestInfo;

public class PublishShareWithoutMusicid{
        private String Content;
        private String Userid;
        private String[] Photo;
        private String[] SharingLyric;

    public String getContent() {
        return Content;
    }

    public String getUserid() {
        return Userid;
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

    public void setUserid(String userid) {
        Userid = userid;
    }

    public void setPhoto(String[] photo) {
        Photo = photo;
    }

    public void setSharingLyric(String[] sharingLyric) {
        SharingLyric = sharingLyric;
    }
}
