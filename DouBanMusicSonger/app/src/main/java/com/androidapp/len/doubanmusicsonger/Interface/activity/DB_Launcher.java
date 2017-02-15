package com.androidapp.len.doubanmusicsonger.Interface.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.text.NoCopySpan;

import com.androidapp.len.doubanmusicsonger.MainActivity;
import com.androidapp.len.doubanmusicsonger.R;

/**
 * Created by len on 16/12/26.
 */

public class DB_Launcher extends Activity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        setContentView(R.layout.db_activity_launcher);
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent mainIntent = new Intent(DB_Launcher.this,MainActivity.class);
                mainIntent.setFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
                DB_Launcher.this.startActivity(mainIntent);
                DB_Launcher.this.finish();
            }
        },3000);
        super.onCreate(savedInstanceState);
    }

}

