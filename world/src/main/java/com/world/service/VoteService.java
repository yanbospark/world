package com.world.service;

import java.util.List;

import com.world.entity.Vote;

public interface VoteService {
	
	public Vote findById(Long id);
	
	public Vote save(Vote bean);
	
	public int update(Vote bean);
	
	public void delete(Long id);
	
	public List<Vote> findAll();
	
}
