package com.alphawizard.hdwallet.common.base.App;


import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.os.SystemClock;

import com.alibaba.android.arouter.launcher.ARouter;

import java.io.File;

import dagger.android.AndroidInjector;
import dagger.android.DaggerApplication;


/**
 * @author qiujuer Email:qiujuer@live.cn
 * @version 1.0.0
 */
public abstract class Application extends DaggerApplication{


    private static  Context instance ;

    @Override
    public void onCreate() {
        super.onCreate();
        instance = this;

        if (isDebug(this)) {           // These two lines must be written before init, otherwise these configurations will be invalid in the init process
            ARouter.openLog();     // Print log
            ARouter.openDebug();   // Turn on debugging mode (If you are running in InstantRun mode, you must turn on debug mode! Online version needs to be closed, otherwise there is a security risk)
        }
        ARouter.init(this);
    }

    @Override
    protected abstract AndroidInjector<? extends DaggerApplication> applicationInjector();

    public static Context getInstance() {
        return instance;
    }



    public static File getAppCache(){
        return instance.getCacheDir();
    }

    public static String getAppCacheDir(){
        return instance.getCacheDir().getAbsolutePath();
    }


    public static File getImageCacheFile(){
        File filePortrait = new File(getAppCacheDir(), "keystore");

        if(!filePortrait.exists()){
            filePortrait.mkdirs();
        }
        if(filePortrait.listFiles().length>0) {
            for (File f : filePortrait.listFiles()) {
                f.delete();
            }
        }

        File  imageFile  = new File(filePortrait,SystemClock.uptimeMillis()+".txt");


        return imageFile;
    }








    public  static void  showToast(final String string){
//      用Rx 来代替
//      Run.onUiAsync(new Action() {
//            @Override
//            public void call() {
//                Toast.makeText(instance, string , Toast.LENGTH_SHORT).show();
//            }
//        });
    }

    public  static void showToast(int  resId){
        showToast(instance.getString(resId));
    }


    /**
     *  判断当前应用是否是debug状态
     */
    public static boolean isDebug(Context context) {
        try {
            ApplicationInfo info = context.getApplicationInfo();
            return (info.flags & ApplicationInfo.FLAG_DEBUGGABLE) != 0;
        } catch (Exception e) {
            return false;
        }
    }

}
