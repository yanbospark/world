package com.hello.utils;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

/**
 * 将Object／List／Map转成JSON格式 
 * @author yanbo
 */
@SuppressWarnings("all")
public class ObjectUtils {
	
	/**
	 * <b>convert simple Object bean to JsonObject bean</b>
	 * @param 	obj
	 * @return	JSONObject
	 */
	public static JSONObject objectToJson(Object obj){
		JSONObject json=new JSONObject();
		Class c=obj.getClass();
		Field[] fields = c.getDeclaredFields();
		Method[] methods = c.getDeclaredMethods();
		for (Method method : methods) {
			if(method.getName().startsWith("get")){
				String upperFieldName = method.getName().substring(3);
				upperFieldName = upperFieldName.substring(0,1).toLowerCase()+upperFieldName.substring(1);
				try {
					Object filedValue=method.invoke(obj);
					json.put(upperFieldName, filedValue);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		}
		return json;
	}
	
	/**
	 * <b>convert Map bean to JSONObject bean</b>
	 * @param 	map	
	 * 				The key must be a String type
	 * @return	JSONObject
	 */
	public static JSONObject mapToJson(Map<String, Object> map){
		JSONObject json = new JSONObject();
		Iterator<String> keys = map.keySet().iterator();
		while(keys.hasNext()){
			String key = keys.next();
			json.put(key, map.get(key));
		}
		return json;
	}
	
	/**
	 * <b>convert List bean to JsonObject bean</b>
	 * @param 	keys	
	 * 				The JSONObject key collections
	 * @param 	values
	 * 				The JSONObject value collections
	 * @return	JSONObject
	 */
	public static JSONObject listToJson(List<String> keys,List<Object> values){
		JSONObject json = new JSONObject();
		if(keys.size()!=values.size()){
			return null;
		}
		for (int i = 0; i < values.size(); i++) {
			json.put(keys.get(i),values.get(i));
		}
		return json;
	}
	
}
