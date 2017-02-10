package com.world.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.world.entity.Vote;
import com.world.repository.VoteRepository;
import com.world.service.VoteService;


@Service
@Transactional
public class VoteServiceImpl implements VoteService{

	@Autowired
	VoteRepository voteRep;
	
	@Override
	public Vote findById(Long id) {
		return voteRep.findOne(id);
	}

	@Override
	public Vote save(Vote bean) {
		return voteRep.save(bean);
	}

	@Override
	public int update(Vote bean) {
		return voteRep.update(bean.getName(), bean.getScore(), bean.getId());
	}

	@Override
	public void delete(Long id) {
		voteRep.delete(id);
	}

	@Override
	public List<Vote> findAll() {
		return (List<Vote>) voteRep.findAll();
	}

}
