package com.alphawizard.hdwallet.alphahdwallet.functionModule.main;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v7.app.AppCompatActivity;

import android.view.MenuItem;
import android.widget.TextView;

import com.alphawizard.hdwallet.alphahdwallet.R;
import com.alphawizard.hdwallet.alphahdwallet.functionModule.fristLaunch.FirstLaunchActivity;
import com.alphawizard.hdwallet.common.presenter.BaseContract;
import com.alphawizard.hdwallet.common.presenter.BasePresenterToolbarActivity;
import com.alphawizard.hdwallet.common.util.Log;

import javax.inject.Inject;

public class MainActivity extends BasePresenterToolbarActivity<BaseContract.BasePresenter> {

    @Inject
    MainContract.Presenter mPresenter;

    private TextView mTextMessage;

    public static void show(Context context) {
        context.startActivity(new Intent(context, MainActivity.class));
    }

    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            switch (item.getItemId()) {
                case R.id.navigation_home:
                    mTextMessage.setText(R.string.title_home);
                    return true;
                case R.id.navigation_dashboard:
                    mTextMessage.setText(R.string.title_dashboard);
                    return true;
                case R.id.navigation_notifications:
                    mTextMessage.setText(R.string.title_notifications);
                    return true;
            }
            return false;
        }
    };

    @Override
    public int getContentLayoutID() {
        return R.layout.activity_main;
    }

    @Override
    public BaseContract.BasePresenter initPresenter() {
        return mPresenter;
    }


    @Override
    public void initFirst() {
        super.initData();
        mTextMessage = (TextView) findViewById(R.id.message);
        BottomNavigationView navigation = (BottomNavigationView) findViewById(R.id.navigation);
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);

        if(mPresenter!=null){
            Log.d("mPresenter : "+mPresenter.toString());
        }else{
            Log.d("mPresenter :  null");
        }
    }
}
