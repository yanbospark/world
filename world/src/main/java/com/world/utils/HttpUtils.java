package com.world.utils;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * <p>发送Http请求API</p>
 * 
 * @author yanbo
 */
public class HttpUtils {

	public static String sendPost(String address, String jsonData) {
		
		URL url = null;
		try {
			// 创建连接
			url = new URL(address);
			HttpURLConnection conn = (HttpURLConnection) url.openConnection();

			conn.setDoOutput(true);
			conn.setDoInput(true);
			conn.setUseCaches(false);
			conn.setRequestMethod("POST");
			conn.setRequestProperty("Content-type","application/x-www-form-urlencoded");
			conn.setRequestProperty("Accept-Charset", "UTF-8");
			conn.connect();

			// json参数
			DataOutputStream out = new DataOutputStream(conn.getOutputStream());
			out.write(jsonData.getBytes("utf-8"));
			out.flush();
			out.close();

			// 获取响应
			BufferedReader reader = new BufferedReader(new InputStreamReader(conn.getInputStream()));
			String lines;
			StringBuffer sb = new StringBuffer();
			while ((lines = reader.readLine()) != null) {
				lines = new String(lines.getBytes(), "utf-8");
				sb.append(lines);
			}
			reader.close();

			// 关闭连接
			conn.disconnect();
			return sb.toString();
		} catch (Exception e) {
			e.printStackTrace();
			return "";
		} finally {
		}
	}

	public static String sendGet(String address, String jsonData) {
		URL url = null;
		try {
			// 创建连接
			url = new URL(address);
			HttpURLConnection conn = (HttpURLConnection) url.openConnection();

			conn.setDoOutput(true);
			conn.setDoInput(true);
			conn.setUseCaches(false);
			conn.setRequestMethod("GET");
			conn.setRequestProperty("Content-type","application/x-www-form-urlencoded");
			conn.setRequestProperty("Accept-Charset", "UTF-8");
			conn.connect();
			// json参数
			DataOutputStream out = new DataOutputStream(conn.getOutputStream());
			out.write(jsonData.getBytes("utf-8"));
			out.flush();
			out.close();

			// 获取响应
			BufferedReader reader = new BufferedReader(new InputStreamReader(conn.getInputStream()));
			String lines;
			StringBuffer sb = new StringBuffer();
			while ((lines = reader.readLine()) != null) {
				lines = new String(lines.getBytes(), "utf-8");
				sb.append(lines);
			}
			reader.close();

			// 关闭连接
			conn.disconnect();
			return sb.toString();
		} catch (Exception e) {
			e.printStackTrace();
			return "";
		} finally {
		}
	}
	public static void main(String[] args) {
		System.out.println(sendGet("http://localhost:8080/netbar/yanbo.jsp", "cardNumber=622627198510011639&callback=calldo"));;
	}
}
