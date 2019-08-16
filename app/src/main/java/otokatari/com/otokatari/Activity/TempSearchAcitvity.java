package otokatari.com.otokatari.Activity;

import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import otokatari.com.otokatari.Adapter.SearchResultAdapter;
import otokatari.com.otokatari.R;
import android.os.Bundle;
import otokatari.com.otokatari.View.SearchView;


public class TempSearchAcitvity extends AppCompatActivity {

    private RecyclerView searchResultList;
    private SearchView searchKeyWord;
    private SearchResultAdapter searchResultAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_temp_search);
        init();
    }

    private void init(){

        searchResultList = findViewById(R.id.search_result_list);
        searchKeyWord = findViewById(R.id.search_text);
        searchResultList.setLayoutManager(new LinearLayoutManager(this));
        searchResultList.setAdapter(searchResultAdapter = new SearchResultAdapter());
//        String searchContent=searchKeyWord.etInput.getText().toString();
//        startSearch(searchContent);

    }

//    private void startSearch(String content)
//    {
//
//        updateSearchResult(KugouSearchSongsTask.sendRequestWithOkHttp(content));
//    }

    private void updateSearchResult(String data){

        //测试更新列表
        if(data.length() <= 0)
            searchResultAdapter.clearItems();
        else
            searchResultAdapter.addNewItem(data);
    }
}
