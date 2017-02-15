package com.androidapp.len.doubanmusicsonger.Adapter;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.androidapp.len.doubanmusicsonger.R;

import java.util.List;
import java.util.Map;

/**
 * Created by len on 16/11/30.
 */

public class DB_HotSongerAdapter extends BaseAdapter {
    private LayoutInflater inflater;
    private List<Map<String, Object>> listArr;

    public DB_HotSongerAdapter() {
        // TODO Auto-generated constructor stub
    }
    public DB_HotSongerAdapter(Context context,List<Map<String, Object>>listArr){
        this.inflater = LayoutInflater.from(context);
        this.listArr = listArr;
    }
    @Override
    public int getCount() {
        // TODO Auto-generated method stub
        return this.listArr.size();
    }

    @Override
    public Object getItem(int position) {
        // TODO Auto-generated method stub
        return getItem(position);
    }

    @Override
    public long getItemId(int position) {
        // TODO Auto-generated method stub
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        // TODO Auto-generated method stub
        DB_HotSongerView homeView = null;
        if (convertView == null) {
            homeView = new DB_HotSongerView();
            convertView = inflater.inflate(R.layout.db_listitem_hotsonger, null);
            homeView.photo = (ImageView) convertView.findViewById(R.id.hotsongphoto);
            homeView.songer = (TextView) convertView.findViewById(R.id.hotsongname);
            homeView.categary = (TextView) convertView.findViewById(R.id.hotsongcate);
            homeView.fav = (TextView) convertView.findViewById(R.id.hotsongfav);
            convertView.setTag(homeView);
        } else {
            homeView = (DB_HotSongerView) convertView.getTag();
        }
        Map<String, Object> map = this.listArr.get(position);
        int resP = Integer.valueOf(map.get("photo").toString());
        Log.i("M_HomeHotSongerAdapter", "<<<<<<<< resp"+resP);
        homeView.photo.setImageResource(resP);
        homeView.songer.setText(map.get("songer").toString());
        Log.i("M_HomeHotSongerAdapter", "<<<<<<<<"+map.get("songer"));
        homeView.fav.setText(map.get("fav").toString());
        homeView.categary.setText(map.get("categary").toString());
        return convertView;
    }

}
class DB_HotSongerView{
    ImageView photo;
    TextView songer;
    TextView categary;
    TextView fav;
}
