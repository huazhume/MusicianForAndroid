package com.androidapp.len.doubanmusicsonger.Interface.activity;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.widget.ListView;
import android.widget.TextView;

import com.androidapp.len.doubanmusicsonger.Adapter.DB_HotOneAdapter;
import com.androidapp.len.doubanmusicsonger.Common.DB_CommonUtils;
import com.androidapp.len.doubanmusicsonger.Model.VO.DB_HotMusicVO;
import com.androidapp.len.doubanmusicsonger.R;
import com.androidapp.len.doubanmusicsonger.Service.DB_DownloadCallback;
import com.androidapp.len.doubanmusicsonger.Service.DB_DownloadUtils;
import com.lidroid.xutils.http.RequestParams;
import com.lidroid.xutils.http.client.HttpRequest;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.json.JSONTokener;

import java.util.ArrayList;
import java.util.List;


/**
 * Created by len on 16/12/5.
 */

public class DB_SearchResultActivity extends Activity {
    private ListView searchListView;
    private DB_HotOneAdapter adapter;
    //data
    private List<DB_HotMusicVO> listArr = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.db_activity_searchresult);
        initBaceView();
        loadData();
    }

    private void initBaceView() {
        TextView tv = (TextView) this.findViewById(R.id.nav_tv);
        if(getIntent().getBooleanExtra("type",false) == false){
            tv.setText("搜索 \"" + getIntent().getStringExtra("keyword") + "\"");
        }
        else{
            tv.setText(getIntent().getStringExtra("name"));
        }
        searchListView = (ListView) this.findViewById(R.id.search_listview);
        adapter = new DB_HotOneAdapter(this, listArr, null);
        searchListView.setAdapter(adapter);
    }

    private void loadData() {

        String paths = getIntent().getBooleanExtra("type", false) ? "http://route.showapi.com/213-4" : "http://route.showapi.com/213-1";
        RequestParams params = new RequestParams();
        params.addBodyParameter("page", "1");
        params.addBodyParameter("showapi_appid", "16626");
        params.addBodyParameter("showapi_timestamp", DB_CommonUtils.getCurrentTime());
        params.addBodyParameter("showapi_sign", "e19fa39608784506b3ba0bde1066d83a");
        if (getIntent().getBooleanExtra("type", false) == false) {
            params.addBodyParameter("keyword", getIntent().getStringExtra("keyword"));
        } else {
            params.addBodyParameter("topid", getIntent().getStringExtra("number"));
        }

        DB_DownloadUtils.sendRequest(this, HttpRequest.HttpMethod.POST, paths, params, new DB_DownloadCallback() {
            @Override
            public void callback(Object object) {
                JSONObject obj1 = null;
                try {
                    JSONObject res = new JSONObject(new JSONTokener(new String(object.toString())));
                    obj1 = res.getJSONObject("showapi_res_body");
                    JSONObject obj2 = obj1.getJSONObject("pagebean");
                    String key = getIntent().getBooleanExtra("type",false) ? "songlist":"contentlist";
                    JSONArray arrlist = obj2.getJSONArray(key);
                    Log.i("DB_TabSecondFragment", "<<<<<<<<< lenth" + arrlist.length());
                    List<DB_HotMusicVO> arr = new ArrayList<DB_HotMusicVO>();
                    for (int i = 0; i < arrlist.length(); i++) {

                        JSONObject obj3 = arrlist.getJSONObject(i);
                        DB_HotMusicVO model = new DB_HotMusicVO();

                        model.setAlbumpic_big(obj3.getString("albumpic_big").toString());

                        model.setDownUrl(obj3.getString("downUrl").toString());
                        ;
                        model.setSingername(obj3.getString("singername").toString());
                        ;
                        model.setSongid(obj3.getString("songid").toString());
                        ;
                        model.setSongname(obj3.getString("songname").toString());
                        ;
                        model.setSongIndex(i);
                        arr.add(model);

                        Log.i("DB_TabSecondFragment", "<<<<<<<<<" + model.getSongname());
                    }
                    listArr.addAll(arr);
                    adapter.notifyDataSetChanged();
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        });
    }
}
