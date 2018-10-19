package com.alphawizard.hdwallet.alphahdwallet.functionModule.Wallet;

import android.content.Context;
import android.content.Intent;

import com.alphawizard.hdwallet.alphahdwallet.functionModule.main.MainActivity;

public class WalletRouter {
    public void open(Context context) {
        Intent intent = new Intent(context, MainActivity.class);
        context.startActivity(intent);
    }
}
