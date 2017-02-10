package com.world.test;

public class BornUser {
	
	public static void main(String[] args) {
		String sql="insert into User values(%s,%s,%s,%s,%s,%s,%s,%s,%s,%s);";
		for (int i = 0; i < 100; i++) {
			String url="";
			if(i/20<1) url="'http://localhost:8080/world/resources/images/IMG_2948.jpg'";
			else if(i/20>=1&&i/20<2) url="'http://localhost:8080/world/resources/images/IMG_3792.jpg'";
			else if(i/20>=2&&i/20<3) url="'http://localhost:8080/world/resources/images/IMG_3803.jpg'";
			else if(i/20>=3&&i/20<4) url="'http://localhost:8080/world/resources/images/IMG_3798.jpg'";
			else if(i/20>=4&&i/20<=5) url="'http://localhost:8080/world/resources/images/IMG_3794.jpg'"; 
			System.out.println(String.format(sql, 0,0,"'研发部'","'42028119980103723"+i+"'",url,
					"'yanbo"+i+"'","''","'1517892323"+i+"'","'yanbo"+i+"'",0));
		}
	}
}
