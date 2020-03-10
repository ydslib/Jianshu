package com.yds.jianshulib.utils;

/**
 * Created by yds
 * on 2019/8/2.
 */
public class ObjectUtil {
    public static<T> T requireNonNull(T object,String message){
        if(object == null){
            throw new NullPointerException(message);
        }
        return object;
    }
}
