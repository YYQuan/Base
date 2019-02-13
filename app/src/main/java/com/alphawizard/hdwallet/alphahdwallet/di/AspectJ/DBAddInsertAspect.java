package com.alphawizard.hdwallet.alphahdwallet.di.AspectJ;

import com.alphawizard.hdwallet.alphahdwallet.di.AspectJ.Annotation.DBAddInsert;
import com.alphawizard.hdwallet.alphahdwallet.di.dagger.support.Repositor.RealmDBOperator;
import com.alphawizard.hdwallet.alphahdwallet.di.dagger.support.Repositor.SharedPreferenceRepository;
import com.alphawizard.hdwallet.common.util.MyLogger;
import com.hujiang.library.annotation.RealmsInsert;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;

import java.lang.reflect.Method;

import io.realm.RealmObject;

/**
 * Created by Tony Shen on 16/3/28.
 */
@Aspect
public class DBAddInsertAspect {
    private static final String POINTCUT_METHOD = "execution(@com.alphawizard.hdwallet.alphahdwallet.di.AspectJ.Annotation.DBAddInsert * *(..))";

    @Around("execution(!synthetic * *(..)) && onDBAddInsertMethod()")
    public  Object  doRealmsInsertMethod(final ProceedingJoinPoint joinPoint) throws Throwable {
          return dbAddMethod(joinPoint);
    }

//    @Pointcut("@within(com.alphawizard.hdwallet.alphahdwallet.di.AspectJ.Annotation.RealmsInsert)|| " +
//    @Pointcut("@annotation(com.alphawizard.hdwallet.alphahdwallet.di.AspectJ.Annotation.DBAddInsert)")
    @Pointcut("@within(com.alphawizard.hdwallet.alphahdwallet.di.AspectJ.Annotation.DBAddInsert)||" +
            "@annotation(com.alphawizard.hdwallet.alphahdwallet.di.AspectJ.Annotation.DBAddInsert)")
    public void onDBAddInsertMethod() {


    }

    private Object dbAddMethod(final ProceedingJoinPoint joinPoint) throws Throwable {


        MyLogger.jLog().e(" realmsMethod  ");
        MethodSignature signature = (MethodSignature) joinPoint.getSignature();
        Method method = signature.getMethod();
        DBAddInsert daAddInsert = method.getAnnotation(DBAddInsert.class);
//        RealmsInsert prefsInsert = method.getAnnotation(RealmsInsert.class);
        Object result = null;
        if (daAddInsert !=null) {

            boolean isUpdate = daAddInsert.isUpdate();

            result = joinPoint.proceed();
            String type = ((MethodSignature) joinPoint.getSignature()).getReturnType().toString();

//            if (!"void".equalsIgnoreCase(type)) {
//                String className = ((MethodSignature) joinPoint.getSignature()).getReturnType().getCanonicalName();
//                MyLogger.jLog().e("className  :"+className);
//                if ("int".equals(className) || "java.lang.Integer".equals(className)) {
//
//                } else if ("boolean".equals(className) || "java.lang.Boolean".equals(className)) {
//
//                } else if ("float".equals(className) || "java.lang.Float".equals(className)) {
//
//                } else if ("long".equals(className) || "java.lang.Long".equals(className)) {
//
//                } else if ("java.lang.String".equals(className)) {
//
//                }
//            }

            if(result instanceof  RealmObject) {
                MyLogger.jLog().d("result  is  realmObject Sub class");
                if (isUpdate) {
                    RealmDBOperator.getInstance()
                            .insertOrUpdate((RealmObject) result)
                            .subscribe();
                } else {
                    RealmDBOperator.getInstance()
                            .insert((RealmObject) result)
                            .subscribe();
                }
            }else{
                MyLogger.jLog().e("result  is not realmObject Sub class");
            }

        } else {
            // 不影响原来的流程
            result = joinPoint.proceed();
        }
        return result;
    }
}
