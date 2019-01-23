package com.alphawizard.hdwallet.common.util;

import java.math.RoundingMode;
import java.text.DecimalFormat;

public class NumberStrCutOffUtil {
    /**
     *  字符串数字截断
     *  如
     *  toPoint2   :     "0.123456789"  ->  "0.12"
     *  toPoint4   :     "0.123456789"  ->  "0.1234"
     * @param s
     * @return
     */
    public static String toPoint2(String s) {
        DecimalFormat decimalFormat = new DecimalFormat("0.00");
        String d4 = decimalFormat.format(Double.valueOf(s));
        if (d4.indexOf(".") > 0) {
            d4 = d4.replaceAll("0+?$", "");//去掉多余的0
            d4 = d4.replaceAll("[.]$", "");//如最后一位是.则去掉
        }
        return d4;
    }

    public static String toPoint4down(String s) {
        DecimalFormat decimalFormat = new DecimalFormat("0.0000");
        decimalFormat.setRoundingMode(RoundingMode.FLOOR);
        String d4 = decimalFormat.format(Double.valueOf(s));
        if (d4.indexOf(".") > 0) {
            d4 = d4.replaceAll("0+?$", "");//去掉多余的0
            d4 = d4.replaceAll("[.]$", "");//如最后一位是.则去掉
        }
        return d4;
    }

    public static String toPointN(String s, int jd) {
        StringBuilder builder = new StringBuilder("0.");
        for (int i = 0; i < jd; i++) {
            builder.append("0");
        }

        DecimalFormat decimalFormat = new DecimalFormat(builder.toString());
        decimalFormat.setRoundingMode(RoundingMode.FLOOR);
        String d4 = decimalFormat.format(Double.valueOf(s));
        if (d4.indexOf(".") > 0) {
//            d4 = d4.replaceAll("0+?$", "");//去掉多余的0
            d4 = d4.replaceAll("[.]$", "");//如最后一位是.则去掉
        }
        return d4;
    }

    public static String toPointNHalfUp(String s, int jd) {
        StringBuilder builder = new StringBuilder("0.");
        for (int i = 0; i < jd; i++) {
            builder.append("0");
        }

        DecimalFormat decimalFormat = new DecimalFormat(builder.toString());
        decimalFormat.setRoundingMode(RoundingMode.HALF_UP);
        String d4 = decimalFormat.format(Double.valueOf(s));
        if (d4.indexOf(".") > 0) {
//            d4 = d4.replaceAll("0+?$", "");//去掉多余的0
            d4 = d4.replaceAll("[.]$", "");//如最后一位是.则去掉
        }
        return d4;
    }

    public static String toPointNHalfDown(String s, int jd) {
        StringBuilder builder = new StringBuilder("0.");
        for (int i = 0; i < jd; i++) {
            builder.append("0");
        }

        DecimalFormat decimalFormat = new DecimalFormat(builder.toString());
        decimalFormat.setRoundingMode(RoundingMode.HALF_DOWN);
        String d4 = decimalFormat.format(Double.valueOf(s));
        if (d4.indexOf(".") > 0) {
//            d4 = d4.replaceAll("0+?$", "");//去掉多余的0
            d4 = d4.replaceAll("[.]$", "");//如最后一位是.则去掉
        }
        return d4;
    }

    public static String toPoint6down(String s) {
        DecimalFormat decimalFormat = new DecimalFormat("0.000000");
        decimalFormat.setRoundingMode(RoundingMode.FLOOR);
        String d8 = decimalFormat.format(Double.valueOf(s));
        if (d8.indexOf(".") > 0) {
            d8 = d8.replaceAll("0+?$", "");//去掉多余的0
            d8 = d8.replaceAll("[.]$", "");//如最后一位是.则去掉
        }
        return d8;
    }

    public static String toPoint8down(String s) {
        DecimalFormat decimalFormat = new DecimalFormat("0.00000000");
        decimalFormat.setRoundingMode(RoundingMode.FLOOR);
        String d8 = decimalFormat.format(Double.valueOf(s));
        if (d8.indexOf(".") > 0) {
            d8 = d8.replaceAll("0+?$", "");//去掉多余的0
            d8 = d8.replaceAll("[.]$", "");//如最后一位是.则去掉
        }
        return d8;
    }
}
