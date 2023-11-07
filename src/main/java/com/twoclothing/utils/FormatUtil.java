package com.twoclothing.utils;

import java.sql.Timestamp;
import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;

// 格式化工具包
public class FormatUtil {

    /**
     * @return yyyy/MM/dd HH:mm:ss
     */
    public static String timestampDateTime(Timestamp timestamp) {
        if (timestamp == null) return "";
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
        return dateFormat.format(timestamp);
    }

    /**
     * @return yyyy/MM/dd HH:mm:ss
     */
    public static String timestampNoSecond(Timestamp timestamp) {
        if (timestamp == null) return "";
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm");
        return dateFormat.format(timestamp);
    }

    /**
     * @return yyyy/MM/dd
     */
    public static String timestampDate(Timestamp timestamp) {
        if (timestamp == null) return "";
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd");
        return dateFormat.format(timestamp);
    }

    /**
     * @return Thousands Separators
     */
    public static String numberThousandsSeparators(Integer number) {
        if (number == null) return "";
        DecimalFormat decimalFormat = new DecimalFormat("#,###");
        return decimalFormat.format(number);
    }

    /**
     * @return Thousands Separators
     */
    public static String numberThousandsSeparators(String number) {
        if (number == null || number.isEmpty()) return "";
        DecimalFormat decimalFormat = new DecimalFormat("#,###");
        return decimalFormat.format(Integer.parseInt(number));
    }

    /**
     * @param formattedNumber "#,###"'s String
     * @return Integer Number
     */
    public static Integer parseFormattedNumber(String formattedNumber) {
        DecimalFormat decimalFormat = new DecimalFormat("#,###");
        try {
            return decimalFormat.parse(formattedNumber).intValue();
        } catch (ParseException e) {
            e.printStackTrace();
            return null;
        }
    }
}
