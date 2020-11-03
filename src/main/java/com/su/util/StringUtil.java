package com.su.util;

/**
 * 字符串判断
 *
 * @author Sujinran
 */
public class StringUtil {
    /**
     * 字符串为空
     * @param str 字符串
     * @return
     */
    public static boolean isEmpty(String str) {
        //如果字符串为空返回true
        if ("".equals(str) || str == null) {
            return true;
        } else {
            return false;
        }
    }

    /**
     * 字符串不为空
     * @param str 字符串
     * @return
     */
    public static boolean isNotEmpty(String str) {
        //字符串不为空
        if (!"".equals(str) && str != null) {
            return true;
        } else {
            return false;
        }
    }
}

