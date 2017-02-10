package com.world.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.world.entity.Award;
import com.world.repository.AwardRepository;
import com.world.service.AwardService;

@Service
@Transactional
public class AwardServiceImpl implements AwardService{

	@Autowired
	private AwardRepository awardRep;
	
	public List<Award> findAll() {
		return (List<Award>) awardRep.findAll();
	}

	public Award save(Award award) {
		return awardRep.save(award);
	}

	public void update(Award award) {
		
	}
	
	public void update(String name, float price, Long id) {
		awardRep.update(name, price, id);
	}
	
	public void update(int amount, int leftAmount, long id) {
		awardRep.updateAmount(amount, leftAmount, id);
	}
	
	public void delete(long id) {
		awardRep.delete(id);
	}

	@Override
	public Award findById(Long id) {
		return awardRep.findOne(id);
	}

}
