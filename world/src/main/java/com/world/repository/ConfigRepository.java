package com.world.repository;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.world.entity.Config;

public interface ConfigRepository extends CrudRepository<Config, Long>{

	public Config findByKeyName(String keyName);
	
	@Modifying
	@Query("update Config set keyName=?1,keyValue=?2,description=?3 where id=?4")
	public int updateConfig(String keyName,String keyValue,String description,Long id);
	
}
