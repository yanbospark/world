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
 * 员工投票记录表
 * @author yanbo
 */
@Entity
@Table
public class UserVote implements Serializable{
	
	private static final long serialVersionUID = -174914952038160814L;
	//一个员工一个节目只能投一次票
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private long id;
	@OneToOne
	@JoinColumn(name="showId")
	private Show show;
	@OneToOne
	@JoinColumn(name="userId")
	private User user;
	@Column
	private int score;//得分
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public Show getShow() {
		return show;
	}
	public void setShow(Show show) {
		this.show = show;
	}
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}
	public int getScore() {
		return score;
	}
	public void setScore(int score) {
		this.score = score;
	}
	public UserVote() {
	}
	public UserVote(long id, Show show, User user, int score) {
		this.id = id;
		this.show = show;
		this.user = user;
		this.score = score;
	}
	
}
