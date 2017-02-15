package com.androidapp.len.doubanmusicsonger.Interface.fragment;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.androidapp.len.doubanmusicsonger.MainActivity;
import com.androidapp.len.doubanmusicsonger.R;
import com.androidapp.len.doubanmusicsonger.AVAudioPlayer.DB_AVAudioplayer;
import com.androidapp.len.doubanmusicsonger.Common.DB_CommonUtils;
import com.androidapp.len.doubanmusicsonger.Service.DB_DownloadCallback;
import com.androidapp.len.doubanmusicsonger.AVAudioPlayer.DB_MusicPlayAsync;
import com.androidapp.len.doubanmusicsonger.AVAudioPlayer.DB_MusicPlayCallback;
import com.androidapp.len.doubanmusicsonger.Service.DB_DownloadUtils;
import com.androidapp.len.doubanmusicsonger.Adapter.DB_HotOneAdapter;
import com.androidapp.len.doubanmusicsonger.Model.VO.DB_HotMusicVO;
import com.lidroid.xutils.http.RequestParams;
import com.lidroid.xutils.http.client.HttpRequest;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.json.JSONTokener;

import java.util.ArrayList;
import java.util.List;


/**
 * Created by len on 16/11/30.
 */
public class DB_HotOneFragment extends android.support.v4.app.Fragment {
    //listView
    private ListView hotOneListView;
    private DB_HotOneAdapter hotOneAdapter;
    private List<DB_HotMusicVO> listArr= new ArrayList<DB_HotMusicVO>();

    private DB_AVAudioplayer audioplayer;
    private DB_MusicPlayAsync musicPlayAsync;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // TODO Auto-generated method stub
        return inflater.inflate(R.layout.db_fragment_hotone, null);
    }

    @Override
    public void onResume() {
        Log.i("DB_TabFirstFragment","<<<<<<<<< zhixing");
        super.onResume();
        initBaceViews();
        loadData();
    }

    //初始化baseViews
    private void initBaceViews() {
        // TODO Auto-generated method stub
        hotOneListView = (ListView) this.getView().findViewById(R.id.hotOneListView);
        hotOneAdapter = new DB_HotOneAdapter(this.getActivity(), listArr, new DB_MusicPlayCallback() {
            @Override
            public void musicPlay(int position) {
                Log.i("DB_TabFirstFragment","<<<<<<<<< 播放:"+position   +"链接:"+ listArr.get(position).getDownUrl().toString());
               songPlayAndPause(position);
            }
        });
        Log.i("DB_TabFirstFragment","<<<<<<<<< l"+listArr);
        hotOneListView.setAdapter(hotOneAdapter);
    }
    private void loadData() {
        // download
        Log.i("DB_HotOneFregment","<<<<<<<download");
        String paths = "http://route.showapi.com/213-4";
        RequestParams params = new RequestParams();
        params.addBodyParameter("topid", "26");
        params.addBodyParameter("page", "1");
        params.addBodyParameter("pagesize", "50");
        params.addBodyParameter("showapi_appid", "16626");
        params.addBodyParameter("showapi_timestamp", DB_CommonUtils.getCurrentTime());
        params.addBodyParameter("showapi_sign", "e19fa39608784506b3ba0bde1066d83a");
        DB_DownloadUtils.sendRequest(this.getActivity(), HttpRequest.HttpMethod.POST, paths, params, new DB_DownloadCallback() {
            @Override
            public void callback(Object object) {
                JSONObject obj1 = null;
                try {
                    JSONObject res =  new JSONObject(new JSONTokener(new String(object.toString())));
                    obj1 = res.getJSONObject("showapi_res_body");
                    JSONObject obj2 = obj1.getJSONObject("pagebean");
                    JSONArray arrlist = obj2.getJSONArray("songlist");
                    List<DB_HotMusicVO> arr = new ArrayList<DB_HotMusicVO>();
                    for (int i = 0; i < arrlist.length(); i++) {
                        JSONObject obj3 = arrlist.getJSONObject(i);
                        DB_HotMusicVO model = new DB_HotMusicVO();
                        model.setAlbumpic_big(obj3.getString("albumpic_big").toString());
                        ;
                        model.setDownUrl(obj3.getString("url").toString());
                        ;
                        model.setSingername(obj3.getString("singername").toString());
                        ;
                        model.setSongid(obj3.getString("songid").toString());
                        ;
                        model.setSongname(obj3.getString("songname").toString());
                        ;
                        model.setSongIndex(i);
                        arr.add(model);
                    }
                    listArr.addAll(arr);
                    hotOneAdapter.notifyDataSetChanged();

                } catch (JSONException e) {
                    e.printStackTrace();
                }
                Log.i("DB_TabFirstFragment","<<<<<<<<< downloadmodel"+listArr.size());
            }
        });
    }

    //music play
    public void songPlayAndPause(int position){
        //点击相同的 并且这首歌还在播放
        if(DB_AVAudioplayer.getMediaPlayer().isPlaying()&&position == DB_AVAudioplayer.getInstance().musicModel.songIndex()){
            //暂停 给个较大的值
            hotOneAdapter.notifyDataSetChanged();
            DB_AVAudioplayer.getInstance().pause();
            audioplayer.musicModel = null;
        }else {
            audioplayer = DB_AVAudioplayer.getInstance();
            audioplayer.musicModel = listArr.get(position);
            hotOneAdapter.notifyDataSetChanged();
            musicPlayAsync = DB_MusicPlayAsync.getInstance();
            musicPlayAsync.musicAsyncPlay(listArr.get(position).getDownUrl().toString(),audioplayer);
            musicPlayAsync.run();
        }
        MainActivity main = (MainActivity) this.getActivity();
        main.changePlayingViewState(true);
    }

}
