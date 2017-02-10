package com.hello.utils;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.security.MessageDigest;

/**
 * 读取文件内容或者文件的MD5值
 * @author yanbo
 */
public class FileReaderUtils {
	
	/**
	 * 默认utf8
	 * 读取本地文件
	 */
	public static String readLocal(String url){
		return readLocal(url, "utf8");
	}
	
	/**
	 * 带缓冲区的文件流
	 * 读取本地文件
	 */
	public static String readLocal(String url,String charset){
		StringBuffer sb=new StringBuffer();
		try(BufferedReader bf=new BufferedReader(new InputStreamReader(new FileInputStream(url),charset))){
			String line=null;
			while((line=bf.readLine())!=null){
				sb.append(line);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return sb.toString();
	}
	
	/**
	 * 读取远程文件的MD5值
	 */
	public static String readRemote(String url){
		String md5="";
		try {
			HttpURLConnection connect = (HttpURLConnection)new URL(url).openConnection();
			InputStream is = null;
			if(connect.getResponseCode()==HttpURLConnection.HTTP_OK){
				MessageDigest messagedigest = MessageDigest.getInstance("MD5");
				is = connect.getInputStream();
				byte buff[] = new byte[1024];
				int len = 0;
				while ((len = is.read(buff, 0, 1024)) > 0) {
					messagedigest.update(buff, 0, len);// 对文件进行MD5

				}
				md5 = MD5Utils.byteToHEX(messagedigest.digest());
				is.close();
			}
		}catch (Exception e) {e.printStackTrace();}
		return md5;
	}
	
	public static void main(String[] args) {
		String url="http://z.dzrtv.cn/f/k/swf/xiaohs.swf";
		System.out.println(readRemote(url));
	}
}
