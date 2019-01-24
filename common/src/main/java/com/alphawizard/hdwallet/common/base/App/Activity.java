package com.alphawizard.hdwallet.common.base.App;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.LayoutRes;
import android.support.annotation.Nullable;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;

import com.alibaba.android.arouter.launcher.ARouter;
import com.alphawizard.hdwallet.common.R;
import com.alphawizard.hdwallet.common.util.MyLogger;
import com.gyf.barlibrary.ImmersionBar;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.cookie.CookieJarImpl;
import com.lzy.okgo.cookie.store.SPCookieStore;
import com.lzy.okgo.interceptor.HttpLoggingInterceptor;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.List;
import java.util.concurrent.TimeUnit;
import java.util.logging.Level;

//import butterknife.ButterKnife;
//import butterknife.Unbinder;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import dagger.android.support.DaggerAppCompatActivity;
import okhttp3.OkHttpClient;

/**
 *
 * 封装的Activity的基类
 * Created by Yqquan on 2018/6/29.
 */

public abstract class Activity extends DaggerAppCompatActivity {

    boolean isFirstCreate = true;
    protected ImmersionBar mImmersionBar;
    Unbinder unbinder;

    public  static  void show(Context context){
        context.startActivity(new Intent(context, Activity.class));
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        // 5.0以上系统状态栏透明
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            Window window = getWindow();
            window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
            window.getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                    | View.SYSTEM_UI_FLAG_LAYOUT_STABLE);
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            window.setStatusBarColor(Color.TRANSPARENT);
        } else if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        }
        if (mImmersionBar == null) {
            mImmersionBar = ImmersionBar.with(this);
        }
        EventBus.getDefault().register(this);
        initWindow();
        super.onCreate(savedInstanceState);
        ARouter.getInstance().inject(this);
        if(initArgs(getIntent().getExtras())){
            setContentView(getContentLayoutID());
            initBeforeInitData();
            initWidget();

            initData();
        }else{
            finish();
        }
    }



    /**
     * 在initArgs前，但在initData之后被调用
     */
    public void initBeforeInitData() {
    }

    /**
     * Activity的资源ID
     * @return  返回资源ID
     */
    @LayoutRes
    public abstract int getContentLayoutID();


    /**
     *  args正确的情况下，用来初始化数据
     */
    public void initData() {
        if(isFirstCreate) {
            isFirstCreate = !isFirstCreate;
            initFirst();
        }
    }

    public void initFirst() {
    }


    /**
     * 用来初始化控件
     * 已经绑定了ButterKnife
     */
    public void initWidget() {
        unbinder = ButterKnife.bind(this);
    }

    /**
     * 参数初始化
     * 如果初始化失败那么后续的初始化函数就不会被执行 ，并且该activity 会被finish ，
     * @param savedInstanceState
     * @return  true： 参数初始化成功  false： 参数初始化失败
     */
    public boolean initArgs(Bundle savedInstanceState) {
      return true;
    }


    /**
     * 最先被调用的初始化
     */
    public void initWindow() {

    }


    /**
     * 导航栏的返回键的回调
     * @return
     */
    @Override
    public boolean onSupportNavigateUp() {
        return super.onSupportNavigateUp();
    }

    /**
     *  返回键的回调
     *  先判断与之绑定的Fragment是否有对backPressed的处理，
     *  如果Fragment都没有对backPressed处理的话，那么就finish掉activity
     */
    @Override
    public void onBackPressed() {
        super.onBackPressed();

        List<android.support.v4.app.Fragment> list = getSupportFragmentManager().getFragments();
        //这里的fragments中的fragment的优先级，有需要的话应该是可以指定的
        if(list.size()>0) {
            for (android.support.v4.app.Fragment fragment : list) {
                if (fragment instanceof Fragment) {
                    if(((Fragment) fragment).onBackPressed()){
                        return;
                    }
                }
            }
        }

        finish();

    }

    protected void initStatusBar() {
//        mImmersionBar.transparentStatusBar().init();
        mImmersionBar.statusBarDarkFont(true)
                .navigationBarColor(R.color.white)
                .init();
    }

    @SuppressWarnings("ResourceType")
    protected void initStatusBarWithColor() {
        mImmersionBar.statusBarColor(R.color.colorPrimary).statusBarView(R.id.status_bar_view).init();
    }

    @SuppressLint("ResourceType")
    protected void initStatusBarWithColor(boolean dark,int color) {
        mImmersionBar.statusBarDarkFont(dark).statusBarColor(color).statusBarView(R.id.status_bar_view).init();
    }

    @SuppressWarnings("ResourceType")
    protected void initImmersionBarWithView() {
        mImmersionBar.statusBarView(R.id.status_bar_view).init();
    }

    @Override
    protected void onDestroy() {

        unbinder.unbind();
        if (mImmersionBar!= null) {
            mImmersionBar.destroy();
        }
//        EventBus.getDefault().register(this);
        EventBus.getDefault().unregister(this);
        OkGo.getInstance().cancelAll();
        super.onDestroy();
    }

//    要把  eventbus  封装到   基类当中的话， 就必须要在基类注册一个接收方法才行
    @Subscribe(sticky = true, threadMode = ThreadMode.BACKGROUND, priority = 300)
    public void eventBusDemo(String  str) {
//        Application.showToast("base activity  : "+str);
    }
}
