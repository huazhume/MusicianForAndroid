package com.androidapp.len.doubanmusicsonger.Interface.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

import com.androidapp.len.doubanmusicsonger.Interface.activity.DB_SearchResultActivity;
import com.androidapp.len.doubanmusicsonger.R;
import com.androidapp.len.doubanmusicsonger.Adapter.DB_HotSongerAdapter;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by len on 16/11/30.
 */

public class DB_HotSongerFragment extends Fragment{
    private ListView hotSongerListView;
    private DB_HotSongerAdapter adapter;
    private List<Map<String, Object>> listArr;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // TODO Auto-generated method stub
        return inflater.inflate(R.layout.db_fragment_hotsonger, null);
    }
    @Override
    public void onResume() {
        // TODO Auto-generated method stub
        super.onResume();
        loadData();
        initBaceViews();
    }

    private void initBaceViews() {
        // TODO Auto-generated method stub
        hotSongerListView = (ListView) this.getView().findViewById(R.id.hotSongerListView);
        adapter = new DB_HotSongerAdapter(this.getActivity(),listArr);
        hotSongerListView.setAdapter(adapter);
        hotSongerListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Log.i("DB_HotSongerFragment","<<<<<<<<< l"+i);
                String songer =  listArr.get(i).get("songer").toString();
                inMusicResult(songer);
            }
        });
    }
    public void  inMusicResult(String songer){
        Intent intent = new Intent();
        intent.setClass(this.getActivity(), DB_SearchResultActivity.class);
        intent.putExtra("keyword", songer);
        intent.putExtra("type",false);
        startActivity(intent);
    }

    //下载数据
    private void loadData() {
        // TODO Auto-generated method stub
        listArr = new ArrayList<Map<String,Object>>();
        Map<String, Object> map1 = new HashMap<String, Object>();
        map1.put("photo", R.mipmap.zhoujielun+"");
        map1.put("songer", "周杰伦");
        map1.put("categary", "流行 Rock");
        map1.put("fav", "20213人关注");

        Map<String, Object> map2 = new HashMap<String, Object>();
        map2.put("photo", R.mipmap.xusong+"");
        map2.put("songer", "许嵩");
        map2.put("categary", "流行 Pop");
        map2.put("fav", "12312人关注");

        Map<String, Object> map3 = new HashMap<String, Object>();
        map3.put("photo", R.mipmap.chenyixun+"");
        map3.put("songer", "陈奕迅");
        map3.put("categary", "粤语 Pop");
        map3.put("fav", "32431人关注");

        Map<String, Object> map4 = new HashMap<String, Object>();
        map4.put("photo", R.mipmap.wangsulong+"");
        map4.put("songer", "汪苏泷");
        map4.put("categary", "流行 Pop");
        map4.put("fav", "11121人关注");

        Map<String, Object> map5 = new HashMap<String, Object>();
        map5.put("photo", R.mipmap.dingdang+"");
        map5.put("songer", "丁当");
        map5.put("categary", "流行 Pop");
        map5.put("fav", "21321人关注");

        Map<String, Object> map6 = new HashMap<String, Object>();
        map6.put("photo", R.mipmap.liudehua+"");
        map6.put("songer", "刘德华");
        map6.put("categary", "粤语 Rock");
        map6.put("fav", "31212人关注");

        Map<String, Object> map7 = new HashMap<String, Object>();
        map7.put("photo", R.mipmap.liyuchun+"");
        map7.put("songer", "李宇春");
        map7.put("categary", "流行 Rock");
        map7.put("fav", "21212人关注");

        Map<String, Object> map8 = new HashMap<String, Object>();
        map8.put("photo", R.mipmap.liangjingru+"");
        map8.put("songer", "梁静茹");
        map8.put("categary", "国语 Pop");
        map8.put("fav", "12312人关注");

        Map<String, Object> map9 = new HashMap<String, Object>();
        map9.put("photo", R.mipmap.linjunjie+"");
        map9.put("songer", "林俊杰");
        map9.put("categary", "流行 Rock");
        map9.put("fav", "21246人关注");

        Map<String, Object> map10 = new HashMap<String, Object>();
        map10.put("photo", R.mipmap.zhouhuajian+"");
        map10.put("songer", "周华健");
        map10.put("categary", "国语 流行");
        map10.put("fav", "31431人关注");

        //加入数组中
        listArr.add(map1);
        listArr.add(map2);
        listArr.add(map3);
        listArr.add(map4);
        listArr.add(map5);
        listArr.add(map6);
        listArr.add(map7);
        listArr.add(map8);
        listArr.add(map9);
        listArr.add(map10);
    }
}
