package otokatari.com.otokatari.Tasks;

import otokatari.com.otokatari.User.APIDocs;

public class GetQQmusicDownloadAddress {
    public void sendRequestWithOkHttp(String songmid,String vkey) {
        String VariedUrl = APIDocs.fullQQmusicGetDownloadAddress;
        VariedUrl = VariedUrl + songmid+".m4a?guid=5448538077&vkey="+vkey+"&uin=0&fromtag=66";
    }
}
