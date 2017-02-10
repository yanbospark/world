package com.world.utils;

import java.text.SimpleDateFormat;

public class ConstantUtils {
	
	public static String imageURI="/resources/images/";
	public static String path=ConstantUtils.class.getClassLoader().getResource("").getPath().replaceAll("/classes/", "");
	public static SimpleDateFormat sdf=new SimpleDateFormat("yyMMddHHmmssSSS");
	public static SimpleDateFormat simpleTime=new SimpleDateFormat("yy-MM-dd/HH:mm:ss");
	public static int MAX=9999;//最大中奖号
}
