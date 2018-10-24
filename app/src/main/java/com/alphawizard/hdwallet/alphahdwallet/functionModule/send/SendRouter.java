package com.alphawizard.hdwallet.alphahdwallet.functionModule.send;

import android.content.Context;
import android.content.Intent;

import com.alphawizard.hdwallet.alphahdwallet.functionModule.Wallet.WalletActivity;

public class SendRouter {

    public void open(Context context) {
        Intent intent = new Intent(context, SendActivity.class);
        context.startActivity(intent);
    }

}
