package com.world.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.world.entity.Gamer;
import com.world.repository.GamerRepository;
import com.world.service.GamerService;

@Service
@Transactional
public class GamerServiceImpl implements GamerService {

	@Autowired 
	GamerRepository gamerRep;
	
	@Override
	public Gamer findById(Long id) {
		return gamerRep.findOne(id);
	}

	@Override
	public Gamer save(Gamer bean) {
		return gamerRep.save(bean);
	}

	@Override
	public int update(Gamer bean) {
		return 0;
	}

	@Override
	public void delete(Long id) {
		gamerRep.delete(id);
	}

	@Override
	public List<Gamer> findAll() {
		return (List<Gamer>) gamerRep.findAll();
	}
	public Gamer findByUserId(long userId){
		return gamerRep.findByUserId(userId);
	}
	
	public int getAmount(){
		return gamerRep.getAmount();
	}
	
	public List<Gamer> findAllByOrderDesc(){
		return gamerRep.findAllByOrderDesc();
	}

	
}
