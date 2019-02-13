package com.alphawizard.hdwallet.alphahdwallet.di.AspectJ;

import android.os.Looper;
import android.util.Log;

import com.alphawizard.hdwallet.common.util.MyLogger;

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
public class StopDoubleClickAspect {

    private final static int SPACE_TIME = 1500;//2次点击的间隔时间，单位ms
    private static long lastClickTime;

    @Around("execution(!synthetic * *(..)) && onStopDoubleClickMethod()")
    public void doStopDoubleClickMethod(final ProceedingJoinPoint joinPoint) throws Throwable {
        stopDoubleClickMethod(joinPoint);
    }



    @Pointcut("execution(@com.alphawizard.hdwallet.alphahdwallet.di.AspectJ.Annotation.StopDoubleClick * *(..))")
    public void onStopDoubleClickMethod() {
    }

    private void stopDoubleClickMethod(final ProceedingJoinPoint joinPoint) throws Throwable {
        if (!isDoubleClick()) {
            MyLogger.jLog().d("NOT  DOUBLE  CLICK ");
            joinPoint.proceed();
        }else{
            MyLogger.jLog().d("DOUBLE  CLICK ");
        }
    }



    public synchronized static boolean isDoubleClick() {
        long currentTime = System.currentTimeMillis();
        boolean isClick;
        if (currentTime - lastClickTime > SPACE_TIME) {
            isClick = false;
        } else {
            isClick = true;
        }
        lastClickTime = currentTime;
        return isClick;
    }
}
