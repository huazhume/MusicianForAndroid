package com.androidapp.len.doubanmusicsonger.Common;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by len on 16/11/30.
 */

public class DB_CommonUtils {
    public static String getCurrentTime() {
        Date curDate = new Date(System.currentTimeMillis());// 获取当前时间
        SimpleDateFormat sDateFormat = new SimpleDateFormat("yyyyMMddHHmmss");
        return sDateFormat.format(curDate);
    }
}

