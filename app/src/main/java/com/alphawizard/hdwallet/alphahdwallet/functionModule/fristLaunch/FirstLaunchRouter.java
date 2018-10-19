package com.alphawizard.hdwallet.alphahdwallet.functionModule.fristLaunch;

import android.content.Context;
import android.content.Intent;

public class FirstLaunchRouter {

    public void open(Context context) {
        Intent intent = new Intent(context, FirstLaunchActivity.class);
        context.startActivity(intent);
    }
}
