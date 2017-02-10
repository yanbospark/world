package com.hello.entity;

import com.hello.annotations.Column;
import com.hello.annotations.Table;

@Table
public class Teacher {
	
	@Column
	private String name;
	@Column
	private String classes;
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getClasses() {
		return classes;
	}
	public void setClasses(String classes) {
		this.classes = classes;
	}
	
	
	
}
