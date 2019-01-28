package com.alphawizard.hdwallet.alphahdwallet.di.Repositor;

import com.alphawizard.hdwallet.alphahdwallet.entity.db.TestDBBean;

import org.json.JSONArray;

import java.util.Date;
import java.util.List;

import io.reactivex.Flowable;
import io.reactivex.Single;
import io.realm.RealmObject;
import io.realm.RealmResults;

public interface RealmDBOperatorType {
    /**
     * 添加(性能优于下面的saveOrUpdate（）方法)
     *
     * @param object
     * @return 保存或者修改是否成功
     */
    public Single<Boolean> insert(RealmObject object) ;

    /**
     * 添加(性能优于下面的saveOrUpdateBatch（）方法)
     *
     * @param list
     * @return 批量保存是否成功
     */
    public Single<Boolean> insert(List<? extends RealmObject> list);

    /**
     * 添加或者修改
     *
     * @param object
     * @return 保存或者修改是否成功
     */
    public Single<Boolean> insertOrUpdate(RealmObject object);




    /**
     * 添加或者修改(性能优于下面的saveOrUpdateBatch（）方法)
     *
     * @param list
     * @return 保存或者修改是否成功
     */
    public Single<Boolean> insertOrUpdateBatch(List<? extends RealmObject> list);


    /**
     * 批量添加或者修改
     *
     * @param list
     * @return 全部保存成功 或 全部失败
     */
    public  Single<Boolean> saveOrUpdateBatch(List<? extends RealmObject> list) ;


    /**
     * save or update RealmObject from json data
     *
     * @param jsonObject json数据
     * @param clazz      具体类型
     * @return 已经保存的对象
     */
    public Single<RealmObject> saveOrUpdateFromJSON(Class<? extends RealmObject> clazz, String jsonObject);

    /**
     * batch save or update from json array
     *
     * @param json  json数组
     * @param clazz 类型
     * @return 批量保存json对象是否成功
     */
    public Single<Boolean> saveOrUpdateFromJSONBatch(Class<? extends RealmObject> clazz, JSONArray json);

    /**
     * 删除当前表中所有数据
     *
     * @param clazz
     * @return
     */
    public Single<Boolean> deleteAll(Class<? extends RealmObject> clazz) ;

    /**
     * 按照id删除制定的记录
     *
     * @param clazz       类型
     * @param idFieldName id字段的名称
     * @param id          id字段值
     * @return
     */
    public Single<Boolean> deleteById(Class<? extends RealmObject> clazz, String idFieldName, int id) ;

    /**
     * 查询所有
     *
     * @return 返回结果集合
     */
     public<T  extends  RealmObject> Single<List<T  >> findAll(Class< T > clazz);

    public<T extends  RealmObject> Single<T> findEqaul(Class< T> clazz,String fieldName,String value);

    public<T extends  RealmObject> Single<List<T>> findEqaul(Class< T> clazz,String fieldName,long value);

    public<T extends  RealmObject> Single<List<T>> findGreater(Class< T> clazz,String fieldName,long value);

    public<T extends  RealmObject> Single<List<T>> findLess(Class< T> clazz,String fieldName,long value);

    public<T extends  RealmObject> Single<List<T>> findEqaul(Class< T> clazz,String fieldName,Date value);

    public<T extends  RealmObject> Single<List<T>> findGreater(Class< T> clazz,String fieldName,Date value);

    public<T extends  RealmObject> Single<List<T>> findLess(Class< T> clazz,String fieldName,Date value);

    /**
     * 清空数据库
     *
     * @return
     */
    public Single<Boolean> clearDatabase() ;
}
