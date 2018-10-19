package com.alphawizard.hdwallet.alphahdwallet.functionModule.Wallet;

import android.content.Context;
import android.content.Intent;



public class WalletRouter {
    public void open(Context context) {
        Intent intent = new Intent(context, WalletActivity.class);
        context.startActivity(intent);
    }
}
