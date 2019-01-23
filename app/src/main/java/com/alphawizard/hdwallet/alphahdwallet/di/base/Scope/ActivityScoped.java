package com.alphawizard.hdwallet.alphahdwallet.di.base.Scope;

import com.alphawizard.hdwallet.alphahdwallet.di.base.AppComponent;

import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

import javax.inject.Scope;

/**
 * In Dagger, an unscoped component cannot depend on a scoped component. As
 * {@link AppComponent} is a scoped component ({@code @Singleton}, we create a custom
 * scope to be used by all fragment components. Additionally, a component with a specific scope
 * cannot have a sub component with the same scope.
 *
 * 自定义作用域   (用于activity)
 */
@Documented
@Scope
@Retention(RetentionPolicy.RUNTIME)
public @interface ActivityScoped {
}
