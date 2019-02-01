package com.hujiang.library;

/**
 *
 *
 *      该 lib  用于支持 aop    勿删
 *
 *      每次增加新的注解时  要 clean  project 下
 *
 *     @Aspect：声明切面，标记类
 *     @Pointcut(切点表达式)：定义切点，标记方法
 *     @Before(切点表达式)：前置通知，切点之前执行
 *     @Around(切点表达式)：环绕通知，切点前后执行
 *     @After(切点表达式)：后置通知，切点之后执行
 *     @AfterReturning(切点表达式)：返回通知，切点方法返回结果之后执行
 *     @AfterThrowing(切点表达式)：异常通知，切点抛出异常时执行
 *
 *
 *     不要在这里module 里写  aspectJ相关的代码
 *     跨包好像aspectJ  调用不起来。  暂时 不知道怎么解决
 *
 *     注意 在增加  aop 切点之后 要 重新编译一下才能够其效果
 *
 *     基本语法说明  pointcut  切点 规则
 *
 *        Async 该注解的地方 设置为切点
 *        @Pointcut("@annotation(com.alphawizard.hdwallet.alphahdwallet.di.AspectJ.Annotation.Async)")
 *
 *        @Pointcut("execution(@com.alphawizard.hdwallet.alphahdwallet.di.AspectJ.Annotation.Async/注解/ * /返回类型/ *(..) /函数名称以及参数类型/)")
 *
 *
 *     还有一点要注意
 *          aop 不能以没有写出来的代码 作为切点
 *
 *          比如说  有两个 activity  A ，B    A重写的  onResume 方法  ，哪怕只是  super.onresume ，  B 没有重写
 *          这时候 以  activity的onResume作为切点，   A  的  onresume 会被作为切点， 而 B 的onresume 不会被当做切点的
 *
 *
 *     do not    has   same   method name     expect   under @Pointcut  or  @Around
 *
 */
public interface Doc {

}
