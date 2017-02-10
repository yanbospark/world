package com.world.repository;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.world.entity.Vote;

public interface VoteRepository extends CrudRepository<Vote, Long>{

	@Modifying
	@Query("update Vote set name=?1,score=?2 where id=?3")
	public int update(String name,int score,Long id);
	
}
