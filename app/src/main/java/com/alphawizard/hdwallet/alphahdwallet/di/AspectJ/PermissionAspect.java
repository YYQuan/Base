package com.alphawizard.hdwallet.alphahdwallet.di.AspectJ;

import android.Manifest;
import android.app.Activity;
import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.content.ContextCompat;
import android.util.Log;


import com.alphawizard.hdwallet.alphahdwallet.di.AspectJ.Annotation.Permission;
import com.alphawizard.hdwallet.common.util.MyLogger;
import com.safframework.tony.common.reflect.Reflect;
import com.safframework.tony.common.reflect.ReflectException;
import com.safframework.tony.common.utils.Preconditions;


import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;

import java.lang.reflect.Method;

import pub.devrel.easypermissions.EasyPermissions;

/**
 * @FileName: com.safframework.aop.PermissionAspect
 * @author: Tony Shen
 * @date: 2018-11-06 15:56
 * @version: V1.0 <描述当前版本功能>
 *
 *
 *      @Permission(value = {Manifest.permission.WRITE_EXTERNAL_STORAGE,
 *                              Manifest.permission.READ_EXTERNAL_STORAGE,
 *                              Manifest.permission.CAMERA},
 *                              requestCode = RC)
 *      @AfterPermissionGranted(RC)
 *
 *      如果说想要使用
 *
 */
@Aspect
public class PermissionAspect {


    private static final String POINTCUT_METHOD = "execution(@com.alphawizard.hdwallet.alphahdwallet.di.AspectJ.Annotation.Permission * *(..))";
    private static final String POINTCUT_PREMISSION_CALLBACK_METHOD = "execution(@pub.devrel.easypermissions.AfterPermissionGranted * *(..))";
    @Pointcut(POINTCUT_METHOD)
    public void methodAnnotatedWithPermission() {
    }

    @Pointcut(POINTCUT_PREMISSION_CALLBACK_METHOD)
    public void methodAnnotatedWithPermissionCallback() {
    }

    @Around("methodAnnotatedWithPermission()")
    public Object permissionMethod(final ProceedingJoinPoint joinPoint) throws Throwable {
        MyLogger.jLog().e("permissionMethod  ");
        MethodSignature signature = (MethodSignature) joinPoint.getSignature();
        Method method = signature.getMethod();

        Permission permission = method.getAnnotation(Permission.class);

        String[] permissions = permission.value();
        int requestCode = permission.requestCode();

        Object object = joinPoint.getThis();

        Context context = null;

        if (object instanceof Activity) {
            context = (Activity) object;
        } else if (object instanceof FragmentActivity) {
            context = (FragmentActivity) object;
        } else if (object instanceof Fragment) {
            context = ((Fragment) object).getContext();
        } else if (object instanceof Service) {
            context = (Service) object;
        }

        Object o = null;

        if (checkPermissions(context, permissions)) {
            o = joinPoint.proceed();
            MyLogger.jLog().e("checkPermissions  pass  ");
        } else {
            MyLogger.jLog().e("  request permissions  "  +requestCode);

            String  tips = "被拒绝之后 ， 再次申请时会弹出的tips";
//             申请权限
            if (object instanceof Activity) {
                EasyPermissions.requestPermissions((Activity)object, tips/* tips  to  user*/,requestCode/*标识*/, permissions);
            } else if (object instanceof FragmentActivity) {
                EasyPermissions.requestPermissions((Activity)object, tips/* tips  to  user*/,requestCode/*标识*/, permissions);
            } else if (object instanceof Fragment) {
                EasyPermissions.requestPermissions((Fragment) object, tips/* tips  to  user*/,requestCode/*标识*/, permissions);
            }
//            不支持服务中申请
//            else if (object instanceof Service) {
//                requestPerm(context,requestCode,permissions);
//            }

        }


        return o;
    }


//  以easy permission 的 AfterPermissionGranted 注解为切点 来进行 注入
    @Around("methodAnnotatedWithPermissionCallback()")
    public void permissionMethodCallback(final ProceedingJoinPoint joinPoint) throws Throwable {

        MyLogger.jLog().d("permissionMethodCallback");

        MethodSignature signature = (MethodSignature) joinPoint.getSignature();
        Method method = signature.getMethod();

        Permission permission = method.getAnnotation(Permission.class);
//          有可能只使用了 AfterPermissionGranted 注解
        if(permission == null){
            joinPoint.proceed();
            MyLogger.jLog().d("permissionMethodCallback    permissions ==  null");
            return ;
        }
        String[] permissions = permission.value();



        for(String str : permissions) {
            MyLogger.jLog().d("permissionMethodCallback  "+ str);
        }
        int requestCode = permission.requestCode();
        MyLogger.jLog().d("permissionMethodCallback  :"+  requestCode);
        Object object = joinPoint.getThis();

        Context context = null;

        if (object instanceof Activity) {
            context = (Activity) object;
        } else if (object instanceof FragmentActivity) {
            context = (FragmentActivity) object;
        } else if (object instanceof Fragment) {
            context = ((Fragment) object).getContext();
        } else if (object instanceof Service) {
            context = (Service) object;
        }
        if (checkPermissions(context, permissions)) {
            joinPoint.proceed();
            MyLogger.jLog().e("checkPermissions  pass  ");
        }
        String callBackHas = permission.hasPermissionCallBack();
        String callBackNotHas = permission.notHasPermissionCallBack();

        if (Preconditions.isNotBlank(callBackHas)) {

            try {
                Reflect.on(joinPoint.getTarget()).call(callBackHas);
            } catch (ReflectException exception) {
                exception.printStackTrace();
                MyLogger.jLog().e("no method "+callBackHas);
            }
        }
        if (Preconditions.isNotBlank(callBackNotHas)) {

            try {
                Reflect.on(joinPoint.getTarget()).call(callBackNotHas);
            } catch (ReflectException exception) {
                exception.printStackTrace();
                MyLogger.jLog().e("no method "+callBackNotHas);
            }
        }
    }


//     给onRequestPermissionsResult 方法 加入  easy permission 的  Forward results to EasyPermissions
//  匹配  activity 和 fragment 的 onRequestPermissionsResult
    @After("execution(* *.onRequestPermissionsResult(*,*,*))")
    public void onResumeMethod(JoinPoint joinPoint) throws Throwable {
        MyLogger.jLog().d("permissionOnRequestPermissionsResult");



//        onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults)

        Object[]  objects = joinPoint.getArgs();
        int  request =  (int)objects[0];
        String[]  permissions =  (String[])objects[1];
        int[]  grantResults =  (int[])objects[2];
        EasyPermissions.onRequestPermissionsResult(request, permissions, grantResults, joinPoint.getThis());

    }




    private boolean checkPermission(Context context, String permission) {

        if (ContextCompat.checkSelfPermission(context, permission) == PackageManager.PERMISSION_GRANTED) {
            return true;
        }
        return false;
    }

    private boolean checkPermissions(Context context, String[] permissions) {

        for(String permission : permissions) {
            if (!checkPermission(context, permission)) {
                return false;
            }
        }
        return true;
    }





}
