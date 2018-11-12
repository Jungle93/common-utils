package com.jungle.common.utils;

import java.util.Collection;

/**
 * @author jungle
 * @version V1.0
 * @date 2018/11/12 10:36
 * @Title: Assert.java
 * @Package com.jungle.common.utils
 * @Description: 判断类型
 * copyright © 2018- github.com
 */
public final class Assert {

    /**
     * no-args constructor.
     */
    private Assert() {/**/ }

    /**
     * 当入参为空时，使用所给的message抛出{@link IllegalArgumentException}
     *
     * @param o       目标对象
     * @param message 异常消息
     */
    public static void notNull(Object o, String message) {
        if (o == null) {
            throw new IllegalArgumentException(message);
        }
    }

    /**
     * 当入参集合类型为空时，使用所给的message抛出{@link IllegalArgumentException}
     *
     * @param c       目标集合
     * @param message 异常消息
     */
    public static void notZeroSize(Collection c, String message) {
        if (c.size() == 0) {
            throw new IllegalArgumentException(message);
        }
    }

}
