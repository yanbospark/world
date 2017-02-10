package com.world.service;

import java.util.List;

import com.world.entity.Config;

public interface ConfigService {
	
	public Config findById(Long id);
	
	public Config findByKeyName(String keyName);
	
	public Config save(Config bean);
	
	public int update(Config bean);
	
	public void delete(Long id);
	
	public List<Config> findAll();
	
}
