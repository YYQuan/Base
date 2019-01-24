package com.alphawizard.hdwallet.alphahdwallet.functionModule.DemoFristLaunch;

import android.content.Context;
import android.content.Intent;

import com.alibaba.android.arouter.launcher.ARouter;

public class FirstLaunchRouter {

    public void open(Context context) {
//        Intent intent = new Intent(context, FirstLaunchActivity.class);
//        context.startActivity(intent);
        ARouter.getInstance().build("/test/activity/firstLaunch")
                .navigation();
    }
}
