package com.world.service;

import java.util.List;

import com.world.entity.Award;

public interface AwardService {
	
	public List<Award> findAll();
	
	public Award save(Award award);
	
	public void update(String name,float price,Long id);
	
	public void update(int amount,int leftAmount,long id);
	
	public void update(Award award);
	
	public void delete(long id);
	
	public Award findById(Long id);
}
