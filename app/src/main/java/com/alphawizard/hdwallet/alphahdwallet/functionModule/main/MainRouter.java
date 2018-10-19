package com.alphawizard.hdwallet.alphahdwallet.functionModule.main;

import android.content.Context;
import android.content.Intent;

import com.alphawizard.hdwallet.alphahdwallet.functionModule.fristLaunch.FirstLaunchActivity;

public class MainRouter {
    public void open(Context context) {
        Intent intent = new Intent(context, MainActivity.class);
        context.startActivity(intent);
    }
}
