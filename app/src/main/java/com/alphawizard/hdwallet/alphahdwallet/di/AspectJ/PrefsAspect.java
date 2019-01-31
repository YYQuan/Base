package com.alphawizard.hdwallet.alphahdwallet.di.AspectJ;

import com.alphawizard.hdwallet.alphahdwallet.di.AspectJ.Annotation.Prefs;
import com.alphawizard.hdwallet.alphahdwallet.di.dagger.support.Repositor.SharedPreferenceRepository;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;

import java.lang.reflect.Method;

/**
 * Created by Tony Shen on 16/3/28.
 */
@Aspect
public class PrefsAspect {

    @Around("execution(!synthetic * *(..)) && onPrefsMethod()")
    public Object doPrefsMethod(final ProceedingJoinPoint joinPoint) throws Throwable {
        return prefsMethod(joinPoint);
    }

    @Pointcut("@within(com.alphawizard.hdwallet.alphahdwallet.di.AspectJ.Annotation.Prefs)||@annotation(com.alphawizard.hdwallet.alphahdwallet.di.AspectJ.Annotation.Prefs)")
    public void onPrefsMethod() {
    }

    private Object prefsMethod(final ProceedingJoinPoint joinPoint) throws Throwable {

        MethodSignature signature = (MethodSignature) joinPoint.getSignature();
        Method method = signature.getMethod();

        Prefs prefs = method.getAnnotation(Prefs.class);
        Object result = null;
        if (prefs!=null) {
            String key = prefs.key();

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
    }
}
