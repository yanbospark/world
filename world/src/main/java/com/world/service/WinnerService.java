package com.world.service;

import java.util.List;

import com.world.entity.Winner;

public interface WinnerService {
	
	public Winner findById(Long id);
	
	public Winner save(Winner bean);
	
	public int update(Winner bean);
	
	public void delete(Long id);
	
	public List<Winner> findAll();
}
