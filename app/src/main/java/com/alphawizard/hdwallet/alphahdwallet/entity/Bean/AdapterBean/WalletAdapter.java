package com.alphawizard.hdwallet.alphahdwallet.entity.Bean.AdapterBean;

import android.support.annotation.Nullable;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.alphawizard.hdwallet.alphahdwallet.R;
import com.alphawizard.hdwallet.alphahdwallet.entity.Wallet;
import com.alphawizard.hdwallet.common.base.widget.RecyclerView.AdapterCallback;
import com.alphawizard.hdwallet.common.base.widget.RecyclerView.RecyclerAdapter;


import java.util.List;

import butterknife.BindView;

public class WalletAdapter extends RecyclerAdapter<Wallet, WalletAdapter.WalletHolder> {
    private String  defautlAddress ;


    public WalletAdapter(int layoutResId, @Nullable List list) {
        super(layoutResId, list);
    }

    public WalletAdapter(@Nullable List list) {
        super(list);
    }

    public WalletAdapter(int layoutResId) {
        super(layoutResId);
    }

    @Override
    protected void holderConvert(WalletHolder helper, Wallet item) {
        if(item.address.equalsIgnoreCase(defautlAddress)){
           helper.setBackGroup(false);
        }else{
            helper.setBackGroup(true);
        }
        helper.setAddressText(item.address);
        helper.setContent(item.address);

    }

    public String getDefautlAddress() {
        return defautlAddress;
    }

    public void setDefautlAddress(String defautlAddress) {
        this.defautlAddress = defautlAddress;
    }



    @Override
    public void setAdapterCallback(AdapterCallback<WalletHolder, Wallet> adapterCallback) {
        super.setAdapterCallback(adapterCallback);
    }

    public class WalletHolder  extends RecyclerAdapter.BaseHolder {
        @Nullable
        @BindView(R.id.txt_title)
        TextView mTitle;
        @Nullable
        @BindView(R.id.txt_content)
        TextView mContent;
        @Nullable
        @BindView(R.id.iv_eth)
        public ImageView mImageView;

        public WalletHolder(View view) {
            super(view);
//            mTitle  = view.findViewById(R.id.txt_title);
//            mContent  = view.findViewById(R.id.txt_content);
//            mImageView  = view.findViewById(R.id.iv_eth);

        }



        public  void  setBackGroup(boolean  isShow){
            if(isShow){
                mImageView.setBackgroundResource(R.drawable.ic_not_selecte);
            }else{
                mImageView.setBackgroundResource(R.drawable.ic_selecte);
            }
        }

        public void  setAddressText(String  address){
            mTitle.setText(address);
        }


        public  void setContent(String  str){
            mContent.setText(str);
        }


    }


}
