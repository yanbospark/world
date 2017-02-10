package com.world.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.world.entity.Winner;
import com.world.repository.WInnerRepository;
import com.world.service.WinnerService;

@Service
@Transactional
public class WinnerServiceImpl implements WinnerService {

	@Autowired
	WInnerRepository winnerRep;
	
	@Override
	public Winner findById(Long id) {
		return winnerRep.findOne(id);
	}

	@Override
	public Winner save(Winner bean) {
		return winnerRep.save(bean);
	}

	@Override
	public int update(Winner bean) {
		return winnerRep.update(bean.getUsername(), bean.getUserId(), bean.getAward().getId(), bean.getId());
	}

	@Override
	public void delete(Long id) {
		winnerRep.delete(id);
	}

	@Override
	public List<Winner> findAll() {
		return (List<Winner>) winnerRep.findAll();
	}

}
