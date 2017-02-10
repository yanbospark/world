package com.world.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.world.entity.UserVote;
import com.world.repository.UserVoteRepository;
import com.world.service.UserVoteService;

@Service
@Transactional
public class UserVoteServiceImpl implements UserVoteService {

	@Autowired
	UserVoteRepository userVoteRep;
	
	@Override
	public UserVote findById(Long id) {
		return userVoteRep.findOne(id);
	}

	/**
	 * 添加投票记录并且增加投票数
	 */
	@Override
	public UserVote save(UserVote bean) {
		bean=userVoteRep.save(bean);
		return bean;
	}

	@Override
	public int update(UserVote bean) {
		return userVoteRep.update( bean.getScore(),bean.getUser().getId(), bean.getShow().getId());
	}

	@Override
	public int update(int score,long userId,long showId){
		return userVoteRep.update(score, userId, showId);
	}
	
	@Override
	public void delete(Long id) {
		userVoteRep.delete(id);
	}

	@Override
	public List<UserVote> findAll() {
		return (List<UserVote>) userVoteRep.findAll();
	}
	/**
	 * 查询用户已经投过的节目
	 */
	public List<UserVote> haveVoted(long userId){
		return userVoteRep.haveVoted(userId);
	}
	
	public List<UserVote> countVote(){
		return userVoteRep.countVote();
	}
	/**
	 * 是否已经投过票
	 */
	public UserVote ifVoted(long userId,long showId){
		return userVoteRep.ifVoted(userId, showId);
	}
}
