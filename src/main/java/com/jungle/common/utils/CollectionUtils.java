package com.jungle.common.utils;

import java.util.HashMap;

/**
 * @author jungle
 * @version V1.0
 * @date 2018/11/12 11:15
 * @Title: CollectionUtils.java
 * @Package com.jungle.common.utils
 * @Description: 集合工具类
 * copyright © 2018- github.com
 */
public final class CollectionUtils {

    /**
     * no-args constructor.
     */
    private CollectionUtils() {/**/}

    /**
     * 构造map。
     *
     * @param k   键
     * @param v   值
     * @param <K> 键类型
     * @param <V> 值类型
     * @return HashMap   K   V
     */
    public static <K, V> HashMap<K, V> map(K k, V v) {
        HashMap<K, V> result = new HashMap<>();
        result.put(k, v);
        return result;
    }

    /**
     * 构造Map。
     *
     * @param k1  键1
     * @param v1  值1
     * @param k2  键2
     * @param v2  值2
     * @param <K> 键类型
     * @param <V> 值类型
     * @return HashMap K V
     */
    public static <K, V> HashMap<K, V> map(K k1, V v1,
                                           K k2, V v2) {
        HashMap<K, V> result = new HashMap<K, V>();
        result.put(k1, v1);
        result.put(k2, v2);
        return result;
    }

    /**
     * 构造Map。
     *
     * @param k1  键1
     * @param v1  值1
     * @param k2  键2
     * @param v2  值2
     * @param k3  键3
     * @param v3  值3
     * @param <K> 键类型
     * @param <V> 值类型
     * @return HashMap K V
     */
    public static <K, V> HashMap<K, V> map(K k1, V v1,
                                           K k2, V v2,
                                           K k3, V v3) {
        HashMap<K, V> result = new HashMap<K, V>();
        result.put(k1, v1);
        result.put(k2, v2);
        result.put(k3, v3);
        return result;
    }

    /**
     * 构造Map。
     *
     * @param k1  键1
     * @param v1  值1
     * @param k2  键2
     * @param v2  值2
     * @param k3  键3
     * @param v3  值3
     * @param k4  键4
     * @param v4  值4
     * @param <K> 键类型
     * @param <V> 值类型
     * @return HashMap K V
     */
    public static <K, V> HashMap<K, V> map(K k1, V v1,
                                           K k2, V v2,
                                           K k3, V v3,
                                           K k4, V v4) {
        HashMap<K, V> result = new HashMap<K, V>();
        result.put(k1, v1);
        result.put(k2, v2);
        result.put(k3, v3);
        result.put(k4, v4);
        return result;
    }

    /**
     * 构造Map。
     *
     * @param k1  键1
     * @param v1  值1
     * @param k2  键2
     * @param v2  值2
     * @param k3  键3
     * @param v3  值3
     * @param k4  键4
     * @param v4  值4
     * @param k5  键5
     * @param v5  值5
     * @param <K> 键类型
     * @param <V> 值类型
     * @return HashMap K V
     */
    public static <K, V> HashMap<K, V> map(K k1, V v1,
                                           K k2, V v2,
                                           K k3, V v3,
                                           K k4, V v4,
                                           K k5, V v5) {
        HashMap<K, V> result = new HashMap<K, V>();
        result.put(k1, v1);
        result.put(k2, v2);
        result.put(k3, v3);
        result.put(k4, v4);
        result.put(k5, v5);
        return result;
    }

    /**
     * 构造Map。
     *
     * @param k1  键1
     * @param v1  值1
     * @param k2  键2
     * @param v2  值2
     * @param k3  键3
     * @param v3  值3
     * @param k4  键4
     * @param v4  值4
     * @param k5  键5
     * @param v5  值5
     * @param k6  键6
     * @param v6  值6
     * @param <K> 键类型
     * @param <V> 值类型
     * @return HashMap K V
     */
    public static <K, V> HashMap<K, V> map(K k1, V v1,
                                           K k2, V v2,
                                           K k3, V v3,
                                           K k4, V v4,
                                           K k5, V v5,
                                           K k6, V v6) {
        HashMap<K, V> result = new HashMap<K, V>();
        result.put(k1, v1);
        result.put(k2, v2);
        result.put(k3, v3);
        result.put(k4, v4);
        result.put(k5, v5);
        result.put(k6, v6);
        return result;
    }

    /**
     * 构造数组，该方法可能造成过多类类型的问题，慎用。
     *
     * @param args T ...
     * @param <T>  类型
     * @return T []
     */
    public static <T> T[] array(T... args) {
        return args;
    }


}
