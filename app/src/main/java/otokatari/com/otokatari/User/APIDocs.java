package otokatari.com.otokatari.User;

public class APIDocs {
    public static final String DeploymentAddress = "http://110.64.88.125:8090";
    public static final String login="/user/auth/login";
    public static final String register="/user/auth/register";
    public static final String getAccountInfo="/user/profile/getprofile?userid=";
    public static final String updateMusicLibrary="/music/listening";

    public static final String fullLogin=DeploymentAddress+login;
    public static final String fullRegister=DeploymentAddress+register;
    public static final String fullGetAccountInfo=DeploymentAddress+getAccountInfo;
    public static final String fullUpdateMusicLibrary=DeploymentAddress+updateMusicLibrary;




}
