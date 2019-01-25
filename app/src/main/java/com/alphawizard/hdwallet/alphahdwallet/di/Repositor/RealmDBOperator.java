package com.alphawizard.hdwallet.alphahdwallet.di.Repositor;

import com.alphawizard.hdwallet.alphahdwallet.entity.db.TestDBBean;

import org.json.JSONArray;

import java.util.List;

import io.reactivex.Flowable;
import io.reactivex.Observable;
import io.reactivex.Single;
import io.reactivex.schedulers.Schedulers;
import io.realm.Realm;
import io.realm.RealmObject;
import io.realm.RealmResults;

/**
 *  注意  realm  只能在 创建的线程当中  操作
 */
public class RealmDBOperator implements RealmDBOperatorType {


//    private Realm   realm = getDefaultInstance();
//
//
//    public  Realm  getDefaultInstance(){
//        if(realm ==null){
//            realm = Realm.getDefaultInstance();
//        }
//        return  realm;
//
//    }




    /**
     * 添加(性能优于下面的saveOrUpdate（）方法)
     *
     * @param object
     * @return 保存或者修改是否成功
     */
    @Override
    public Single<Boolean> insert(RealmObject object) {
        Realm   realm = Realm.getDefaultInstance();
        try {
            realm.beginTransaction();
            realm.insert(object);
            realm.commitTransaction();
            return Single.just(true);
        } catch (Exception e) {
            e.printStackTrace();
            realm.cancelTransaction();
            return Single.just(false);
        }
//        return Single.fromCallable(()->{
//            Realm   realm = Realm.getDefaultInstance();
//            try {
//                realm.beginTransaction();
//                realm.insert(object);
//                realm.commitTransaction();
//                return true;
//            } catch (Exception e) {
//                e.printStackTrace();
//                realm.cancelTransaction();
//                return false;
//            }
//        }).subscribeOn(Schedulers.io());
    }

    /**
     * 添加(性能优于下面的saveOrUpdateBatch（）方法)
     *
     * @param list
     * @return 批量保存是否成功
     */
    @Override
    public Single<Boolean> insert(List<? extends RealmObject> list) {
        return Single.fromCallable(()-> {
            Realm   realm = Realm.getDefaultInstance();
            try {
                realm.beginTransaction();
                realm.insert(list);
                realm.commitTransaction();
                return true;
            } catch (Exception e) {
                e.printStackTrace();
                realm.cancelTransaction();
                return false;
            }
        }).subscribeOn(Schedulers.io());
    }

    /**
     * 添加或者修改(性能优于下面的saveOrUpdate（）方法)
     *
     * @param object
     * @return 保存或者修改是否成功
     */
    @Override
    public Single<Boolean> insertOrUpdate(RealmObject object) {
        return Single.fromCallable(()-> {
            Realm   realm = Realm.getDefaultInstance();
            try {
                realm.beginTransaction();
                realm.insertOrUpdate(object);
                realm.commitTransaction();
                return true;
            } catch (Exception e) {
                e.printStackTrace();
                realm.cancelTransaction();
                return false;
            }
        }).subscribeOn(Schedulers.io());
    }

    /**
     * 添加或者修改(性能优于下面的saveOrUpdateBatch（）方法)
     *
     * @param list
     * @return 保存或者修改是否成功
     */
    @Override
    public Single<Boolean> insertOrUpdateBatch(List<? extends RealmObject> list) {
        return Single.fromCallable(()-> {
            Realm   realm = Realm.getDefaultInstance();
            try {
                realm.beginTransaction();
                realm.insertOrUpdate(list);
                realm.commitTransaction();
                return true;
            } catch (Exception e) {
                e.printStackTrace();
                realm.cancelTransaction();
                return false;
            }
        }).subscribeOn(Schedulers.io());
    }



    /**
     * 批量添加或者修改
     *
     * @param list
     * @return 全部保存成功 或 全部失败
     */
    @Override
    public Single<Boolean> saveOrUpdateBatch(List<? extends RealmObject> list) {
        return Single.fromCallable(()-> {
            Realm   realm = Realm.getDefaultInstance();
            try {
                realm.beginTransaction();
                realm.copyToRealmOrUpdate(list);
                realm.commitTransaction();
                return true;
            } catch (Exception e) {
                e.printStackTrace();
                realm.cancelTransaction();
                return false;
            }
        }).subscribeOn(Schedulers.io());
    }


    /**
     * save or update RealmObject from json data
     *
     * @param jsonObject json数据
     * @param clazz      具体类型
     * @return 已经保存的对象
     */
    @Override
    public Single<RealmObject> saveOrUpdateFromJSON(Class<? extends RealmObject> clazz, String jsonObject) {
        return Single.fromCallable(()-> {
            Realm   realm = Realm.getDefaultInstance();
            RealmObject RealmObject = null;
            try {
                realm.beginTransaction();
                RealmObject = realm.createOrUpdateObjectFromJson(clazz, jsonObject);
                realm.commitTransaction();
                return RealmObject;
            } catch (Exception e) {
                e.printStackTrace();
                realm.cancelTransaction();
                return RealmObject;
            }
        }).subscribeOn(Schedulers.io());
    }

    /**
     * batch save or update from json array
     *
     * @param json  json数组
     * @param clazz 类型
     * @return 批量保存json对象是否成功
     */
    @Override
    public Single<Boolean> saveOrUpdateFromJSONBatch(Class<? extends RealmObject> clazz, JSONArray json) {
        return Single.fromCallable(()-> {
            Realm   realm = Realm.getDefaultInstance();
            try {
                realm.beginTransaction();
                realm.createOrUpdateAllFromJson(clazz, json);
                realm.commitTransaction();
                return true;
            } catch (Exception e) {
                e.printStackTrace();
                realm.cancelTransaction();
                return false;
            }
        }).subscribeOn(Schedulers.io());
    }

    /**
     * 删除当前表中所有数据
     *
     * @param clazz
     * @return
     */
    @Override
    public Single<Boolean> deleteAll(Class<? extends RealmObject> clazz) {
        return Single.fromCallable(()-> {
            Realm   realm = Realm.getDefaultInstance();
            try {
                realm.beginTransaction();
                realm.delete(clazz);
                realm.commitTransaction();
                return true;
            } catch (Exception e) {
                e.printStackTrace();
                realm.cancelTransaction();
                return false;
            }
        }).subscribeOn(Schedulers.io());
    }

    /**
     * 按照id删除制定的记录
     *
     * @param clazz       类型
     * @param idFieldName id字段的名称
     * @param id          id字段值
     * @return
     */
    @Override
    public Single<Boolean> deleteById(Class<? extends RealmObject> clazz, String idFieldName, int id) {
        return Single.fromCallable(()-> {
            Realm   realm = Realm.getDefaultInstance();
            try {
                realm.beginTransaction();
                realm.where(clazz).equalTo(idFieldName, id).findAll().deleteFirstFromRealm();
                realm.commitTransaction();
                return true;
            } catch (Exception e) {
                e.printStackTrace();
                realm.cancelTransaction();
                return false;
            }
        }).subscribeOn(Schedulers.io());
    }

    /**
     * 查询所有
     *
     * @return 返回结果集合
     */

    @Override
    public  Flowable<RealmResults<TestDBBean>> findAll(Class<TestDBBean> clazz){
        return  Realm.getDefaultInstance().where(clazz).findAll().asFlowable()
                .subscribeOn(Schedulers.io());
    }
//    public<T   extends RealmResults<Bean> , Bean extends RealmObject  > Flowable<T> findAll(Class<Bean> clazz) {
//        return (Flowable<T>)  realm.where(clazz).findAll().asFlowable()
//                .subscribeOn(Schedulers.io());
//    }

    /**
     * 清空数据库
     *
     * @return
     */
    @Override
    public Single<Boolean> clearDatabase() {
        return Single.fromCallable(()-> {
            Realm   realm = Realm.getDefaultInstance();
            try {
                realm.beginTransaction();
                realm.deleteAll();
                realm.commitTransaction();
                return true;
            } catch (Exception e) {
                e.printStackTrace();
                realm.cancelTransaction();
                return false;
            }
        }).subscribeOn(Schedulers.io());
    }
}
