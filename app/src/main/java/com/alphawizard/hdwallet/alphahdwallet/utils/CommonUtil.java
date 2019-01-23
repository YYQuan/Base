package com.alphawizard.hdwallet.alphahdwallet.utils;

import android.annotation.TargetApi;
import android.app.Activity;
import android.content.ClipboardManager;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Color;
import android.net.Uri;
import android.os.Build;
import android.provider.Settings;
import android.support.design.widget.TabLayout;
import android.support.v4.content.FileProvider;
import android.text.TextUtils;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.alphawizard.hdwallet.common.util.UnitConvertUtil;

import java.io.File;
import java.lang.reflect.Field;
import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

/**
 * Created by Administrator on 2017/6/16 0016.
 */

public class CommonUtil {


//    /**
//     * 保留4位小数
//     *
//     * @param s 需要转化的数字
//     * @return
//     */
//    public static String toPoint4(String s) {
//        DecimalFormat decimalFormat = new DecimalFormat("0.0000");
//        String d4 = decimalFormat.format(Double.valueOf(s));
//        if (d4.indexOf(".") > 0) {
//            d4 = d4.replaceAll("0+?$", "");//去掉多余的0
//            d4 = d4.replaceAll("[.]$", "");//如最后一位是.则去掉
//        }
//        return d4;
//    }
    public static <T> boolean isEmpty(List<T> list) {
        if (list == null || list.size() == 0) {
            return true;
        }
        return false;
    }



    /**
     * double加减运算获取具体的精度
     *
     * @param number1
     * @param number2
     * @return
     */
    public static String getDecimal(double number1, double number2) {
        BigDecimal b1 = new BigDecimal(number1 + "");
        BigDecimal b2 = new BigDecimal(number2 + "");
        return String.valueOf(b1.subtract(b2).doubleValue());
    }

    /**
     * 获取版本号
     *
     * @param context
     * @return
     */
    public static int getVerCode(Context context) {
        int verCode = -1;
        try {
            verCode = context.getPackageManager().getPackageInfo(Constant.PACKAGE_NAME, 0).versionCode;
        } catch (PackageManager.NameNotFoundException e) {
            Log.e("msg", e.getMessage());
        }
        return verCode;
    }

    /**
     * 获取版本名称
     *
     * @param context
     * @return
     */
    public static String getVerName(Context context) {
        String verName = "";
        try {
            verName = context.getPackageManager().getPackageInfo(
                    Constant.PACKAGE_NAME, 0).versionName;
        } catch (PackageManager.NameNotFoundException e) {
            Log.e("msg", e.getMessage());
        }
        return verName;
    }

    // Android Id
    public static String getAndroidId(Context context) {
        String androidId = Settings.Secure.getString(
                context.getContentResolver(), Settings.Secure.ANDROID_ID);
        return androidId;
    }

    /**
     * 安装程序
     */
    public static void install(Activity activity, File file) {
        if (Build.VERSION.SDK_INT >= 24) {//判读版本是否在7.0以上
//                            Uri apkUri = FileProvider.getUriForFile(MainActivity.this, "com.bihuex.app.fileprovider", file);
            Uri apkUri = FileProvider.getUriForFile(activity, "com.instantex.alpha.fileprovider", file);
            Intent install = new Intent(Intent.ACTION_VIEW);
            install.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            install.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION);//添加这一句表示对目标应用临时授权该Uri所代表的文件
            install.setDataAndType(apkUri, "application/vnd.android.package-archive");
            activity.startActivity(install);

        } else {
            Intent intent = new Intent(Intent.ACTION_VIEW);
            intent.setDataAndType(Uri.parse("file://" + file.getAbsolutePath()), "application/vnd.android.package-archive");
            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            activity.startActivity(intent);
        }
    }
    public static String trimEdit(EditText editText) {
        String s = editText.getText().toString().trim();
        return s;
    }

    public static boolean isTrim(CharSequence str) {
        return TextUtils.isEmpty(str);
    }

    /**
     *  复制到剪切板
     */
    public static void copy2clip(Context context, TextView textView) {
        //剪切板管理器
        ClipboardManager clipboardManager = (ClipboardManager) context.getSystemService(Context.CLIPBOARD_SERVICE);
        //复制字符串到剪切板
        clipboardManager.setText(textView.getText());
        Toast.makeText(context, "已经复制", Toast.LENGTH_SHORT).show();
    }




//    public static void start2Browser(Context context, String url) {
//        Intent intent = new Intent();
//        intent.setAction("android.intent.action.VIEW");
//        Uri content_url = Uri.parse(url);
//        intent.setData(content_url);
//        context.startActivity(intent);
//    }

//    public static String getVolUnit(float num) {
//
//        int e = (int) Math.floor(Math.log10(num));
//
//        if (e >= 8) {
//            return "亿手";
//        } else if (e >= 4) {
//            return "万手";
//        } else {
//            return "手";
//        }
//    }

//    public static int getVolUnitNum(float num) {
//
//        int e = (int) Math.floor(Math.log10(num));
//
//        if (e >= 8) {
//            return 8;
//        } else if (e >= 4) {
//            return 4;
//        } else {
//            return 1;
//        }
//    }

//    public static String getVolUnitText(int unit, float num) {
//        DecimalFormat mFormat;
//        if (unit == 1) {
//            mFormat = new DecimalFormat("#0");
//        } else {
//            mFormat = new DecimalFormat("#0.0000");
//        }
//        num = num / unit;
//        if (num == 0) {
//            return "0";
//        }
//        return mFormat.format(num);
//    }

//    public static String getDecimalFormatVol(float vol) {
//        DecimalFormat decimalFormat = new DecimalFormat("#0.0000");//构造方法的字符格式这里如果小数不足2位,会以0补足.
//        return decimalFormat.format(vol);//format 返回的是字符串
//    }


    /**
     * 设置  tablayout  tab  的下划线的长度
     * @param tabLayout
     * @param width     比字要宽多少
     */
    public static void reflex(final TabLayout tabLayout, final int width) {
        //了解源码得知 线的宽度是根据 tabView的宽度来设置的
        tabLayout.post(new Runnable() {
            @Override
            public void run() {
                try {
                    //拿到tabLayout的mTabStrip属性
                    LinearLayout mTabStrip = (LinearLayout) tabLayout.getChildAt(0);

                    int dp10 = UnitConvertUtil.dp2px(tabLayout.getContext(), width);

                    for (int i = 0; i < mTabStrip.getChildCount(); i++) {
                        View tabView = mTabStrip.getChildAt(i);

                        //拿到tabView的mTextView属性  tab的字数不固定一定用反射取mTextView
                        Field mTextViewField = tabView.getClass().getDeclaredField("mTextView");
                        mTextViewField.setAccessible(true);

                        TextView mTextView = (TextView) mTextViewField.get(tabView);

                        tabView.setPadding(0, 0, 0, 0);

                        //因为我想要的效果是   字多宽线就多宽，所以测量mTextView的宽度
                        int width = 0;
                        width = mTextView.getWidth();
                        if (width == 0) {
                            mTextView.measure(0, 0);
                            width = mTextView.getMeasuredWidth();
                        }

                        //设置tab左右间距为10dp  注意这里不能使用Padding 因为源码中线的宽度是根据 tabView的宽度来设置的
                        LinearLayout.LayoutParams params = (LinearLayout.LayoutParams) tabView.getLayoutParams();
                        params.width = width;
                        params.leftMargin = dp10;
                        params.rightMargin = dp10;
                        tabView.setLayoutParams(params);

                        tabView.invalidate();
                    }

                } catch (NoSuchFieldException e) {
                    e.printStackTrace();
                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                }
            }
        });

    }

    public static DecimalFormat getDecimal(int jd) {
        DecimalFormat dc = new DecimalFormat("0");
        if (jd == 0) {
            dc = new DecimalFormat("0");
        } else if (jd == 1) {
            dc = new DecimalFormat("0.0");
        } else if (jd == 2) {
            dc = new DecimalFormat("0.00");
        } else if (jd == 3) {
            dc = new DecimalFormat("0.000");
        } else if (jd == 4) {
            dc = new DecimalFormat("0.0000");
        } else if (jd == 5) {
            dc = new DecimalFormat("0.00000");
        } else if (jd == 6) {
            dc = new DecimalFormat("0.000000");
        } else if (jd == 7) {
            dc = new DecimalFormat("0.0000000");
        } else if (jd == 8) {
            dc = new DecimalFormat("0.00000000");
        } else if (jd == 9) {
            dc = new DecimalFormat("0.000000000");
        } else if (jd == 10) {
            dc = new DecimalFormat("0.0000000000");
        } else if (jd == 11) {
            dc = new DecimalFormat("0.00000000000");
        } else if (jd == 12) {
            dc = new DecimalFormat("0.000000000000");
        } else {
            dc = new DecimalFormat("0.00000000");
        }
        //dc.setRoundingMode(RoundingMode.FLOOR);
        return dc;
    }





}
