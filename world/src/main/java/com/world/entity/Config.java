package com.world.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * 特殊参数配置表
 * @author yanbo
 */
@Entity
@Table
public class Config implements Serializable{
	
	private static final long serialVersionUID = -4272864492146307923L;
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private long id;
	@Column(unique=true)
	private String keyName;
	@Column
	private String keyValue;
	@Column
	private String description;//描述
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public String getKeyName() {
		return keyName;
	}
	public void setKeyName(String keyName) {
		this.keyName = keyName;
	}
	public String getKeyValue() {
		return keyValue;
	}
	public void setKeyValue(String keyValue) {
		this.keyValue = keyValue;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public Config() {
		super();
	}
	public Config(long id, String keyName, String keyValue, String description) {
		super();
		this.id = id;
		this.keyName = keyName;
		this.keyValue = keyValue;
		this.description = description;
	}
	
}
