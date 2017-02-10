package com.world.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * 用户表
 * @author yanbo
 */
@Entity
@Table
public class User implements Serializable{
	
	private static final long serialVersionUID = -1647899687164849743L;
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;
	@Column
	private String department;//部门
	@Column(unique=true)
	private String username;//姓名
	@Column(unique=true)
	private String nickname;//昵称
	@Column(unique=true)
	private String idCard;//身份证号
	@Column
	private String img;//用户头像
	@Column
	private String tel;//手机号   看中奖信息是不是通过手机发送？？
	@Column
	private int winTimes;//中奖次数
	@Column
	private String serialnumbers;//中奖号码串
	@Column
	private int numbers;//中奖码个数
	@Column
	private int clickCount;//点击次数
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getDepartment() {
		return department;
	}
	public void setDepartment(String department) {
		this.department = department;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getTel() {
		return tel;
	}
	public void setTel(String tel) {
		this.tel = tel;
	}
	
	public String getIdCard() {
		return idCard;
	}
	public void setIdCard(String idCard) {
		this.idCard = idCard;
	}
	public int getWinTimes() {
		return winTimes;
	}
	public void setWinTimes(int winTimes) {
		this.winTimes = winTimes;
	}
	public String getImg() {
		return img;
	}
	public void setImg(String img) {
		this.img = img;
	}
	public String getNickname() {
		return nickname;
	}
	public void setNickname(String nickname) {
		this.nickname = nickname;
	}
	public String getSerialnumbers() {
		return serialnumbers;
	}
	public void setSerialnumbers(String serialnumbers) {
		this.serialnumbers = serialnumbers;
	}
	public int getClickCount() {
		return clickCount;
	}
	public void setClickCount(int clickCount) {
		this.clickCount = clickCount;
	}
	public int getNumbers() {
		return numbers;
	}
	public void setNumbers(int numbers) {
		this.numbers = numbers;
	}
	
	
}
