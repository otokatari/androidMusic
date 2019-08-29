package otokatari.com.otokatari.Activity;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import otokatari.com.otokatari.Adapter.SearchAdapter;
import otokatari.com.otokatari.Model.Player.LyricInfo;
import otokatari.com.otokatari.Model.Player.MusicInfo;
import otokatari.com.otokatari.Model.s.Bean;
import otokatari.com.otokatari.R;
import otokatari.com.otokatari.Service.Common.ActivityCollector;
import otokatari.com.otokatari.Tasks.*;
import otokatari.com.otokatari.User.APIDocs;
import otokatari.com.otokatari.Utils.AppUtils;
import otokatari.com.otokatari.View.SearchView;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

import static otokatari.com.otokatari.Utils.LyricParserImpl.ParseLyric;

import android.util.Base64;

public class SearchMusicActivity extends Activity implements SearchView.SearchViewListener {

    /**
     * 搜索结果列表view
     */
    private ListView lvResults;

    /**
     * 搜索view
     */
    private SearchView searchView;


    /**
     * 热搜框列表adapter
     */
    private ArrayAdapter<String> hintAdapter;

    /**
     * 自动补全列表adapter
     */
    private ArrayAdapter<String> autoCompleteAdapter;

    /**
     * 搜索结果列表adapter
     */
    private SearchAdapter resultAdapter;

    /**
     * 数据库数据，总数据
     */
    private List<Bean> dbData;

    /**
     * 热搜版数据
     */
    private List<String> hintData;

    /**
     * 搜索过程中自动补全数据
     */
    private List<String> autoCompleteData;

    /**
     * 搜索结果的数据
     */
    public static List<Bean> resultData;


    /**
     * 默认提示框显示项的个数
     */
    private static int DEFAULT_HINT_SIZE = 4;

    /**
     * 提示框显示项的个数
     */
    private static int hintSize = DEFAULT_HINT_SIZE;


    private static String song_name;
    private static String author_name;
    private static LyricInfo lyric;


    //Netease Info
    private static String id;
    private static String picUrl;

    //QQmusic Info
    private static String vkey;
    private static String songmid;
    private static String songid;
    //private static String filePath=AppUtils.GetAppCachePath()+"/"+"image.webp";
    private static String albummid;



    /**
     * 设置提示框显示项的个数
     */
    public static void setHintSize(int hintSize) {
        SearchMusicActivity.hintSize = hintSize;
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_search_music);
        ActivityCollector.addAvtivity(this);
        initViews();
        initData();
    }

    private void initViews() {
        lvResults = findViewById(R.id.main_lv_search_results);
        searchView = (SearchView) findViewById(R.id.search_text);
        //设置监听
        searchView.setSearchViewListener(this);
        //设置adapter
        searchView.setTipsHintAdapter(hintAdapter);
        searchView.setAutoCompleteAdapter(autoCompleteAdapter);


        lvResults.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {
                Bean bean = resultData.get(position);
                song_name = bean.getTitle();
                author_name = bean.getContent();
                songmid = bean.getFileHash();
                songid = bean.getSongid();
                albummid =bean.getAlbumid();
                searchDownloadAddress(songid);

            }
        });
    }

    /**
     * 初始化数据
     */
    private void initData() {
        //从数据库获取数据
        getDbData();
        //初始化热搜版数据
        getHintData();
        //初始化自动补全数据
        getAutoCompleteData(null);
        //初始化搜索结果数据
        getResultData();
    }

    /**
     * 获取db 数据
     */
    private void getDbData() {
        int size = 50;
        dbData = new ArrayList<>(size);
    }

    /**
     * 获取热搜版data 和adapter
     */
    private void getHintData() {
        hintData = new ArrayList<>(hintSize);
        for (int i = 1; i <= hintSize; i++) {
            // hintData.add("热搜版" + i + "：Android自定义View");
        }
        hintAdapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, hintData);
    }

    /**
     * 获取自动补全data 和adapter
     */
    private void getAutoCompleteData(String text) {
        if (autoCompleteData == null) {
            //初始化
            autoCompleteData = new ArrayList<>(hintSize);
        } else {
            // 根据text 获取auto data
            autoCompleteData.clear();
            for (int i = 0, count = 0; i < dbData.size()
                    && count < hintSize; i++) {
                if (dbData.get(i).getTitle().contains(text.trim())) {
                    autoCompleteData.add(dbData.get(i).getTitle());
                    count++;
                }
            }
        }
        if (autoCompleteAdapter == null) {
            autoCompleteAdapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, autoCompleteData);
        } else {
            autoCompleteAdapter.notifyDataSetChanged();
        }
    }

    /**
     * 获取搜索结果data和adapter
     */
    private void getResultData() {
        if (resultData == null) {
            // 初始化
            resultData = new ArrayList<>();
        }
        if (resultAdapter == null) {
            resultAdapter = new SearchAdapter(this, resultData, R.layout.item_bean_list);
            lvResults.setAdapter(resultAdapter);
        } else {
            resultAdapter.notifyDataSetChanged();
        }
    }

    /**
     * 当搜索框文本改变时触发的回调 ,更新自动补全数据
     *
     * @param text
     */
    @Override
    public void onRefreshAutoComplete(String text) {
        //更新数据
        getAutoCompleteData(text);
    }

    /**
     * 点击搜索键时edit text触发的回调
     *
     * @param text
     */
    @Override
    public void onSearch(String text) {

        //KugouSearch(text);
        //NeteaseSearch(text);
        QQmusicSearch(text);

        if (lvResults.getVisibility() == View.GONE) {
            lvResults.setVisibility(View.VISIBLE);
        }
    }


//    public void KugouSearch(String text) {
//        new KugouSearchSongsTask(TaskRet -> {
//            if (TaskRet != null) {
//                resultData.clear();
//                for (int i = 0; i < TaskRet.size(); i++) {
//                    resultData.add(new Bean(TaskRet.get(i).getSongName(), TaskRet.get(i).getFileName(), TaskRet.get(i).getFileHash()));
//                }
//                getResultData();
//            } else
//                Log.d("SearchMusicActivity", "gg");
//        }).execute(text);
//    }

    public void QQmusicSearch(String text) {
        new QQmusicSearchSongsTask(TaskRet -> {
            if (TaskRet != null) {
                resultData.clear();
                for (int i = 0; i < TaskRet.size(); i++) {
                    resultData.add(new Bean(TaskRet.get(i).getSongName(),TaskRet.get(i).getSingerName(),TaskRet.get(i).getSongmid(),
                            TaskRet.get(i).getSongid(),TaskRet.get(i).getAlbumid()));
                }
                getResultData();
            } else
                Log.d("SearchMusicActivity", "QQmusic搜索失败");
        }).execute(text);
    }

    public void NeteaseSearch(String text) {
        new NeteaseSearchSongsTask(TaskRet -> {
            if (TaskRet != null) {
                resultData.clear();
                for (int i = 0; i < TaskRet.size(); i++) {
                    resultData.add(new Bean(TaskRet.get(i).getSong_name(), TaskRet.get(i).getAuthor_name(), TaskRet.get(i).getId(), null,null));
                }
                getResultData();
            } else
                Log.d("SearchMusicActivity", "网易云搜索失败");
        }).execute(text);
    }

    public void searchDownloadAddress(String HashFile) {

        //KugouSearchLoadAddress(HashFile);
        //NeteaseSearchLoadAddress(HashFile);
        QQmusicSearchLoadAddress(HashFile);

    }

    public void KugouSearchLoadAddress(String HashFile) {
        new GetKugouDownloadAddressTask(TaskRet -> {
            if (TaskRet != null) {
                String play_url = TaskRet.getPlay_url();
                String img = TaskRet.getImg();
                LyricInfo lyric = ParseLyric(TaskRet.getLyrics());
                String author_name = TaskRet.getAuthor_name();
                String song_name = TaskRet.getSong_name();
                Intent intent = new Intent(SearchMusicActivity.this, PlayerActivityDefaultImpl.class);
                MusicInfo<String> musicInfo = new MusicInfo<>(song_name, author_name, img, lyric, play_url, false, String.class);
                intent.putExtra("MusicInfo", musicInfo);
                startActivity(intent);

            } else
                Log.d("SearchMusicActivity", "获取不到酷狗音乐的下载地址");
        }).execute(HashFile);
    }

    public void QQmusicSearchLoadAddress(String songid) {

        GetQQmusicLyric(songmid);
        GetQQmusicImg(albummid, songid);
        GetQQmusicVkey(songmid);
    }

    public void GetQQmusicLyric(String songmid) {
        new GetQQmusicLyricTask(TaskRet -> {
            if (TaskRet != null) {
                String str = new String(Base64.decode(TaskRet.getBytes(), Base64.DEFAULT));
                lyric = ParseLyric(str);
            } else
                Log.d("SearchMusicActivity", "获取不到QQ音乐歌词");
        }).execute(songmid);
    }

    public void GetQQmusicVkey(String songmid) {
        new GetQQmusicVkeyTask(TaskRet -> {
            if (TaskRet != null) {
                vkey = TaskRet;
                GetQQmusicDownloadStream(songmid, vkey);
            } else {
                Log.d("SearchMusicActivity", "获取不到QQmusic的Vkey");
            }
        }).execute(songmid);
    }

    public void GetQQmusicImg(String albummid, String songmid) {
        new GetQQmusicImgTask(TaskRet -> {
            if (TaskRet != null) {
               QQmusicWriteToLocal(TaskRet);

            } else {
                Log.d("SearchMusicActivity", "获取不到QQmusic的图片");
            }
        }).execute(albummid, songmid);

    }

    public void GetQQmusicDownloadStream(String songmid, String vkey) {
        String VariedUrl = APIDocs.fullQQmusicGetDownloadAddress + songmid + ".m4a?guid=5448538077&vkey=" + vkey
                + "&uin=0&fromtag=66";
        MusicInfo<String> musicInfo = new MusicInfo<>(song_name, author_name, picUrl, lyric, VariedUrl, false, String.class);
        startActivity(new Intent(SearchMusicActivity.this, PlayerActivityDefaultImpl.class).putExtra("MusicInfo", musicInfo));
    }

    public void QQmusicWriteToLocal(InputStream inputStream) {
        new Thread(() -> {
            try {
                writeToLocal(inputStream);
            } catch (IOException e) {
                e.printStackTrace();
            }

        }).start();
    }
//    public static void readFileContent(String fileName) {
//        File file = new File(fileName);
//        BufferedReader reader = null;
//        StringBuffer sbf = new StringBuffer();
//        try {
//            reader = new BufferedReader(new FileReader(file));
//            String tempStr;
//            while ((tempStr = reader.readLine()) != null) {
//                sbf.append(tempStr);
//            }
//            reader.close();
//            picUrl=sbf.toString();
//        } catch (IOException e) {
//            e.printStackTrace();
//        } finally {
//            if (reader != null) {
//                try {
//                    reader.close();
//                } catch (IOException e1) {
//                    e1.printStackTrace();
//                }
//            }
//        }
//       picUrl=sbf.toString();
//    }

    private static void writeToLocal(InputStream input) throws IOException {
        int index;
        byte[] bytes = new byte[1024];
        FileOutputStream downloadFile = new FileOutputStream(AppUtils.GetAppCachePath()+"/"+songid+"image.webp");
        picUrl=AppUtils.GetAppCachePath()+"/"+songid+"image.webp";
        while ((index = input.read(bytes)) >= 0) {
            downloadFile.write(bytes, 0, index);
            downloadFile.flush();
        }
        downloadFile.close();
        input.close();
    }

    public void NeteaseSearchLoadAddress(String id) {
        GetNeteaseLyric(id);
        GetNeteaseImg(id);
        GetNeteaseDownloadAddress(id);
    }

    public void GetNeteaseLyric(String id) {
        new GetNeteaseLyricTask(TaskRet -> {
            if (TaskRet != null) {
                lyric = ParseLyric(TaskRet);
            } else
                Log.d("SearchMusicActivity", "获取不到网易云歌词");
        }).execute(id);
    }

    public void GetNeteaseImg(String id) {
        new GetNeteaseImgTask(TaskRet -> {
            if (TaskRet != null) {
                picUrl = TaskRet;
            } else
                Log.d("SearchMusicActivity", "获取不到网易云专辑图片");
        }).execute(id);
    }

    public void GetNeteaseDownloadAddress(String id) {
        new GetNeteaseDownloadURLTask(TaskRet -> {
            if (TaskRet != null) {
                String play_url = TaskRet;
                Intent intent = new Intent(SearchMusicActivity.this, PlayerActivityDefaultImpl.class);
                MusicInfo<String> musicInfo = new MusicInfo<>(song_name, author_name, picUrl, lyric, play_url, false, String.class);
                intent.putExtra("MusicInfo", musicInfo);
                startActivity(intent);
            } else
                Log.d("SearchMusicActivity", "获取不到网易云下载地址");
        }).execute(id);
    }
}

