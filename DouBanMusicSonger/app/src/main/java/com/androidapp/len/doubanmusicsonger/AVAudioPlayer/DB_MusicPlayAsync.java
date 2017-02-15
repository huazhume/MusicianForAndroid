package com.androidapp.len.doubanmusicsonger.AVAudioPlayer;

/**
 * Created by len on 16/12/2.
 */

public class DB_MusicPlayAsync {
    public String playUrl;
    private DB_AVAudioplayer player;

    private static DB_MusicPlayAsync single = new DB_MusicPlayAsync();

    public DB_MusicPlayAsync(){

    }
    public void musicAsyncPlay(String playUrl,DB_AVAudioplayer player) {
        // TODO Auto-generated constructor stub
        this.playUrl = playUrl;
        this.player  = player;
    }


    public void run() {
        player.playUrl(playUrl);
        player.play();
    }
    public void play(){
        player.play();
    }
    public void stop(){
        player.stop();
    }
    public void pause(){
        player.pause();
    }
    private String url;
    //private static Thread playAsync = new Thread(new MusicPlayAsync());
    private void interrupt(){

    }

    public static DB_MusicPlayAsync getInstance() {

        return single;
    }
}
