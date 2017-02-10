package com.world.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
/**
 * 表主键生成器
 * @author yanbo
 */
@Entity
@Table
public class SerialNumber implements Serializable{

	private static final long serialVersionUID = -3766245625239023254L;
	
	@Id
	private long id;
	@Column(unique=true)
	private String tableName;
	private int value;
	
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public String getTableName() {
		return tableName;
	}
	public void setTableName(String tableName) {
		this.tableName = tableName;
	}
	public int getValue() {
		return value;
	}
	public void setValue(int value) {
		this.value = value;
	}
	
	
}
