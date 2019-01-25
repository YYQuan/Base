package com.alphawizard.hdwallet.alphahdwallet.di.base;

import android.content.Context;

import com.alphawizard.hdwallet.alphahdwallet.di.Repositor.PreferenceRepositoryType;
import com.alphawizard.hdwallet.alphahdwallet.di.Repositor.RealmDBOperator;
import com.alphawizard.hdwallet.alphahdwallet.di.Repositor.RealmDBOperatorType;
import com.alphawizard.hdwallet.alphahdwallet.di.Repositor.SharedPreferenceRepository;

import com.alphawizard.hdwallet.alphahdwallet.service.DemoServiceInterface;
import com.alphawizard.hdwallet.alphahdwallet.service.DemoService;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;


/**
 *  用于绑定     各个仓库  和服务
 */
@Module
public class RepositoriesModule {

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

//	@Singleton
//	@Provides
//	RealmDBOperatorType provideRealmDBOperatorType() {
//		return new RealmDBOperator();
//	}


}
