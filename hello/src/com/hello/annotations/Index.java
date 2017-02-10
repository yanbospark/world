package com.hello.annotations;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 字段上建索引
 * @author yanbo
 */
@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
public  @interface Index {
	
	String name();//索引名称：可以是联合索引
	
}
