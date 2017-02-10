package com.world.entity;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * 节目表
 * @author yanbo
 */
@Entity
@Table(name="Shows")
public class Show implements Serializable{

	private static final long serialVersionUID = -7233838222083715203L;
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private long id;
	private String name;//节目名称
	private String department;//部门
	private String performers;//表演人员
	private int serial;//表演顺序
	private int showState;//表演是否结束 0未开始；1进行中；2已结束
	private int state;//投票是否结束 0未开始；1进行中；2已结束
	private int counts;//得票数
	private String startTime;//节目开始时间
	private String endTime;//节目结束时间
	private int rank;//第几名
	private String teamImg;//团体照
	private float base;//基数
	private String userIds;//员工ID串,用英文逗号隔开
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
	public String getPerformers() {
		return performers;
	}
	public void setPerformers(String performers) {
		this.performers = performers;
	}
	public int getSerial() {
		return serial;
	}
	public void setSerial(int serial) {
		this.serial = serial;
	}
	public int getState() {
		return state;
	}
	public void setState(int state) {
		this.state = state;
	}
	public int getCounts() {
		return counts;
	}
	public void setCounts(int counts) {
		this.counts = counts;
	}
	public String getStartTime() {
		return startTime;
	}
	public void setStartTime(String startTime) {
		this.startTime = startTime;
	}
	public String getEndTime() {
		return endTime;
	}
	public void setEndTime(String endTime) {
		this.endTime = endTime;
	}
	public int getRank() {
		return rank;
	}
	public void setRank(int rank) {
		this.rank = rank;
	}
	public int getShowState() {
		return showState;
	}
	public void setShowState(int showState) {
		this.showState = showState;
	}
	public String getTeamImg() {
		return teamImg;
	}
	public void setTeamImg(String teamImg) {
		this.teamImg = teamImg;
	}
	public Show() {
	}
	public Show(long id) {
		this.id = id;
	}
	public String getDepartment() {
		return department;
	}
	public void setDepartment(String department) {
		this.department = department;
	}
	public float getBase() {
		return base;
	}
	public void setBase(float base) {
		this.base = base;
	}
	public String getUserIds() {
		return userIds;
	}
	public void setUserIds(String userIds) {
		this.userIds = userIds;
	}
	
	
}
