package com.neuedu.cache;


import com.google.common.cache.CacheBuilder;
import com.google.common.cache.CacheLoader;
import com.google.common.cache.LoadingCache;
import org.springframework.stereotype.Component;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;

//自定义缓存类
public class TokenCache {
    private static LoadingCache<String ,String> cacheLoader=
            CacheBuilder.newBuilder()
            .initialCapacity(1000)//初始化缓存项值
            .maximumSize(10000)//最大缓存项值
            .expireAfterAccess(2, TimeUnit.HOURS)//定时回收，时间2小时
            .build(new CacheLoader<String , String>() {
                @Override
                public String load(String s) throws Exception {

                    return "null1";
                }
            });


    //往缓存中设置数据
    public static void set(String key,String value){
        cacheLoader.put(key, value);

    }
    //从缓存中读取数据
    public static String get(String key)  {
        try {
            return cacheLoader.get(key);
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
        return null;
    }

}
