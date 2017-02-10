package com.world.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * 奖品表 
 * @author yanbo
 */
@Entity
@Table
public class Award implements Serializable{
	
	private static final long serialVersionUID = -544859686319877643L;
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private long id;
	@Column
	private String name;//奖品名称
	@Column(nullable=true)
	private int amount;//数量
	@Column
	private int leftAmount;//剩余数量
	@Column
	private float price;//单价
	
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
	public int getAmount() {
		return amount;
	}
	public void setAmount(int amount) {
		this.amount = amount;
	}
	public int getLeftAmount() {
		return leftAmount;
	}
	public void setLeftAmount(int leftAmount) {
		this.leftAmount = leftAmount;
	}
	public float getPrice() {
		return price;
	}
	public void setPrice(float price) {
		this.price = price;
	}
	@Override
	public String toString() {
		return "Award [id=" + id + ", name=" + name + ", amount=" + amount + ", leftAmount=" + leftAmount + ", price="
				+ price + "]";
	}
	
}
