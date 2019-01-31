package com.alphawizard.hdwallet.alphahdwallet.di.AspectJ.Annotation;

import android.annotation.TargetApi;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.METHOD;

/**
 * Created by Tony Shen on 16/3/28.
 *
 *  该 注解用于  把返回值保存到  share Perference
 */
@TargetApi(14)
@Target({METHOD})
@Retention(RetentionPolicy.RUNTIME)
public @interface RealmsInsert {


//    用于判断  是插入还是更新    这个关乎插入性能
//      默认为true  true的话 无论是插入还是更新都OK
    boolean isUpdate() default  false ;
}
