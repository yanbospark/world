package com.world.repository;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.world.entity.SerialNumber;

public interface SerialNumberRepository extends CrudRepository<SerialNumber, Long>{
	
	@Query("select a.value from SerialNumber a where a.tableName=?1")
	public int getIDValue(String tableName);
	
	@Modifying
	@Query("update SerialNumber set value=value+?1 where tableName=?2")
	public int updateIDValue(int value,String tableName);
}
