package com.androidapp.len.doubanmusicsonger.Adapter;

import android.content.Context;
import android.graphics.Bitmap;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.androidapp.len.doubanmusicsonger.R;
import com.androidapp.len.doubanmusicsonger.AVAudioPlayer.DB_AVAudioplayer;
import com.androidapp.len.doubanmusicsonger.AVAudioPlayer.DB_MusicPlayCallback;
import com.androidapp.len.doubanmusicsonger.Model.VO.DB_HotMusicVO;
import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;

import java.text.DecimalFormat;
import java.util.List;

/**
 * Created by len on 16/11/30.
 */

public class DB_HotOneAdapter extends BaseAdapter {
    private LayoutInflater inflater;
    private List<DB_HotMusicVO> listArr;
    private Context context;
    //callback
    private DB_MusicPlayCallback musicPlayCallback;

    public DB_HotOneAdapter() {
        // TODO Auto-generated constructor stub
    }

    public DB_HotOneAdapter(Context context, List<DB_HotMusicVO> listArr2, DB_MusicPlayCallback musicPlayCallback) {
        // TODO Auto-generated constructor stub
        this.listArr = listArr2;
        this.inflater = LayoutInflater.from(context);
        this.context = context;
        this.musicPlayCallback = musicPlayCallback;
    }

    @Override
    public int getCount() {
        // TODO Auto-generated method stub
        return listArr.size();
    }

    @Override
    public Object getItem(int position) {
        // TODO Auto-generated method stub
        return position;
    }

    @Override
    public long getItemId(int position) {
        // TODO Auto-generated method stub
        return position;
    }

    @Override
    public View getView(final int position, View convertView, final ViewGroup parent) {
        // TODO Auto-generated method stub
        DB_HomeItemView homeView = null;
        if (convertView == null) {
            convertView = inflater.inflate(R.layout.db_listitem_hotone, null);
            homeView = new DB_HomeItemView();
            homeView.home_iconBtn = (Button) convertView.findViewById(R.id.home_musicIconBtn);
            homeView.home_iconImage = (ImageView) convertView.findViewById(R.id.home_iconImage);
            homeView.home_musicTitle = (TextView) convertView.findViewById(R.id.home_musicTitle);
            homeView.home_musicInfo = (TextView) convertView.findViewById(R.id.home_musicInfo);
            homeView.home_musicPlayBtn = (Button) convertView.findViewById(R.id.home_musicPlayBtn);
            convertView.setTag(homeView);
        } else {
            homeView = (DB_HomeItemView) convertView.getTag();
        }
        DB_HotMusicVO model = this.listArr.get(position);
        homeView.home_musicTitle.setText(model.getSongname());
        homeView.home_musicInfo.setText(model.getSingername());
        //创建默认的ImageLoader配置参数
        ImageLoaderConfiguration configuration = ImageLoaderConfiguration
                .createDefault(this.context);

        //Initialize ImageLoader with configuration.
        ImageLoader.getInstance().init(configuration);
        // 显示图片的配置
        DisplayImageOptions options = new DisplayImageOptions.Builder().showImageOnLoading(null)
                .showImageOnFail(null).cacheInMemory(true).cacheOnDisk(true)
                .bitmapConfig(Bitmap.Config.RGB_565).build();
        ImageLoader.getInstance().displayImage(model.getAlbumpic_big(), homeView.home_iconImage, options);
        DecimalFormat df = null;
        if (position > 98) {
            df = new DecimalFormat("");
        } else {
            df = new DecimalFormat("00");
        }
        homeView.home_iconBtn.setText(df.format(position + 1));
        final View view = convertView;
        final int p = position;
        final int one = homeView.home_musicPlayBtn.getId();
        homeView.home_musicPlayBtn.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                musicPlayCallback.musicPlay(position);
            }
        });
        DB_AVAudioplayer player = DB_AVAudioplayer.getInstance();
		if(DB_AVAudioplayer.getMediaPlayer().isPlaying() && DB_AVAudioplayer.getInstance().musicModel.songIndex() == position){

			homeView.home_musicPlayBtn.setBackgroundResource(R.mipmap.player_pause_hover);
        }else {
            homeView.home_musicPlayBtn.setBackgroundResource(R.mipmap.player_play_hover);
        }
        return convertView;
    }
}

class DB_HomeItemView {
    ImageView home_iconImage;
    Button home_iconBtn;
    TextView home_musicTitle;
    TextView home_musicInfo;
    Button home_musicPlayBtn;
}

