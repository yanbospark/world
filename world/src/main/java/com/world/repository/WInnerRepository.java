package com.world.repository;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import com.world.entity.Winner;

public interface WInnerRepository extends CrudRepository<Winner, Long>{

	@Modifying
	@Query("update Winner set username=?1,userId=?2,award.id=?3 where id=?4")
	public int update(String username,long userId,long awardId,Long id);
	
}
