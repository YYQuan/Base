package com.alphawizard.hdwallet.alphahdwallet.utils;

import android.os.Environment;

import java.io.File;

/**
 * Created by Administrator on 2017/9/28 0028.
 */

public interface Constant {

    /**
     * 应用包名
     */
    String PACKAGE_NAME = "com.instantex.alpha";

    /**
     * 倒计时总时长
     */
    int TOTAL_TIME = 60*1000;

    /**
     * 间隔时长
     */
    int INTERVAL_TIME = 1*1000;

    /**
     * 后台数据message字段
     */

    String MESSAGE = "message";

    /**
     * 后台数据state字段
     */

    String STATE = "state";

    /**
     * 后台数据obj字段
     */

    String OBJ = "obj";

    /**
     * APP下载连接
     */

    String APP_DOWNLOAD = "https://www.pgyer.com/cccbtc";

    String IMAGE_DIR = Environment.getExternalStorageDirectory() + File.separator + "bihuex截屏";
    String SCREEN_SHOT ="bihuex.png";
}
