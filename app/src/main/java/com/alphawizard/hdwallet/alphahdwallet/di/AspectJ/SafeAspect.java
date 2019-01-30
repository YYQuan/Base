package com.alphawizard.hdwallet.alphahdwallet.di.AspectJ;

import com.alphawizard.hdwallet.alphahdwallet.di.AspectJ.Annotation.Safe;
import com.alphawizard.hdwallet.common.util.MyLogger;
import com.safframework.log.L;
import com.safframework.tony.common.reflect.Reflect;
import com.safframework.tony.common.reflect.ReflectException;
import com.safframework.tony.common.utils.Preconditions;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;

import java.io.PrintWriter;
import java.io.StringWriter;
import java.lang.reflect.Method;

/**
 * Created by Tony Shen on 16/3/23.
 */
@Aspect
public class SafeAspect {

    private static final String POINTCUT_METHOD = "execution(@com.alphawizard.hdwallet.alphahdwallet.di.AspectJ.Annotation.Safe * *(..))";

    @Pointcut(POINTCUT_METHOD)
    public void methodAnnotatedWithSafe() {
    }

//    @Around("methodAnnotatedWithSafe()")
    @Around("execution(!synthetic * *(..)) && methodAnnotatedWithSafe()")
    public Object safeMethod(final ProceedingJoinPoint joinPoint) throws Throwable {

        MethodSignature signature = (MethodSignature) joinPoint.getSignature();
        Method method = signature.getMethod();

        Safe safe = method.getAnnotation(Safe.class);

        Object result = null;
        try {
            result = joinPoint.proceed();
        } catch (Throwable e) {
            MyLogger.jLog().w(getStringFromException(e));

            String callBack = safe.callBack();

            if (Preconditions.isNotBlank(callBack)) {

                try {
                    Reflect.on(joinPoint.getTarget()).call(callBack);
                } catch (ReflectException exception) {
                    exception.printStackTrace();
                    MyLogger.jLog().e("no method "+callBack);
                }
            }
        }
        return result;
    }

    private static String getStringFromException(Throwable ex) {
        StringWriter errors = new StringWriter();
        ex.printStackTrace(new PrintWriter(errors));
        return errors.toString();
    }
}
