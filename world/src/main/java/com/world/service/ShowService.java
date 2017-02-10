package com.world.service;

import java.util.List;

import com.world.entity.Show;

public interface ShowService {
	
	public Show findById(Long id);
	
	public Show findBySerial(int serial);
	
	public List<Show> findByShowState(int showState);
	
	public List<Show> findOrderByCounts();
	
	public List<Show> findByState(int voteState);
	
	public Show findProcessing();
	
	public int maxSerail();
	
	public List<Show> findBySerialLessThan(int maxSerial);
	
	public List<Show> findBySerialMoreThan(int minSerial);
	
	public Show save(Show bean);
	
	public int update(Show bean);

	public int updateStartTime(String startTime,int showState,long id);
	
	public int updateShowEndTime(String endTime,int showState,int voteState,long id);
	
	public int updateVoteState(int count,int voteState,long id);
	
	public int updateRank(int rank,long id);
	
	void delete(Long id);
	
	public List<Show> findAll();
}
