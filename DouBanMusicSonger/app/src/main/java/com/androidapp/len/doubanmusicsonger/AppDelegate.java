package com.androidapp.len.doubanmusicsonger;

import android.app.Application;

import com.umeng.socialize.PlatformConfig;
import com.umeng.socialize.UMShareAPI;

/**
 * Created by len on 16/12/6.
 */

public class AppDelegate extends Application {
    //注册应用
    {
        PlatformConfig.setWeixin("wx967daebe835fbeac", "5bb696d9ccd75a38c8a0bfe0675559b3");
        PlatformConfig.setSinaWeibo("3921700954", "04b48b094faeb16683c32669824ebdad");
        PlatformConfig.setQQZone("1105867764", "KdGlVeYTKhTh0wm8");
    }

    @Override
    public void onCreate() {
        super.onCreate();
        UMShareAPI.get(this);/**/
    }
}
