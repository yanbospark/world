package com.world.service;

import java.util.List;

import com.world.entity.UserVote;

public interface UserVoteService {

	public UserVote findById(Long id);
	
	public UserVote save(UserVote bean);
	
	public int update(UserVote bean);
	
	public int update(int score,long userId,long showId);
	
	public void delete(Long id);
	
	public List<UserVote> findAll();
	
	public List<UserVote> haveVoted(long userId);
	
	public List<UserVote> countVote();
	
	public UserVote ifVoted(long userId,long showId);
}
