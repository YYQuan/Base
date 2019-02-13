package com.alphawizard.hdwallet.alphahdwallet.di.AspectJ.Annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Created by Tony Shen on 16/3/23.
 */
@Target({ElementType.METHOD, ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
//@Target({METHOD})
//@Retention(CLASS)
public @interface StopDoubleClick {
}
