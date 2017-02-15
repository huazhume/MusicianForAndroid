package com.androidapp.len.doubanmusicsonger;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.util.Log;
import android.view.View;
import android.view.animation.LinearInterpolator;
import android.view.animation.TranslateAnimation;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.androidapp.len.doubanmusicsonger.AVAudioPlayer.DB_AVAudioplayer;
import com.androidapp.len.doubanmusicsonger.Interface.fragment.DB_TabFirstFragment;
import com.androidapp.len.doubanmusicsonger.Interface.fragment.DB_TabSecondFragment;
import com.androidapp.len.doubanmusicsonger.Interface.fragment.DB_TabThirdFragment;
import com.androidapp.len.doubanmusicsonger.Model.VO.DB_HotMusicVO;
import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;
import com.umeng.socialize.UMShareAPI;

import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;

public class MainActivity extends FragmentActivity implements View.OnClickListener {

    // 得到fragmentManager
    private FragmentManager fragmentManager;
    private FragmentTransaction transaction;
    // tabbatButton
    private Button firstBtn;
    private Button secondBtn;
    private Button thirdBtn;
    private Button fourthBtn;
    // tabbarFragment
    private DB_TabFirstFragment tab_FirstFragment;
    private DB_TabSecondFragment tab_SecondFragment;
    private DB_TabThirdFragment tab_thirdFragment;

    //audioPlaybar
    private ImageView audio_iconImage;
    private TextView audio_nameTv;
    private TextView audio_songerTv;

    private Button audio_playBtn;
    private Button audio_nextBtn;
    private TextView audio_songDurTv;
    private TextView audio_playDurTv;

    // tabbarBtnArrs
    private ArrayList<Button> tabBtnList;

    // playingView
    private RelativeLayout rl_PlayingView;
    // state
    private Boolean playViewInviableState = false;

    // 得到rl_content
    private RelativeLayout rl_content;

    private int SCREEN_WIDTH;
    private int SCREEN_HEIGHT;

    private static Integer durtion;
    private static Timer timer = new Timer(true);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        configFragment();
        configTabbar();
    }

    // 配置fragment
    private void configFragment() {
        // TODO Auto-generated method stub

        // 创建管理者
        fragmentManager = this.getSupportFragmentManager();
        // 初始化对象
        tab_FirstFragment = new DB_TabFirstFragment();
        tab_SecondFragment = new DB_TabSecondFragment();
        tab_thirdFragment = new DB_TabThirdFragment();

        // 添加回退栈中
        transaction = fragmentManager.beginTransaction();
        transaction.replace(R.id.rl_content, tab_thirdFragment, "thirdFragment");
        transaction.replace(R.id.rl_content, tab_SecondFragment, "secondFragment");
        transaction.replace(R.id.rl_content, tab_FirstFragment, "firstFragment");
        transaction.addToBackStack("thirdFragment");
        transaction.addToBackStack("secondFragment");
        transaction.addToBackStack("firstFragment");
        transaction.commit();

        // 得到rl_content
        rl_content = (RelativeLayout) this.findViewById(R.id.rl_content);

    }

    // 初始化tabbar
    private void configTabbar() {
        // TODO Auto-generated method stub

        firstBtn = (Button) this.findViewById(R.id.firstBtn);
        secondBtn = (Button) this.findViewById(R.id.secondBtn);
        thirdBtn = (Button) this.findViewById(R.id.thirdBtn);
        fourthBtn = (Button) this.findViewById(R.id.fourthBtn);

        // playingView
        rl_PlayingView = (RelativeLayout) this.findViewById(R.id.rl_PlayingView);

        // 添加到arraylist中
        tabBtnList = new ArrayList<Button>();
        tabBtnList.add(firstBtn);
        tabBtnList.add(secondBtn);
        tabBtnList.add(thirdBtn);
        // tabBtnList.add(fourthBtn);
        // 添加事件
        firstBtn.setOnClickListener(this);
        secondBtn.setOnClickListener(this);
        thirdBtn.setOnClickListener(this);
        fourthBtn.setOnClickListener(this);

        firstBtn.setTextColor(Color.rgb(100, 100, 100));

        //audioBtn
        audio_iconImage = (ImageView) this.findViewById(R.id.playView_icon);
        audio_nameTv = (TextView) this.findViewById(R.id.playView_songnametile);
        audio_songerTv = (TextView) this.findViewById(R.id.playView_songinfo);
        audio_nextBtn = (Button) this.findViewById(R.id.playView_nextbtn);
        audio_playBtn = (Button) this.findViewById(R.id.playView_playingbtn);
        audio_songDurTv = (TextView) this.findViewById(R.id.playView_songdurtion);
        audio_playDurTv = (TextView) this.findViewById(R.id.playView_playdurtion);

        audio_playBtn.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        // TODO Auto-generated method stub
        switch (v.getId()) {
            case R.id.firstBtn:
                changeTabbarProperty(firstBtn);
                addToBackStack(tab_FirstFragment);
                break;
            case R.id.secondBtn:
                changeTabbarProperty(secondBtn);
                addToBackStack(tab_SecondFragment);
                break;
            case R.id.thirdBtn:
                changeTabbarProperty(thirdBtn);
                addToBackStack(tab_thirdFragment);
                break;
            case R.id.fourthBtn:
                // changeTabbarProperty(fourthBtn);
                changePlayingViewState(true);
                break;
            case R.id.playView_playingbtn:
                DB_AVAudioplayer avAudioplayer = DB_AVAudioplayer.getInstance();
                avAudioplayer.musicModel = null;
                changePlayingViewState(true);
                break;
            default:
                break;
        }
    }

    // 加载tabbarFragment
    public void addToBackStack(Fragment object) {
        String tagstring = null;
        if (object instanceof DB_TabFirstFragment) {
            tagstring = "firstFragment";
        } else if (object instanceof DB_TabSecondFragment) {
            tagstring = "secondFragment";
        } else if (object instanceof DB_TabThirdFragment) {
            tagstring = "thirdFragment";
        }
        transaction = fragmentManager.beginTransaction();
        transaction.replace(R.id.rl_content, object, tagstring);
        transaction.commit();
        Log.i("MainActivity", "<<<<<<<" + fragmentManager.getBackStackEntryCount());
    }

    // 点击tabbar 改变颜色
    public void changeTabbarProperty(Button tabBtn) {
        for (Button iBtn : tabBtnList) {
            if (iBtn == tabBtn) {
                tabBtn.setTextColor(Color.rgb(100, 100, 100));
            } else {
                iBtn.setTextColor(Color.rgb(255, 255, 255));
            }
        }
    }

    // 显示playingView
    public void changePlayingViewState(boolean playState) {

        //setData
        // audio_playBtn.setBackgroundResource(R.mipmap.play_playing);
        DB_HotMusicVO model = DB_AVAudioplayer.getInstance().musicModel;
        if (model != null && DB_AVAudioplayer.getMediaPlayer().isPlaying() == true) {
            audio_nameTv.setText(model.getSongname());
            audio_songerTv.setText(model.getSingername());
            //创建默认的ImageLoader配置参数
            ImageLoaderConfiguration configuration = ImageLoaderConfiguration
                    .createDefault(this);

            //Initialize ImageLoader with configuration.
            ImageLoader.getInstance().init(configuration);
            // 显示图片的配置
            DisplayImageOptions options = new DisplayImageOptions.Builder().showImageOnLoading(null)
                    .showImageOnFail(null).cacheInMemory(true).cacheOnDisk(true)
                    .bitmapConfig(Bitmap.Config.RGB_565).build();
            ImageLoader.getInstance().displayImage(model.getAlbumpic_big(), audio_iconImage, options);
            audio_playBtn.setBackgroundResource(R.mipmap.play_playing);
            audio_songDurTv.setText(model.songDurtion() / 60 + " :" + model.songDurtion() % 60);
            durtion = 0;

            timer.schedule(new TimerTask() {
                @Override
                public void run() {
                    // audio_playDurTv.setText(durtion+"");
                    durtion++;
                    Log.i("MainActivity", "<<<<<<<-----------" + durtion);
                }
            }, 1000);

        } else {
            audio_nameTv.setText("目前没有播放任何歌曲呦");
            audio_songerTv.setText("-_-");
            audio_iconImage.setImageBitmap(null);
            audio_playBtn.setBackgroundResource(R.mipmap.playing_pause);
            audio_songDurTv.setText("");
        }

        RelativeLayout.LayoutParams laParams = (android.widget.RelativeLayout.LayoutParams) rl_content
                .getLayoutParams();


//		 ScaleAnimation scaleAnimation = new ScaleAnimation(1.0f, 1.0f, 1.0f,
//		 0.9f);
//		 scaleAnimation.setFillAfter(true);
//		 scaleAnimation.setDuration(700);
//		 rl_content.startAnimation(scaleAnimation);


        if (!playViewInviableState) {
            TranslateAnimation animation = new TranslateAnimation(0, 0, 0, -98);
            animation.setInterpolator(new LinearInterpolator());
            animation.setDuration(400);
            animation.setFillAfter(true);
            laParams.bottomMargin = 98;

            rl_PlayingView.startAnimation(animation);
        } else {
            TranslateAnimation animation = new TranslateAnimation(0, 0, 0, 98);
            animation.setInterpolator(new LinearInterpolator());
            animation.setDuration(600);
            animation.setFillAfter(true);
            laParams.bottomMargin = 0;
            rl_PlayingView.startAnimation(animation);
        }
        playViewInviableState = !playViewInviableState;
        rl_content.setLayoutParams(laParams);

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        UMShareAPI.get(this).onActivityResult(requestCode, resultCode, data);
    }
}
