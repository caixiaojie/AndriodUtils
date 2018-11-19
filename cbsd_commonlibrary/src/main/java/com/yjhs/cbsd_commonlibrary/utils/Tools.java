package com.yjhs.cbsd_commonlibrary.utils;

import com.google.gson.Gson;

import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.Map;

/**
 * author: Administrator
 * date: 2018/11/1 0001
 * desc:
 */
public class Tools {
    /**
     * 获取利用反射获取类里面的值和名称
     *
     * @param obj
     * @return
     * @throws IllegalAccessException
     */
    public static Map<String, String> objectToMap(Object obj) throws IllegalAccessException {
        Map<String, String> map = new HashMap<>();
        Class<?> clazz = obj.getClass();
        System.out.println(clazz);
        System.out.println(new Gson().toJson(obj));
        for (Field field : clazz.getDeclaredFields()) {
            field.setAccessible(true);
            String fieldName = field.getName();
            String value = String.valueOf(field.get(obj));
            if (value != null && !"null".equals(value)) {
                map.put(fieldName, value);
            }
        }
        return map;
    }




}
