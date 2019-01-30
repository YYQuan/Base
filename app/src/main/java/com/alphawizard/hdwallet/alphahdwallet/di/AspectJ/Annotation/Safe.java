package com.alphawizard.hdwallet.alphahdwallet.di.AspectJ.Annotation;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.METHOD;

/**
 * Created by Tony Shen on 16/3/23.
 *
 *  用来 代替 try   catch
 *
 */
@Target({METHOD})
@Retention(RetentionPolicy.RUNTIME)
public @interface Safe {

    String callBack() default "";
}
