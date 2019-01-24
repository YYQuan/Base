package com.alphawizard.hdwallet.alphahdwallet.di.base;

import com.alphawizard.hdwallet.alphahdwallet.App;
import com.alphawizard.hdwallet.alphahdwallet.di.interact.diModule.InteractModule;
import com.alphawizard.hdwallet.alphahdwallet.di.interact.diModule.RouterModule;


import javax.inject.Singleton;

import dagger.BindsInstance;
import dagger.Component;
import dagger.android.AndroidInjector;
import dagger.android.support.AndroidSupportInjectionModule;

/**
 * This is a Dagger component. Refer to {@link App} for the list of Dagger components
 * used in this application.
 * <p>
 * Even though Dagger allows annotating a {@link Component} as a singleton, the code
 * itself must ensure only one instance of the class is created. This is done in {@link
 * App}.
 * //{@link AndroidSupportInjectionModule}
 * // is the module from Dagger.Android that helps with the generation
 * // and location of subcomponents.
 *
 *
 *   新加一个activity   需要加的东西有
 *   1.在 activityBindingModule    里添加   对应的abstract  module类，
 *   2.添加该activity 所需要的  该activity所需的  view module
 * 比如    加入了sendActivity   就得 添加  SendViewModule的factory   到  ViewModuel 的声明中去
 * 这样activity 里才能通过
 *      SendViewModule viewModel = ViewModelProviders.of(this, viewModuleFactory).get(SendViewModule.class);
 *  这种方式 来获取到  所需的viewModule类
 *   3. 在di. ViewModelModule包里 添加  viewModule  的factory
 *     并且  在   intract.diModule 包里的ViewModuleModule类里  声明注入  factory
 *   4. 添加 该activity  的router
 *   5. 把router 声明到  intract.diModule包下 的 RouterModuel 里
 *
 *
 */
@Singleton
@Component(modules = {
        /**
         * 在application  出要使用到的
         */
        ApplicationModule.class,
        /**
        *用来 绑定   activity  和 其对应的所需的 内容（比如  用  mvp 的话 就可以 把presenter  注入）
        */
        ActivityBindingModule.class,
        /**
         * 提供  repositoriesModule  &  service
         * 这里是真正提供  功能的地方
         */
        RepositoriesModule.class,
        /**
         *  提供各个 Activity 所需的  ViewModule的Factory
         *  每一个activity  所需的功能组件 都是由 一个个的  InteractModule  来完成的
         */
        ViewModuleModule.class,
        /**
         *  功能组件 都是由 一个个的  InteractModule  来完成的
         *  完成某个功能的功能单位
         */
        InteractModule.class,
        /**
         *  提供跳转功能组件
         */
        RouterModule.class,
        /**
         * 提供工具类
         */
        ToolsModule.class,
        /**
         * 所需要的支持
         */
        AndroidSupportInjectionModule.class})
public interface AppComponent extends AndroidInjector<App> {



    // Gives us syntactic sugar. we can then do DaggerAppComponent.builder().application(this).build().inject(this);
    // never having to instantiate any modules or say which module we are passing the application to.
    // Application will just be provided into our app graph now.
    @Component.Builder
    interface Builder {

        @BindsInstance
        Builder application(App application);
        AppComponent build();
    }

}
