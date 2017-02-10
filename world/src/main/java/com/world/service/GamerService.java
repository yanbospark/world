package com.world.service;

import java.util.List;

import com.world.entity.Gamer;

public interface GamerService {
	
	public Gamer findById(Long id);
	
	public Gamer save(Gamer bean);
	
	public int update(Gamer bean);
	
	public void delete(Long id);
	
	public List<Gamer> findAll();
	
	public Gamer findByUserId(long userId);
	
	public int getAmount();
	
	public List<Gamer> findAllByOrderDesc();
}
