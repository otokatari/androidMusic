package otokatari.com.otokatari.Adapter;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import otokatari.com.otokatari.Activity.SearchMusicActivity;
import otokatari.com.otokatari.Activity.TempPlayMusicActivity;
import otokatari.com.otokatari.Activity.TempSearchAcitvity;
import otokatari.com.otokatari.R;

import java.util.ArrayList;
import java.util.List;

public class SearchResultAdapter extends RecyclerView.Adapter<SearchResultAdapter.SearchResultViewHolder> {

    private List<String> data;
    private Context parentActivityContex;
    public void updateAdapter(List<String> data){
        this.data = data;
    }

    public SearchResultAdapter(){

    }
    public void addNewItem(String itemTitle) {
        if(data == null) {
            data = new ArrayList<String>();
        }
        data.add(0, itemTitle);
        notifyItemInserted(0);
    }

    public void deleteItem() {
        if(data == null || data.isEmpty()) {
            return;
        }
        data.remove(0);
        notifyItemRemoved(0);
    }

    public void clearItems(){
        if(data == null || data.isEmpty()) {
            return;
        }
        data.clear();
        notifyDataSetChanged();
    }
    @Override
    public SearchResultViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        if (parent != null) {
            parent.removeAllViews();
        }

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.recycleviewtemp_search_single_item,parent , false);
        return new SearchResultViewHolder(view, parent.getContext());
    }

    @Override
    public void onBindViewHolder(SearchResultViewHolder holder, int position) {
        holder.title.setText(data.get(position));
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(parentActivityContex, TempPlayMusicActivity.class);
                parentActivityContex.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        if(data != null){
            return data.size();
        }
        return 0;
    }

    public class SearchResultViewHolder extends RecyclerView.ViewHolder{
        public TextView title;
        public SearchResultViewHolder(View itemView, Context context){
            super(itemView);
            title = (TextView)itemView.findViewById(R.id.music_title);
            parentActivityContex = context;
        }
    }
}
