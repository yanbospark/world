package com.world.entity;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table
public class Gamer implements Serializable{
	
	private static final long serialVersionUID = -4981974098900530734L;
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	public long id;
	@OneToOne
	@JoinColumn(name="userId",unique=true)
	public User user;
	
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}
	
}
