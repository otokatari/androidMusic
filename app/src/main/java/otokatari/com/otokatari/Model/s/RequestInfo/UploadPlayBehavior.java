package otokatari.com.otokatari.Model.s.RequestInfo;

public class UploadPlayBehavior {
    private String Userid;
    private String Behaviour;
    private int Time;
    private SimpleMusic Music;
    private boolean Isinplaylist;

    public int getTime() {
        return Time;
    }

    public SimpleMusic getMusic() {
        return Music;
    }

    public String getBehaviour() {
        return Behaviour;
    }

    public String getUserid() {
        return Userid;
    }

    public boolean getIsinplaylist()
    {
        return Isinplaylist;
    }

    public void setBehaviour(String behaviour) {
        Behaviour = behaviour;
    }

    public void setIsinplaylist(boolean isinplaylist) {
        Isinplaylist = isinplaylist;
    }

    public void setTime(int time) {
        Time = time;
    }

    public void setMusic(SimpleMusic music) {
        Music = music;
    }

    public void setUserid(String userid) {
        Userid = userid;
    }
}
