package com.alphawizard.hdwallet.alphahdwallet.di.dagger.base;

import android.content.Context;


import com.alphawizard.hdwallet.alphahdwallet.di.dagger.support.Repositor.PreferenceRepositoryType;
import com.alphawizard.hdwallet.alphahdwallet.di.dagger.support.Repositor.RealmDBOperator;
import com.alphawizard.hdwallet.alphahdwallet.di.dagger.support.Repositor.RealmDBOperatorType;
import com.alphawizard.hdwallet.alphahdwallet.di.dagger.support.Repositor.SharedPreferenceRepository;
import com.alphawizard.hdwallet.alphahdwallet.di.dagger.support.service.DemoService;
import com.alphawizard.hdwallet.alphahdwallet.di.dagger.support.service.DemoServiceInterface;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;


/**
 *  用于绑定     各个仓库  和服务
 */
@Module
public class SupportModule {

	@Singleton
	@Provides
	PreferenceRepositoryType providePreferenceRepository(Context context) {
		return new SharedPreferenceRepository(context);
	}

	@Singleton
	@Provides
	DemoServiceInterface provideDemoServiceInterface() {
		return new DemoService();
	}

	@Singleton
	@Provides
	RealmDBOperatorType provideRealmDBOperatorType() {
		return new RealmDBOperator();
	}


}
