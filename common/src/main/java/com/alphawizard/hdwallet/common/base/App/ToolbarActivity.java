package com.alphawizard.hdwallet.common.base.App;


import android.support.v7.app.ActionBar;
import android.support.v7.widget.Toolbar;
import android.view.View;

import com.alphawizard.hdwallet.common.R;


public abstract class ToolbarActivity extends Activity {
    protected Toolbar mToolbar;

    @Override
    public void initWidget() {
        super.initWidget();
        initToolbar((Toolbar) findViewById(R.id.toolbar));

    }

    public void initToolbar(Toolbar toolbar){
        mToolbar = toolbar;
        if(toolbar!=null){
            setSupportActionBar(toolbar);
        }

        initTitleNeedBack();

    }

    protected  void initTitleNeedBack(){
        //setSupportActionBar是 传入的是 Toolbar
//        get回来的是ActionBar
        ActionBar actionBar = getSupportActionBar();

//        把toolbar上的返回键设置为 实际的back按键 的那种效果
        if(actionBar!=null){
            actionBar.setDisplayHomeAsUpEnabled(true);
            actionBar.setHomeButtonEnabled(false);
        }


        mToolbar.setNavigationOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                onBackPressed();
            }
        });
    }


}
