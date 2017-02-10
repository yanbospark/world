package com.world.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * 投票类型表
 * @author yanbo
 */
@Entity
@Table
public class Vote implements Serializable{

	private static final long serialVersionUID = 1055525888136583430L;
	/**
	 * 一般  1分 一颗星
	 * 还行  2分 二颗星
	 * 还好  3分 三颗星
	 * 挺好  4分 四颗星
	 * 非常好 5分 五颗星
	 */
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private long id;
	@Column
	private String name;
	@Column
	private int score;
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
	public int getScore() {
		return score;
	}
	public void setScore(int score) {
		this.score = score;
	}
	
	
}
