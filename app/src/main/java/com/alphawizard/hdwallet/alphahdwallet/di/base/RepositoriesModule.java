package com.alphawizard.hdwallet.alphahdwallet.di.base;

import android.content.Context;

import com.alphawizard.hdwallet.alphahdwallet.db.Repositor.PreferenceRepositoryType;
import com.alphawizard.hdwallet.alphahdwallet.db.Repositor.SharedPreferenceRepository;

import com.alphawizard.hdwallet.alphahdwallet.service.DemoServiceInterface;
import com.alphawizard.hdwallet.alphahdwallet.service.DemoService;
import java.io.File;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import okhttp3.OkHttpClient;


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



}
