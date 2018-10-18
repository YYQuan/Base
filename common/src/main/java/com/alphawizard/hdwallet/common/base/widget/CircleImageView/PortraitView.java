package com.alphawizard.hdwallet.common.base.widget.CircleImageView;

import android.content.Context;
import android.util.AttributeSet;
import com.alphawizard.hdwallet.common.R;
import com.bumptech.glide.RequestManager;


import de.hdodenhof.circleimageview.CircleImageView;

/**
 * Created by Yqquan on 2018/7/3.
 */

public class PortraitView extends CircleImageView {
    public PortraitView(Context context) {
        super(context);
    }

    public PortraitView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public PortraitView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
    }




    public  void setPortrait(RequestManager manager ,String uri){
        setPortrait(manager, R.drawable.default_portrait,uri);
    }

    public void  setPortrait(RequestManager manager,int  res,String uri){
        if(uri ==null){
            uri = "";
        }
        manager.load(uri)
                .placeholder(res)
                .centerCrop()
                .dontAnimate()  //  circleImageView中不能使用动画， 会有异常
                .into(this);
    }

}
