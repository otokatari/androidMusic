package otokatari.com.otokatari.Adapter;

import android.content.Context;
import otokatari.com.otokatari.Model.s.Bean;
import otokatari.com.otokatari.R;
import otokatari.com.otokatari.View.ViewHolder;
import java.util.List;

public class SearchAdapter extends CommonAdapter<Bean> {

    public SearchAdapter(Context context, List<Bean> data, int layoutId) {
        super(context, data, layoutId);
    }

    @Override
    public void convert(ViewHolder holder, int position) {
        holder.setText(R.id.item_search_tv_title,mData.get(position).getTitle())
                .setText(R.id.item_search_tv_content,mData.get(position).getContent());
    }
}
