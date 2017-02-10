package com.hello.utils;

import java.security.MessageDigest;

/**
 * MD5加密
 * @author yanbo
 *
 */
public class MD5Utils {
	
	/**
	 * 给字符串数据加密
	 * 默认编码是.java文件的编码
	 * @param message
	 * @return
	 */
	public static String getMD5(String message){
		return getMD5(message,"");
	}
	
	public static String getMD5(String message,String charset){
		String result = "";
		try {
			MessageDigest mDigest = MessageDigest.getInstance("MD5");
			byte[] input;
			if("".equals(charset)){
				input=message.getBytes();
			}else{
				input=message.getBytes(charset);
			}
			byte[] buff = mDigest.digest(input);//计算得到128位数据
			result = byteToHEX(buff);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}
	
	public static String byteToHEX(byte[] arr){
		StringBuffer sbBuffer = new StringBuffer();
		int digest;
		for(int i=0;i<arr.length;i++){
			digest = arr[i];
			if(digest < 0){
				digest = digest + 256;
			}
			if(digest < 16){
				sbBuffer.append("0");
			}
			sbBuffer.append(Integer.toHexString(digest));
		}
		return sbBuffer.toString();
	}
	
}
