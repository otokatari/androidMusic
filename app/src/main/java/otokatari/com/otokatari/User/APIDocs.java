package otokatari.com.otokatari.User;

public class APIDocs {
    public static final String DeploymentAddress = "http://129.204.223.158/backend";
    public static final String ThreeMusicDeploymentAddress="http://129.204.223.158";

    public static final String login="/user/auth/login";
    public static final String logout="/user/auth/logout";
    public static final String register="/user/auth/register";
    public static final String userInfoDetails="/user/profile/getprofile?userid=";
    public static final String getProfilelist="/user/profile/getprofilelist";
    public static final String updateUserInfo="/user/profile/setprofile?userid=";
    public static final String uploadPlayBehavior="/music/tracking/behaviour";
    public static final String queryPlaylist="/music/playlist/querylist?userid=";
    public static final String addMusicToPlaylist="/music/playlist/addsong?playlistid=";
    public static final String deleteMusicInPlaylist="/music/playlist/deletesong?";
    public static final String deletePlaylist="/music/playlist/deletelist?playlistid=";
    public static final String createPlaylist="/music/playlist/createlist";
    public static final String getUserHeadphoto="/user/profile/getavatar?avatar=";
    public static final String updateUserHeadphoto="/user/profile/changeavatar";
    public static final String getProfilePrivacy="/user/profile/profileprivacy?userid=";
    public static final String getProfilePrivacyList="/user/profile/profileprivacylist";
    public static final String getSongSharelist="/music/sharing/getcomments?musicid=";
    public static final String publishShare="/music/sharing/postcomment?musicid=";
    public static final String commentsShare="/music/sharing/replycomment?musicid=";
    public static final String likeOrCancelShare="/music/sharing/likecomment?like?=";

    public static final String neteaseSearchSongs="/netease/search?keywords=";
    public static final String neteaseGetDownloadAddress="/netease/song/url?id=";
    public static final String kugouSearchSongs="/kugou/search?keywords=";
    public static final String kugouGetDownloadAddress="/kugou/songurl?hash=";
    public static final String qqmusicSearchSongs="/qqmusic/search?query=";
    public static final String getQQmusicVkey="/qqmusic/song/vkey?songmid=";
    public static final String qqmusicGetDownloadAddress="http://isure.stream.qqmusic.qq.com/C400";

    public static final String fullLogin=DeploymentAddress+login;
    public static final String fullLogout=DeploymentAddress+logout;
    public static final String fullRegister=DeploymentAddress+register;
    public static final String fullUserInfoDetails=DeploymentAddress+userInfoDetails;
    public static final String fullGetProfilelist=DeploymentAddress+getProfilelist;
    public static final String fullUpdateUserInfo=DeploymentAddress+updateUserInfo;
    public static final String fullUploadPlayBehavior=DeploymentAddress+uploadPlayBehavior;
    public static final String fullQueryPlaylist=DeploymentAddress+queryPlaylist;
    public static final String fullAddMusicToPlaylist=DeploymentAddress+addMusicToPlaylist;
    public static final String fullDeleteMusicInPlaylist=DeploymentAddress+deleteMusicInPlaylist;
    public static final String fullDeletePlaylist=DeploymentAddress+deletePlaylist;
    public static final String fullCreatePlaylist=DeploymentAddress+createPlaylist;
    public static final String fullGetUserHeadphoto=DeploymentAddress+getUserHeadphoto;
    public static final String fullUpdateUserHeadphoto=DeploymentAddress+updateUserHeadphoto;
    public static final String fullProfilePrivacy=DeploymentAddress+getProfilePrivacy;
    public static final String fullGetProfilePrivacyList=DeploymentAddress+getProfilePrivacyList;
    public static final String fullGetSongSharelist=DeploymentAddress+getSongSharelist;
    public static final String fullPublishShare=DeploymentAddress+publishShare;
    public static final String fullCommentShare=DeploymentAddress+commentsShare;
    public static final String fullLikeOrCancelShare=DeploymentAddress+likeOrCancelShare;

    public static final String fullNeteaseSearchSongs=ThreeMusicDeploymentAddress+neteaseSearchSongs;
    public static final String fullNeteaseGetDownloadAddress=ThreeMusicDeploymentAddress+neteaseGetDownloadAddress;
    public static final String fullKugouSearchSongs=ThreeMusicDeploymentAddress+kugouSearchSongs;
    public static final String fullKugouGetDownloadAddress=ThreeMusicDeploymentAddress+kugouGetDownloadAddress;
    public static final String fullQQmusicSearchSongs=ThreeMusicDeploymentAddress+qqmusicSearchSongs;
    public static final String fullGetQQmusicVkey=ThreeMusicDeploymentAddress+getQQmusicVkey;
    public static final String fullQQmusicGetDownloadAddress=ThreeMusicDeploymentAddress+qqmusicGetDownloadAddress;







}
