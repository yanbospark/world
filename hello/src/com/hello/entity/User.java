package com.hello.entity;

import com.hello.annotations.Column;
import com.hello.annotations.Table;

@Table
public class User {
	@Column(isKey=true)
	private long id;
	@Column(comment="用户名")
	private String name;
	@Column(name="u_age",length=8)
	private int age;
	
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getAge() {
		return age;
	}
	public void setAge(int age) {
		this.age = age;
	}
	
	
	
}
