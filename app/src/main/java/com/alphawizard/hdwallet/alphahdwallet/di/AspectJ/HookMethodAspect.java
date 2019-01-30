package com.alphawizard.hdwallet.alphahdwallet.di.AspectJ;

import com.alphawizard.hdwallet.alphahdwallet.di.AspectJ.Annotation.HookMethod;
import com.alphawizard.hdwallet.common.util.MyLogger;
import com.safframework.tony.common.reflect.Reflect;
import com.safframework.tony.common.reflect.ReflectException;
import com.safframework.tony.common.utils.Preconditions;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;

import java.lang.reflect.Method;


/**
 * Created by Tony Shen on 2016/12/7.
 */
@Aspect
public class HookMethodAspect {

    private static final String POINTCUT_METHOD = "execution(@com.alphawizard.hdwallet.alphahdwallet.di.AspectJ.Annotation.HookMethod * *(..))";

    private static final String POINTCUT_CONSTRUCTOR = "execution(@com.alphawizard.hdwallet.alphahdwallet.di.AspectJ.Annotation.HookMethod *.new(..))";
//    @Pointcut("execution(@com.alphawizard.hdwallet.alphahdwallet.di.AspectJ.Annotation.Async * *(..))")

    @Pointcut(POINTCUT_METHOD)
    public void methodAnnotatedWithHookMethod() {
    }

    @Pointcut(POINTCUT_CONSTRUCTOR)
    public void constructorAnnotatedHookMethod() {
    }

//    @Around("methodAnnotatedWithHookMethod() || constructorAnnotatedHookMethod()")
    @Around("execution(!synthetic * *(..)) && methodAnnotatedWithHookMethod()")
    public void hookMethod(final ProceedingJoinPoint joinPoint) throws Throwable {
        MyLogger.jLog().d("hookMethod");
        MethodSignature signature = (MethodSignature) joinPoint.getSignature();
        Method method = signature.getMethod();

        HookMethod hookMethod = method.getAnnotation(HookMethod.class);

        if (hookMethod==null) return;

        String beforeMethod = hookMethod.beforeMethod();
        String afterMethod = hookMethod.afterMethod();

        if (Preconditions.isNotBlank(beforeMethod)) {
            try {
//                Reflect.on(joinPoint.getTarget())   ： 回到  切点处  调用 (beforeMethod);
                Reflect.on(joinPoint.getTarget()).call(beforeMethod);
            } catch (ReflectException e) {
                e.printStackTrace();
                MyLogger.jLog().e("no method "+beforeMethod);
            }
        }

        joinPoint.proceed();

        if (Preconditions.isNotBlank(afterMethod)) {
            try {
                Reflect.on(joinPoint.getTarget()).call(afterMethod);
            } catch (ReflectException e) {
                e.printStackTrace();
                MyLogger.jLog().e("no method "+afterMethod);
            }
        }
    }
}
