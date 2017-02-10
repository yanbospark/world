package com.hello.annotations;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
public @interface Column {
	
	public boolean isKey() default false;//是否为主键
	
	public String name() default "";//字段名
	
	public int length() default 0;//字段长度
	
	public String type() default "varchar";//字段类型
	
	public String comment() default "";//描述
	
	public boolean ifNull() default true;//是否为空
}
