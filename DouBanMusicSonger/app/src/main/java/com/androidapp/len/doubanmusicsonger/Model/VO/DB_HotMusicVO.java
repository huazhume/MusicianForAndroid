package com.androidapp.len.doubanmusicsonger.Model.VO;

/**
 * Created by len on 16/11/30.
 */

public class DB_HotMusicVO {
    public String getSongid() {
        return songid;
    }
    public void setSongid(String songid) {
        this.songid = songid;
    }
    public String getDownUrl() {
        return downUrl;
    }
    public void setDownUrl(String downUrl) {
        this.downUrl = downUrl;
    }
    public String getAlbumpic_big() {
        return albumpic_big;
    }
    public void setAlbumpic_big(String albumpic_big) {
        this.albumpic_big = albumpic_big;
    }
    public String getSongname() {
        return songname;
    }
    public void setSongname(String songname) {
        this.songname = songname;
    }
    public String getSingername() {
        return singername;
    }
    public void setSingername(String singername) {
        this.singername = singername;
    }
    public String getAlbumname() {
        return albumname;
    }
    public void setAlbumname(String albumname) {
        this.albumname = albumname;
    }
    public boolean isPlayState() {
        return playState;
    }

    public void setPlayState(boolean playState) {
        this.playState = playState;
    }
    public Integer songIndex() {
        return songIndex;
    }
    public void setSongIndex(Integer songIndex) {
        this.songIndex = songIndex;
    }
    public Integer songDurtion() {
        return songDurtion;
    }
    public void setSongDurtion(Integer songDurtion) {
        this.songDurtion = songDurtion;
    }
    private String songid;
    private String downUrl;
    private String albumpic_big;
    private String songname;
    private String singername;
    private String albumname;
    private Integer songIndex;
    boolean playState;
    private Integer songDurtion;
}
