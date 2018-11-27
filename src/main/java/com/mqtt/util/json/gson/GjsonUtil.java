package com.mqtt.util.json.gson;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonSyntaxException;
/**
 * 
 * @author binggu
 * @date 2016-07-21
 */
@SuppressWarnings("all")
public class GjsonUtil
{
	private static Gson gson1 = null;
	private static Gson gson2 = null;
	
	static{
		gson1  = new GsonBuilder().registerTypeAdapterFactory(
                new NullStringToEmptyAdapterFactory()).create(); 
		gson2 = new GsonBuilder().registerTypeAdapterFactory(
                new NullStringToEmptyAdapterFactory2()).create();
	}
   /* private static final Gson gson = new GsonBuilder().registerTypeAdapterFactory(
            new NullStringToEmptyAdapterFactory()).create();*/
    /**
     * 对象转换为json（null属性拼装为字符串"null"）
     * @param obj
     * @return
     */
    public static String toJson(Object obj)
    {
        //Gson gson = new GsonBuilder().registerTypeAdapterFactory(
        //        new NullStringToEmptyAdapterFactory()).create();
        String json = "";
        try
        {
            json = gson1.toJson(obj);
        }
        catch (Exception e)
        {
        }
        return json;
    }
    
    /**
     * 对象转换为json(null属性不拼装)
     * @param obj
     * @return
     */
    public static String toJson2(Object obj)
    {
        Gson gson = new GsonBuilder().registerTypeAdapterFactory(
                new NullStringToEmptyAdapterFactory2()).create();
        String json = "";
        try
        {
            json = gson.toJson(obj);
        }
        catch (Exception e)
        {
        }
        return json;
    }
    
    /**
     * json转换为对象
     * @param json
     * @param clazz
     * @return
     */
    public static <T> T fromJson(String json,Class<T> clazz)
    {
        T jsonObj = null;
        try
        {
            jsonObj = gson1.fromJson(json, clazz);
        }
        catch (JsonSyntaxException e)
        {
            e.printStackTrace();
        }
        return jsonObj;
    }
    
    /**
     * json转换为对象
     * @param json
     * @param clazz
     * @return
     */
    public static <T> T fromJson2(String json,Class<T> clazz)
    {
        T jsonObj = null;
        try
        {
            jsonObj = gson2.fromJson(json, clazz);
        }
        catch (JsonSyntaxException e)
        {
            e.printStackTrace();
        }
        return jsonObj;
    }
    
    
}
