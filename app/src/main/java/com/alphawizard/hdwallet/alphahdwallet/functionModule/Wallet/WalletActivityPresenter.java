/*
 * Copyright 2016, The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.alphawizard.hdwallet.alphahdwallet.functionModule.Wallet;

import com.alphawizard.hdwallet.alphahdwallet.di.Scope.ActivityScoped;
import com.alphawizard.hdwallet.common.presenter.BasePresenter;

import javax.inject.Inject;


@ActivityScoped
final class WalletActivityPresenter extends BasePresenter<WalletActivityContract.View,WalletViewModule> implements WalletActivityContract.Presenter {

//     dagger 不需要 public  , 避免 误操作 就不把构造函数暴露出去
    @Inject
    WalletActivityPresenter() {
    }

    @Override
    public String getBalance() {
        return null;
    }

    @Override
    public String getEthValue() {
        return null;
    }

    @Override
    public String getAccounts() {
        return null;
    }

    @Override
    public void getAddressPolicy() {

    }

    @Override
    public void sendTransaction() {

    }

    @Override
    public void getTransactionRecord() {

    }
}
