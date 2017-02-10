package com.hello.utils;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

import com.hello.utils.help.CacheObject;

/**
 * 缓存工具类
 * @author yanbo
 */
@SuppressWarnings("all")
public class CacheUtils {
	static {
		init();
	}
	
	private static Map pool = new ConcurrentHashMap();

	private static void init() {
		(new Timer()).schedule(new TimerTask() {
			public void run() {
				for (Iterator it = CacheUtils.pool.keySet().iterator(); it.hasNext();) {
					String key = (String) it.next();
					CacheObject obj = (CacheObject) CacheUtils.pool.get(key);
					if (obj != null && obj.getActiveTime() + obj.getTimeOut() < System.currentTimeMillis()) {
						CacheUtils.pool.remove(key);
						Object obj1 = null;
					}
				}

			}

		}, new Date(), 10000L);
		return;
	}

	public static void putCacheObject(Object key, CacheObject value) {
		value.setActiveTime(System.currentTimeMillis());
		pool.put(key, value);
	}

	public static void remove(Object key) {
		if (pool.containsKey(key))
			pool.remove(key);
	}

	/**
	 * 默认缓存5分钟
	 */
	public static void put(Object key, int value) {
		put(key, value, 0L);
	}

	public static void put(Object key, int value, long timeOut) {
		if (timeOut <= 0L)
			timeOut = 300000L;
		CacheObject co = new CacheObject(value);
		co.setTimeOut(timeOut);
		putCacheObject(key, co);
	}

	public static void put(Object key, long value) {
		pool.put(key, new CacheObject(value));
	}

	public static void put(Object key, Object objectValue, long timeOut) {
		if (timeOut <= 0L)
			timeOut = 300000L;
		CacheObject co = new CacheObject(objectValue);
		co.setTimeOut(timeOut);
		putCacheObject(key, co);
	}

	public static void put(Object key, Object objectValue) {
		put(key, objectValue, 300000L);
	}

	public static int getInt(Object key) {
		CacheObject co = getCacheObject(key);
		if (co == null)
			return 0;
		else
			return co.getIntValue();
	}

	public static String getString(Object key) {
		CacheObject co = getCacheObject(key);
		if (co == null)
			return null;
		else
			return co.getStringValue();
	}

	public static Map getMap(Object key) {
		CacheObject co = getCacheObject(key);
		if (co == null)
			return null;
		else
			return (Map) co.getObjectValue();
	}

	public static Object get(Object key) {
		CacheObject co = getCacheObject(key);
		if (co == null)
			return null;
		else
			return co.getObjectValue();
	}

	public static CacheObject getCacheObject(Object key) {
		CacheObject obj = (CacheObject) pool.get(key);
		if (obj != null && obj.getActiveTime() + obj.getTimeOut() < System.currentTimeMillis()) {
			pool.remove(key);
			obj = null;
		}
		return obj;
	}

	public Set getEntrySet() {
		return pool.keySet();
	}

	public static void clear() {
		pool.clear();
	}

	public static void main(String args[]) {
		System.out.println("start");
		for (int i = 0; i < 200; i++)
			put((new StringBuilder("obj:")).append(i).toString(), (new StringBuilder()).append(i).toString());

		for (int i = 0; i < 200; i++)
			System.out.println(getString((new StringBuilder("obj:")).append(i).toString()));

		System.out.println("SIZE:"+pool.size());
	}

}
