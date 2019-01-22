package com.alphawizard.hdwallet.alphahdwallet.functionModule.send;

import android.arch.lifecycle.ViewModelProviders;
import android.widget.Button;
import android.widget.EditText;

import com.alphawizard.hdwallet.alphahdwallet.R;
import com.alphawizard.hdwallet.alphahdwallet.di.ViewModule.SendViewModuleFactory;
import com.alphawizard.hdwallet.common.base.App.ToolbarActivity;



import javax.inject.Inject;

import butterknife.BindView;
import butterknife.OnClick;

public class SendActivity extends ToolbarActivity {



    @Inject
    SendViewModuleFactory viewModuleFactory;
    SendViewModule viewModel;


    @BindView(R.id.ed_eth_address)
    EditText  mAddresss;

    @BindView(R.id.ed_eth_amounts)
    EditText  mAmount;

    @BindView(R.id.btn_send)
    Button mSend;


    @Override
    public int getContentLayoutID() {
        return R.layout.activity_send;
    }

    @Override
    public void initData() {
        super.initData();
        viewModel = ViewModelProviders.of(this, viewModuleFactory)
                .get(SendViewModule.class);
    }

    @OnClick(R.id.btn_send)
    void onClickSend(){
        String address = mAddresss.getText().toString();
        String amounts = mAmount.getText().toString();
        viewModel.sendTransaction("aa",address,amounts);
    }
}

