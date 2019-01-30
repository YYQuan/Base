package com.alphawizard.hdwallet.alphahdwallet.di.AspectJ.Annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @FileName: com.safframework.aop.annotation.Permission
 * @author: Tony Shen
 * @date: 2018-11-06 15:53
 * @version: V1.0 <描述当前版本功能>
 *
 *      要使能  @AfterPermissionGranted 的话，
 *      那么就要 写出 重写 onRequestPermissionsResult  （哪怕 里面只需要super.onRequestPermissionsResult()）
 *      要这么做的原因是， aop 不能以没有被重写的父类的方法作为切点
 *
 *
 *     @Permission(value = {Manifest.permission.WRITE_EXTERNAL_STORAGE,
 *                              Manifest.permission.READ_EXTERNAL_STORAGE,
 *                              Manifest.permission.CAMERA},
 *                              requestCode = RC)
 *      @AfterPermissionGranted(RC)
 *      void  a(){
 *
 *      }
 *      如果说想要使用 该 注解的话  ，permission  注解只负责检测如果有权限就 执行a()  没有权限的话就去 申请权限。
 *      申请好了权限之后， a() 并不会被调用
 *      要是要有申请好权限之后 如果 用户授权则执行 a()的话 那么 就要  @Permission 和@AfterPermissionGranted
 *      配合使用 ， 这两个注解配合之下，只要有权限  ，a() 就会被执行。
 *
 *      再次强调  如果没有权限，只被Permission  注解的函数 ，不会被执行。
 *
 *      也可以把@Permission
 *      @AfterPermissionGranted
 *      拆开来 分别注释在两个不同的函数上
 *
 *
 *
 */
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
public @interface Permission {

    String[] value();

    int requestCode() default 1;

    String hasPermissionCallBack() default "";
    String notHasPermissionCallBack() default "";
}
