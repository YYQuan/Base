package com.alphawizard.hdwallet.alphahdwallet.di.AspectJ;

import com.alphawizard.hdwallet.alphahdwallet.di.AspectJ.Annotation.RealmsInserttt;
import com.alphawizard.hdwallet.alphahdwallet.di.dagger.support.Repositor.RealmDBOperator;
import com.alphawizard.hdwallet.alphahdwallet.di.dagger.support.Repositor.SharedPreferenceRepository;

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
public class RealmInsertAspecttttt {
    private static final String POINTCUT_METHOD = "execution(@com.alphawizard.hdwallet.alphahdwallet.di.AspectJ.Annotation.RealmsInserttt * *(..))";

    @Around("execution(!synthetic * *(..)) && onRealmsInsertMethod()")
    public void doRealmsInsertMethod(final ProceedingJoinPoint joinPoint) throws Throwable {
          realmsMethod(joinPoint);
    }

//    @Pointcut("@within(com.alphawizard.hdwallet.alphahdwallet.di.AspectJ.Annotation.RealmsInserttt)|| " +
//    @Pointcut("@annotation(com.alphawizard.hdwallet.alphahdwallet.di.AspectJ.Annotation.RealmsInserttt)")
    @Pointcut("@within(com.alphawizard.hdwallet.alphahdwallet.di.AspectJ.Annotation.RealmsInserttt)||@annotation(com.alphawizard.hdwallet.alphahdwallet.di.AspectJ.Annotation.RealmsInserttt)")

    public void onRealmsInsertMethod() {


    }

    private Object realmsMethod(final ProceedingJoinPoint joinPoint) throws Throwable {

        MethodSignature signature = (MethodSignature) joinPoint.getSignature();
        Method method = signature.getMethod();

        RealmsInserttt prefsInsert = method.getAnnotation(RealmsInserttt.class);
        Object result = null;
        if (prefsInsert !=null) {
            String key = SharedPreferenceRepository.GAS_LIMIT_KEY;

            result = joinPoint.proceed();
            String type = ((MethodSignature) joinPoint.getSignature()).getReturnType().toString();

            if (!"void".equalsIgnoreCase(type)) {
                String className = ((MethodSignature) joinPoint.getSignature()).getReturnType().getCanonicalName();
                SharedPreferenceRepository  sharedPreference =  SharedPreferenceRepository.getInstance();
                if ("int".equals(className) || "java.lang.Integer".equals(className)) {
                    sharedPreference.putInt(key, (Integer) result);
                } else if ("boolean".equals(className) || "java.lang.Boolean".equals(className)) {
                    sharedPreference.putBoolean(key,(Boolean) result);
                } else if ("float".equals(className) || "java.lang.Float".equals(className)) {
                    sharedPreference.putFloat(key,(Float) result);
                } else if ("long".equals(className) || "java.lang.Long".equals(className)) {
                    sharedPreference.putLong(key,(Long) result);
                } else if ("java.lang.String".equals(className)) {
                    sharedPreference.putString(key,(String) result);
                }
            }
        } else {
            // 不影响原来的流程
            result = joinPoint.proceed();
        }
        return result;

//        MyLogger.jLog().e(" realmsMethod  ");
//        return joinPoint.proceed();


//        MyLogger.jLog().e(" realmsMethod  ");
//        MethodSignature signature = (MethodSignature) joinPoint.getSignature();
//        Method method = signature.getMethod();
//
//        RealmsInserttt prefsInsert = method.getAnnotation(RealmsInserttt.class);
//        Object result = null;
//        if (prefsInsert !=null) {
//
//            boolean isUpdate = prefsInsert.isUpdate();
//
//            result = joinPoint.proceed();
//            String type = ((MethodSignature) joinPoint.getSignature()).getReturnType().toString();
//
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
//
//            if(isUpdate) {
//                RealmDBOperator.getInstance()
//                        .insertOrUpdate((RealmObject) result)
//                        .subscribe();
//            }else{
//                RealmDBOperator.getInstance()
//                        .insert((RealmObject) result)
//                        .subscribe();
//            }
//        } else {
//            // 不影响原来的流程
//            result = joinPoint.proceed();
//        }
//        return result;
    }
}
