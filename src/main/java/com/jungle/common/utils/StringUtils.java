package com.jungle.common.utils;

/**
 * @author jungle
 * @version V1.0
 * @date 2018/11/12 9:59
 * @Title: StringUtils.java
 * @Package com.jungle.common.utils
 * @Description: 字符串工具类
 * copyright © 2018- github.com
 */
public final class StringUtils {

    /**
     * no-args constructor.
     */
    private StringUtils() {/**/}

    /**
     * 判断目标串是否为空，包括：null、空串、空白串。
     *
     * @param s 目标串
     * @return s == null || s.length == 0 || s.trim.length == 0
     */
    public static boolean isEmpty(String s) {
        return s == null || s.length() == 0 || s.trim().length() == 0;
    }

    /**
     * 判断目标串不为空，详见{@link #isEmpty(String)}
     *
     * @param s 目标串
     * @return boolean
     */
    public static boolean isNotEmpty(String s) {
        return !isEmpty(s);
    }

    /**
     * 避免空指针的情况向下判断两个Object相等，调用{@link Object#equals(Object)}方法。
     *
     * @param o1    比较对象1
     * @param o2    比较对象2
     * @return  (o1 == null && o2 == null) || (o1 != null && o1.equals(o2)) || (o2 != null && o2.equals(o1))
     */
    public static boolean equalsWithoutNPE(Object o1, Object o2) {
        return (o1 == null && o2 == null) || (o1 != null && o1.equals(o2)) || (o2 != null && o2.equals(o1));
    }

}
