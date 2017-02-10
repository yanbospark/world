package com.world.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.world.entity.Gamer;

public interface GamerRepository extends CrudRepository<Gamer, Long>{
	
	@Query("from Gamer where user.id=?1")
	public Gamer findByUserId(long userId);
	
	@Query("select count(id) from Gamer")
	public int getAmount();
	
	@Query("from Gamer order by id desc")
	public List<Gamer> findAllByOrderDesc();
}
