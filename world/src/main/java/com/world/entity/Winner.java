package com.world.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

/**
 * 中奖表
 * @author yanbo
 */
@Entity
@Table
public class Winner implements Serializable{
	
	private static final long serialVersionUID = -5378464748085740667L;
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private long id;
	@Column
	private String username;
	@Column
	private long userId;
	@OneToOne
	@JoinColumn(name="awardId")
	private Award award;
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public long getUserId() {
		return userId;
	}
	public void setUserId(long userId) {
		this.userId = userId;
	}
	public Award getAward() {
		return award;
	}
	public void setAward(Award award) {
		this.award = award;
	}
}
