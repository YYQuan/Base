package com.alphawizard.hdwallet.alphahdwallet.di.AspectJ;

import android.os.Looper;
import android.util.Log;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;

import io.reactivex.BackpressureStrategy;
import io.reactivex.Flowable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

//
//import org.aspectj.lang.ProceedingJoinPoint;
//import org.aspectj.lang.annotation.Around;
//import org.aspectj.lang.annotation.Aspect;
//import org.aspectj.lang.annotation.Pointcut;


/**
 * Created by Tony Shen on 16/3/23.
 */
@Aspect
public class AsyncTestAspect {

    @Around("execution(!synthetic * *(..)) && onAsyncTestMethod()")
    public void doAsyncTestMethod(final ProceedingJoinPoint joinPoint) throws Throwable {
        asyncTestMethod(joinPoint);
    }

//    @Pointcut("@annotation(com.alphawizard.hdwallet.alphahdwallet.di.AspectJ.Annotation.Async)")

    @Pointcut("execution(@com.alphawizard.hdwallet.alphahdwallet.di.AspectJ.Annotation.AsyncTest * *(..))")
    public void onAsyncTestMethod() {
    }

    private void asyncTestMethod(final ProceedingJoinPoint joinPoint) throws Throwable {
        Log.d("YYQ","--->>>      asyncMethod");
        Flowable.create(e -> {
            Looper.prepare();
            try {
                joinPoint.proceed();
            } catch (Throwable throwable) {
                throwable.printStackTrace();
            }
            Looper.loop();
        }
                , BackpressureStrategy.BUFFER)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe();
    }
}
