package com.alphawizard.hdwallet.alphahdwallet.functionModule.ConfirmSend;

import com.alphawizard.hdwallet.alphahdwallet.functionModule.send.SendContract;
import com.alphawizard.hdwallet.alphahdwallet.functionModule.send.SendViewModule;
import com.alphawizard.hdwallet.common.presenter.BaseContract;

public interface ConfirmSendContract{
    interface View extends BaseContract.BaseView<ConfirmSendContract.Presenter,ConfirmSendViewModule> {
    }

    interface Presenter extends BaseContract.BasePresenter<ConfirmSendContract.View,ConfirmSendViewModule> {
    }
}
