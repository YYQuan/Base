package com.alphawizard.hdwallet.common.base.App;


import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.os.SystemClock;
import android.widget.Toast;

import com.alibaba.android.arouter.launcher.ARouter;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.cookie.CookieJarImpl;
import com.lzy.okgo.cookie.store.SPCookieStore;
import com.lzy.okgo.interceptor.HttpLoggingInterceptor;

import net.qiujuer.genius.kit.handler.Run;

import java.io.File;
import java.util.concurrent.TimeUnit;
import java.util.logging.Level;

import dagger.android.AndroidInjector;
import dagger.android.DaggerApplication;
import io.reactivex.Single;
import io.reactivex.android.schedulers.AndroidSchedulers;
import okhttp3.OkHttpClient;


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
        initOkGo();
    }

    @Override
    protected abstract AndroidInjector<? extends DaggerApplication> applicationInjector();

    public static Context getInstance() {
        return instance;
    }


    private void initOkGo() {
        //        Thread.setDefaultUncaughtExceptionHandler(handler);
        HttpLoggingInterceptor loggingInterceptor = new HttpLoggingInterceptor("yyq_OkGo");
//        //log打印级别，决定了log显示的详细程度
        loggingInterceptor.setPrintLevel(HttpLoggingInterceptor.Level.BODY);
//        //log颜色级别，决定了log在控制台显示的颜色
        loggingInterceptor.setColorLevel(Level.WARNING);
//        builder.addInterceptor(loggingInterceptor);
//        使用数据库保持cookie，如果cookie不过期，则一直有效
        OkHttpClient.Builder builder = new OkHttpClient.Builder();
        builder.cookieJar(new CookieJarImpl(new SPCookieStore(this)));


        //全局的读取超时时间
        builder.readTimeout(15*1000, TimeUnit.MILLISECONDS);
        //全局的写入超时时间
        builder.writeTimeout(15*1000, TimeUnit.MILLISECONDS);
        //全局的连接超时时间
        builder.connectTimeout(15*1000, TimeUnit.MILLISECONDS);
        OkGo.getInstance().init(this)                       //必须调用初始化
                .setOkHttpClient(builder.build())               //建议设置OkHttpClient，不设置将使用默认的
//                .setCacheMode(CacheMode.NO_CACHE)               //全局统一缓存模式，默认不使用缓存，可以不传
//                .setCacheTime(CacheEntity.CACHE_NEVER_EXPIRE)   //全局统一缓存时间，默认永不过期，可以不传
                .setRetryCount(0);                               //设置重连次数，不包含第一次访问

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
        Single.fromCallable(()->{
            Toast.makeText(instance, string , Toast.LENGTH_SHORT).show();
            return  "Toast ";
        })
                .subscribeOn(AndroidSchedulers.mainThread())
                .subscribe();
//        Run.onUiAsync(new Action() {
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
