package otokatari.com.otokatari.Model.s;

/*
 *  描述相关设置项的含义:
 * EnableNotification : 开关推送通知设置
 *
 * TakePhotoStoreLocation : App内拍摄照片的存储位置
 *    0 : 保存在系统相机存照片的目录
 *    1 : 保存在此App的data目录下
 *
 * */

public class AppSettings {
    private int EnableNotification = 1;
    private int TakePhotoStoreLocation = 0;
    private boolean HaveSeenUserGuide = false;

    public int getEnableNotification() {
        return EnableNotification;
    }

    public int getTakePhotoStoreLocation() {
        return TakePhotoStoreLocation;
    }

    public void setEnableNotification(int enableNotification) {
        EnableNotification = enableNotification;
    }

    public void setTakePhotoStoreLocation(int takePhotoStoreLocation) {
        TakePhotoStoreLocation = takePhotoStoreLocation;
    }
    public boolean getHaveSeenUserGuide() {
        return HaveSeenUserGuide;
    }
    public void setHaveSeenUserGuide(boolean val) {
        HaveSeenUserGuide = val;
    }
}