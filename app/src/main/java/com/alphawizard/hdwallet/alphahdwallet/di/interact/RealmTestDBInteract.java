package com.alphawizard.hdwallet.alphahdwallet.di.interact;


import com.alphawizard.hdwallet.alphahdwallet.di.Repositor.RealmDBOperatorType;
import com.alphawizard.hdwallet.alphahdwallet.entity.db.TestDBBean;
import com.alphawizard.hdwallet.alphahdwallet.service.DemoServiceInterface;

import io.reactivex.Flowable;
import io.reactivex.Single;
import io.realm.RealmObject;
import io.realm.RealmResults;

/**
 *  在这一级  对  操作的db  bean  进行分类
 *  免得集成太多  很混乱。
 */
public class RealmTestDBInteract {

	RealmDBOperatorType realmDBOperatorType;

	public RealmTestDBInteract(RealmDBOperatorType realmDBOperatorType) {
		this.realmDBOperatorType =  realmDBOperatorType;
	}

	public  Single<Boolean>  addTestBean (TestDBBean bean){
	    return  realmDBOperatorType.insert(bean);
	}

	public Flowable<RealmResults<TestDBBean>> findAll(Class<TestDBBean> clazz) {
		return  realmDBOperatorType.findAll(clazz);
	}

}
