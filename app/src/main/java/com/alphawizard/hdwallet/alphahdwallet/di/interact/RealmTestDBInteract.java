package com.alphawizard.hdwallet.alphahdwallet.di.interact;


import com.alphawizard.hdwallet.alphahdwallet.di.Repositor.RealmDBOperatorType;
import com.alphawizard.hdwallet.alphahdwallet.entity.db.TestDBBean;
import com.alphawizard.hdwallet.alphahdwallet.service.DemoServiceInterface;

import java.util.List;

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

	public  Single<Boolean>  addTestBeans (List<TestDBBean> bean){
		return  realmDBOperatorType.insertOrUpdateBatch(bean);
	}

	public Single<Boolean> deleteAllTestBean() {

		return realmDBOperatorType.deleteAll(TestDBBean.class);


	}

	public Single<Boolean> deleteAll() {
		return realmDBOperatorType.clearDatabase();
	}

	public Single<List<TestDBBean>> findAllTestBean() {
//		return realmDBOperatorType.deleteAll(TestDBBean.class)
		return realmDBOperatorType.findAll(TestDBBean.class);
//		return  ;
	}

	public Single<TestDBBean> findEqaultTestBeanId(String id) {
		return  realmDBOperatorType.findEqaul(TestDBBean.class,"id",id);
	}

	public Single<List<TestDBBean>> findEqaultTestBeanAge(long age) {
		return  realmDBOperatorType.findEqaul(TestDBBean.class,"age",age);
	}

	public Single<List<TestDBBean>> findLessTestBeanAge(long age) {
		return  realmDBOperatorType.findLess(TestDBBean.class,"age",age);
	}

	public Single<List<TestDBBean>> findGreaterTestBeanAge(long age) {
		return  realmDBOperatorType.findGreater(TestDBBean.class,"age",age);
	}

}
