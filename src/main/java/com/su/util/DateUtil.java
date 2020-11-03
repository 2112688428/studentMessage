package com.su.util;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * 日期工具类
 *
 * @author Sujinran
 */
public class DateUtil {
    /**
     * 日期格式化
     * @param date 日期
     * @param format 字符串
     * @return
     */
    public static String formatDate(Date date, String format) {
        //定义字符串
        String result = "";
        //格式化日期
        SimpleDateFormat sdf = new SimpleDateFormat(format);
        if (date != null) {
            result = sdf.format(date);
        }
        return result;
    }

    /**
     * 格式化字符串
     * @param str 字符串
     * @param format 格式字符串
     * @return
     * @throws Exception
     */
    public static Date formatString(String str, String format) throws Exception {
        SimpleDateFormat sdf = new SimpleDateFormat(format);
        return sdf.parse(str);
    }
}
