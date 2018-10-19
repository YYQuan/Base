package com.alphawizard.hdwallet.alphahdwallet.di;

import android.content.Context;

import com.alphawizard.hdwallet.alphahdwallet.App;
import com.google.gson.Gson;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import okhttp3.OkHttpClient;

@Module
class ToolsModule {

	@Singleton
	@Provides
	 Gson provideGson() {
		return new Gson();
	}

	@Singleton
	@Provides
	OkHttpClient okHttpClient() {
		return new OkHttpClient.Builder()
//                .addInterceptor(new LogInterceptor())
                .build();
	}



}
