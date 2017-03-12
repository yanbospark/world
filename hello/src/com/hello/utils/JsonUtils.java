package com.hello.utils;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import net.sf.json.JSONObject;

/**
 * JSON工具类
 * @author yanbo
 *
 */
public class JsonUtils {
	
	/**
	 * <b>将实体对象转为JSON</b>
	 * @param 	obj
	 * @return	JSONObject
	 */
	@SuppressWarnings("all")
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
	
	
	public static JSONObject objectMapToJson(Map<String, Object> map){
		return mapToJson(map);
	}
	
	public static JSONObject stringMapToJson(Map<String, String> map){
		return mapToJson(map);
	}
	
	public static JSONObject intMapToJson(Map<String, Integer> map){
		return mapToJson(map);
	}
	
	/**
	 * <b>将Map转为JSON</b>
	 * @param 	map	
	 * @return	JSONObject
	 */
	private static JSONObject mapToJson(Map map){
		JSONObject json = new JSONObject();
		Iterator<String> keys = map.keySet().iterator();
		while(keys.hasNext()){
			String key = keys.next();
			json.put(key, map.get(key));
		}
		return json;
	}
	/**
	 * <b>将List转成JSON</b>
	 * <b>key和value要一一对应</b>
	 * @param 	keys		JSON的key集合
	 * @param 	values  	JSON的value集合 
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

	/**
	 * 默认JSON：{"code":"codeValue","message":"messageValue"}
	 * @param code
	 * @param message
	 * @return
	 */
	public static String defaultJsonToString(String code, String message) {
		try {
			JSONObject json = new JSONObject();
			json.put("code", code);
			json.put("message", message);
			return json.toString();
		} catch (Exception e) {
			System.out.println(JsonUtils.class.toString() + "解析出错：\t"+ e.getMessage());
			return "{}";
		}
	}

	/**
	 * @param values 
	 * 			eg："AA","1","BB","2","CC","3","DD","4"
	 * @return	
	 * 			json的String
	 */
	public static String defineJsonToString(String... values) {
		JSONObject json = new JSONObject();
		if (values.length <= 0) return "{}";
		if (values.length % 2 != 0) return "{}";
		return putValue(json, values.length, values);
	}

	private static String putValue(JSONObject json, int n ,String... v) {
		if (n <= 0) return json.toString();
		try {
			json.put(v[n - 2], v[n - 1]);
			n = n - 2;
			return putValue(json,n,v);
		} catch (Exception e) {
			e.printStackTrace();
			return "{}";
		}
	}
	
	public static void main(String[] args) {
		System.out.println(defineJsonToString("AA","1","BB","2","CC","3","DD","4"));
	}
	
}
