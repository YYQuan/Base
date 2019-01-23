package com.alphawizard.hdwallet.common.base.App;


import android.graphics.drawable.Drawable;
import android.support.v7.app.ActionBar;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.alphawizard.hdwallet.common.R;
import com.alphawizard.hdwallet.common.util.UnitConvertUtil;
/**
 *
 *       在 其 布局文件里  加入
 *       <include layout="@layout/title_view" />
 *
 *       这样通用的  Toolbar 就能使用了
 */
public abstract class ToolbarActivity extends Activity {


    @Override
    public void initWidget() {
        super.initWidget();

        ImageView ivBack = (ImageView) findViewById(R.id.iv_back);
        ivBack.setOnClickListener(view->onBackPressed());
    }






    public void initTitle(final android.app.Activity activity, String title) {
        ImageView ivBack = (ImageView) findViewById(R.id.iv_back);
        TextView tvTitle = (TextView) findViewById(R.id.tv_title);
        if (ivBack != null) {
            ivBack.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    activity.finish();
                }
            });
        }

        if (tvTitle != null) tvTitle.setText(title);
    }



    public void setLeftButton(int img){
        ImageView ivBack = findViewById(R.id.iv_back);
        ivBack.setImageResource(img);
    }

    public  void  setLeftButtonListener(View.OnClickListener  listener){

        ImageView ivBack = findViewById(R.id.iv_back);
        ivBack.setOnClickListener(listener);
    }


    public void setBackgroundColor(int color){
        RelativeLayout rlTitle = findViewById(R.id.rl_title);
        rlTitle.setBackgroundColor(color);
    }

    public void setBackgroundDrawable(Drawable drawable){
        RelativeLayout rlTitle = findViewById(R.id.rl_title);
        rlTitle.setBackground(drawable);
    }

    public void setToolbarTitleColor(int color){
        TextView tvTitle = findViewById(R.id.tv_title);
        tvTitle.setTextColor(color);
    }

    public void setRightButton(String name){
        TextView tvRight = (TextView) findViewById(R.id.tv_title_right);
        tvRight.setVisibility(View.VISIBLE);
        tvRight.setText(name);
    }

    public void setRightButtonColor(int color){
        TextView tvRight = (TextView) findViewById(R.id.tv_title_right);
        tvRight.setTextColor(color);
        tvRight.setVisibility(View.VISIBLE);
    }

    public void setRightImage(int resId){
        ImageView ivRight = findViewById(R.id.image_title_right_icon);
        ivRight.setImageResource(resId);
        ivRight.setVisibility(View.VISIBLE);
    }

    public  void  setRightImageClickListener(View.OnClickListener listener ){
        ImageView ivRight = findViewById(R.id.image_title_right_icon);
        ivRight.setOnClickListener(listener);
    }

    public  void  addRightImage(int resId,View.OnClickListener listener){
        ImageView ivAddView;
        ivAddView  = new ImageView(this);
        RelativeLayout.LayoutParams lp=new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.WRAP_CONTENT,RelativeLayout.LayoutParams.WRAP_CONTENT);


        lp.width = UnitConvertUtil.dp2px(this,50);
        lp.height = UnitConvertUtil.dp2px(this,50);
        lp.setMargins(0,0,UnitConvertUtil.dp2px(this,10),0);
        ivAddView.setLayoutParams(lp);
        ivAddView.setScaleType(ImageView.ScaleType.FIT_XY);
        ivAddView.setPadding(UnitConvertUtil.dp2px(this,10),UnitConvertUtil.dp2px(this,10),UnitConvertUtil.dp2px(this,10),UnitConvertUtil.dp2px(this,10));
        ivAddView.setImageResource(resId);

        LinearLayout   layout =  findViewById(R.id.layout_title_right);
        layout.addView(ivAddView);
        ivAddView.setOnClickListener(listener);
    }



}
