package com.mqtt.util.json.gson;

import com.google.gson.Gson;
import com.google.gson.TypeAdapter;
import com.google.gson.TypeAdapterFactory;
import com.google.gson.reflect.TypeToken;

/**
 * 使用Gson时候对象属性 null处理为""
 * @author 999
 * @date 2016-06-21
 */
public class NullStringToEmptyAdapterFactory2<T> implements TypeAdapterFactory {
    @SuppressWarnings("all")
    public <T> TypeAdapter<T> create(Gson gson, TypeToken<T> type) {
        Class<T> rawType = (Class<T>) type.getRawType();
        if (rawType != String.class) {
            return null;
        }
        return (TypeAdapter<T>) new StringNullAdapter2();
    }
}
