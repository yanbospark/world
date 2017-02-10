package com.world.repository;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import com.world.entity.Award;

public interface AwardRepository extends CrudRepository<Award, Long>{

	@Modifying
	@Query("update Award set name=?1,price=?2 where id=?3")
	public void update(String name,Float price,Long id);
	
	@Modifying(clearAutomatically=true)
	@Query("update Award set amount=amount-:amount,leftAmount=leftAmount+:leftAmount where id=:id")
	public void updateAmount(@Param("amount") int amount,@Param("leftAmount") int leftAmount,@Param("id") long id);
	
}
