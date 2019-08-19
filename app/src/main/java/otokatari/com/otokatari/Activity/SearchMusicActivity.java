package otokatari.com.otokatari.Activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;
import otokatari.com.otokatari.Adapter.SearchAdapter;
import otokatari.com.otokatari.Model.s.Bean;
import otokatari.com.otokatari.Model.s.Response.KugouGetDownloadAddResponse;
import otokatari.com.otokatari.R;
import otokatari.com.otokatari.Tasks.GetKugouDownloadAddressTask;
import otokatari.com.otokatari.Tasks.KugouSearchSongsTask;
import otokatari.com.otokatari.View.SearchView;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

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
                Bean bean=resultData.get(position);
                //Toast.makeText(SearchMusicActivity.this, bean.getFileHash(), Toast.LENGTH_SHORT).show();
                searchDownloadAddress(bean.getFileHash());

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
        int size = 20;
        dbData = new ArrayList<>(size);
        for (int i = 0; i < size; i++) {

           // dbData.add(new Bean("给我一个理由忘记" + (i + 1), "梁静茹" + (i + 1)));
        }
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
        new KugouSearchSongsTask(TaskRet -> {
            if (TaskRet != null) {
                resultData.clear();
                for (int i = 0; i < TaskRet.size(); i++) {
                    resultData.add(new Bean(TaskRet.get(i).getSongName(), TaskRet.get(i).getFileName(),TaskRet.get(i).getFileHash()));
                }
                getResultData();
            } else
                Log.d("SearchMusicActivity", "gg");
        }).execute(text);
        if(lvResults.getVisibility() == View.GONE){
            lvResults.setVisibility(View.VISIBLE);
        }
    }

    public void searchDownloadAddress(String HashFile)
    {
        new GetKugouDownloadAddressTask(TaskRet -> {
            if (TaskRet != null) {
                String play_url=TaskRet.getPlay_url();
                String img=TaskRet.getImg();
                String author_name=TaskRet.getAuthor_name();
                String song_name=TaskRet.getSong_name();
                Intent intent=new Intent(SearchMusicActivity.this,playUIActivity.class);
                intent.putExtra("play_url",play_url);
                intent.putExtra("song_name",song_name);
                intent.putExtra("img",img);
                intent.putExtra("author_name",author_name);
                startActivity(intent);
            } else
                Log.d("SearchMusicActivity", "gg");
        }).execute(HashFile);
    }
}

