package com.alphawizard.hdwallet.alphahdwallet.di.AspectJ;

import com.alphawizard.hdwallet.alphahdwallet.di.AspectJ.Annotation.PrefsInsert;
import com.alphawizard.hdwallet.alphahdwallet.di.AspectJ.Annotation.RealmsInsert;
import com.alphawizard.hdwallet.alphahdwallet.di.dagger.support.Repositor.RealmDBOperator;
import com.alphawizard.hdwallet.alphahdwallet.di.dagger.support.Repositor.SharedPreferenceRepository;
import com.alphawizard.hdwallet.common.util.MyLogger;

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
public class RealmInsertAspect {

    @Around("execution(!synthetic * *(..)) && onRealmsMethod()")
    public Object doPrefsMethod(final ProceedingJoinPoint joinPoint) throws Throwable {
        return realmsMethod(joinPoint);
    }

    @Pointcut("@within(com.alphawizard.hdwallet.alphahdwallet.di.AspectJ.Annotation.RealmsInsert)||@annotation(com.alphawizard.hdwallet.alphahdwallet.di.AspectJ.AnnotationRealmsInsert)")
    public void onRealmsMethod() {
    }

    private Object realmsMethod(final ProceedingJoinPoint joinPoint) throws Throwable {

        MethodSignature signature = (MethodSignature) joinPoint.getSignature();
        Method method = signature.getMethod();

        RealmsInsert prefsInsert = method.getAnnotation(RealmsInsert.class);
        Object result = null;
        if (prefsInsert !=null) {

            boolean isUpdate = prefsInsert.isUpdate();

            result = joinPoint.proceed();
            String type = ((MethodSignature) joinPoint.getSignature()).getReturnType().toString();

            if (!"void".equalsIgnoreCase(type)) {
                String className = ((MethodSignature) joinPoint.getSignature()).getReturnType().getCanonicalName();
                MyLogger.jLog().e("className  :"+className);
                if ("int".equals(className) || "java.lang.Integer".equals(className)) {

                } else if ("boolean".equals(className) || "java.lang.Boolean".equals(className)) {

                } else if ("float".equals(className) || "java.lang.Float".equals(className)) {

                } else if ("long".equals(className) || "java.lang.Long".equals(className)) {

                } else if ("java.lang.String".equals(className)) {

                }
            }

            if(isUpdate) {
                RealmDBOperator.getInstance()
                        .insertOrUpdate((RealmObject) result)
                        .subscribe();
            }else{
                RealmDBOperator.getInstance()
                        .insert((RealmObject) result)
                        .subscribe();
            }
        } else {
            // 不影响原来的流程
            result = joinPoint.proceed();
        }
        return result;
    }
}
