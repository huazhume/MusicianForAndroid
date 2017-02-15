package com.androidapp.len.doubanmusicsonger.AVAudioPlayer;


import android.media.AudioManager;
import android.media.MediaPlayer;
import android.media.MediaPlayer.OnBufferingUpdateListener;
import android.media.MediaPlayer.OnCompletionListener;
import android.media.MediaPlayer.OnErrorListener;
import android.media.MediaPlayer.OnPreparedListener;
import android.util.Log;

import com.androidapp.len.doubanmusicsonger.Model.VO.DB_HotMusicVO;

public class DB_AVAudioplayer implements OnBufferingUpdateListener, OnCompletionListener, OnPreparedListener,OnErrorListener {
	// 增加几个属性
	private static MediaPlayer mediaPlayer = new MediaPlayer();
	private static DB_AVAudioplayer single = new DB_AVAudioplayer();
	private String url;
	//private static Thread playAsync = new Thread(new MusicPlayAsync());
	
	// 增加一个model
	public DB_HotMusicVO musicModel;
	
	// 构造方法私有
	private DB_AVAudioplayer() {
		// TODO Auto-generated constructor stub
	}
	public  static  MediaPlayer getMediaPlayer(){
		return  mediaPlayer;
	}

	public static DB_AVAudioplayer getInstance() {
		mediaPlayer.setAudioStreamType(AudioManager.STREAM_MUSIC);// 设置媒体流类型
		return single;
	}

	public void play() {
		mediaPlayer.start();
	}

	public void playUrl(String url) {
		try {
			mediaPlayer.reset();
			mediaPlayer.setDataSource(url); // 设置数据源
			mediaPlayer.prepare(); // prepare自动播放
			mediaPlayer.setOnCompletionListener(this);
			mediaPlayer.setOnErrorListener(this);
			mediaPlayer.setOnBufferingUpdateListener(this);
			mediaPlayer.setOnPreparedListener(this);
			Log.i("AVAudioplayer", "<<<<<<<<<<------------------"+mediaPlayer.getDuration()/1000/60.0);
			this.musicModel.setSongDurtion(mediaPlayer.getDuration()/1000);
		} catch (Exception e) {
			e.printStackTrace();
		} 
	}
	// 暂停
	public void pause() {
		mediaPlayer.pause();
	}

	// 停止
	public void stop() {
		if (mediaPlayer != null) {
			mediaPlayer.stop();
			mediaPlayer.release();
			mediaPlayer = null;
		}
	}
	//准备
	@Override
	public void onPrepared(MediaPlayer mp) {
		// TODO Auto-generated method stub
	}
	//完成
	@Override
	public void onCompletion(MediaPlayer mp) {
		// TODO Auto-generated method stub
	}
	//缓冲
	@Override
	public void onBufferingUpdate(MediaPlayer mp, int percent) {
		// TODO Auto-generated method stub
	}

	@Override
	public boolean onError(MediaPlayer mp, int what, int extra) {
		// TODO Auto-generated method stub
		switch (what) {
		case MediaPlayer.MEDIA_ERROR_NOT_VALID_FOR_PROGRESSIVE_PLAYBACK:
			break;
		case MediaPlayer.MEDIA_ERROR_SERVER_DIED:
			break;
		case MediaPlayer.MEDIA_ERROR_UNKNOWN:		
			break;
		default:
			break;
		}
		return false;
	}
}


