package com.androidapp.len.doubanmusicsonger.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.androidapp.len.doubanmusicsonger.R;

import java.util.List;
import java.util.Map;

/**
 * Created by len on 16/12/2.
 */

public class DB_TabThirdItemAdapter extends BaseAdapter {
    private List<Map<String, String>> listArr;
    private LayoutInflater inflater;

    public DB_TabThirdItemAdapter(Context activity, List<Map<String, String>> listArr) {
        inflater = LayoutInflater.from(activity);
        this.listArr = listArr;
    }

    @Override
    public int getCount() {
        return listArr.size();
    }

    @Override
    public Object getItem(int i) {
        return getItemId(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        DB_ItemView itemView = null;
        if (view == null) {
            view = inflater.inflate(R.layout.db_listitem_tabthirditem, null);
            itemView = new DB_ItemView();
            itemView.titleTV = (TextView) view.findViewById(R.id.itemtitle_tv);
            itemView.infoTV = (TextView) view.findViewById(R.id.iteminfo_tv);
            view.setTag(itemView);
        } else {
            itemView = (DB_ItemView) view.getTag();
        }
        Map<String,String> map = listArr.get(i);
        itemView.titleTV.setText(map.get("title"));
        itemView.infoTV.setText(map.get("info"));
        return view;
    }
}
class  DB_ItemView{
    TextView titleTV;
    TextView infoTV;
}