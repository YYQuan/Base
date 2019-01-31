package com.alphawizard.hdwallet.alphahdwallet.functionModule.DemoWallet;


import com.alphawizard.hdwallet.alphahdwallet.di.base.Scope.FragmentScoped;

import com.alphawizard.hdwallet.alphahdwallet.di.dagger.base.Scope.ActivityScoped;
import com.alphawizard.hdwallet.alphahdwallet.functionModule.DemoWallet.Fragment.AccountFragment;
import com.alphawizard.hdwallet.alphahdwallet.functionModule.DemoWallet.Fragment.AccountsFragment;
import com.alphawizard.hdwallet.alphahdwallet.functionModule.DemoWallet.Fragment.DimensionFragment;
import com.alphawizard.hdwallet.alphahdwallet.functionModule.DemoWallet.Fragment.LaunchPresenter;
import com.alphawizard.hdwallet.alphahdwallet.functionModule.DemoWallet.Fragment.PersenterInterface;
import com.alphawizard.hdwallet.alphahdwallet.functionModule.DemoWallet.Fragment.Presenter;

import dagger.Binds;
import dagger.Module;
import dagger.android.ContributesAndroidInjector;

/**
 * This is a Dagger module. We use this to pass in the View dependency to the
 * {@link }.
 */
@Module
public abstract class WalletModule {

    /**
     *
     * 想要在WalletModule 注入某对象  必须先实现其接口
     *
     * @Binds
     * abstract  接口名称   方法名（实现类 ）
     * @param presenter
     * @return
     */

    //     实现类    方法名称（实现类 ）  这样的注入是   不行的
    @ActivityScoped
    @Binds
    abstract LaunchPresenter taskPresenter(LaunchPresenter presenter);

    //    接口名称     方法名称（实现类 ）  这样的注入才能通过
    @ActivityScoped
    @Binds
    abstract PersenterInterface walletPresenter(Presenter presenter);

    /**
     * 要在WalletActivity中 使用
     * @Injedct
     * AccountFragment  fragment 来注入 目标的话，
     * 那么 需要 AccoutnFragment 的 构造方法  加上  @Inject 注解
     *
     *
     * 另外fragment  必须带上 @ ContributesAndroidInjector  来把fragment 和 activity 关联起来  否则fragment中不能使用inject来获取对象
     * @return
     */
    @FragmentScoped
    @ContributesAndroidInjector
    abstract AccountFragment accountFragment();

    @FragmentScoped
    @ContributesAndroidInjector
    abstract AccountsFragment accountsFragment();

    @FragmentScoped
    @ContributesAndroidInjector
    abstract DimensionFragment dimensionFragment();
}
