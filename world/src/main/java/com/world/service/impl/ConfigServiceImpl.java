package com.world.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.world.entity.Config;
import com.world.repository.ConfigRepository;
import com.world.service.ConfigService;

@Service
@Transactional
public class ConfigServiceImpl implements ConfigService {

	@Autowired
	ConfigRepository configRep;
	
	@Override
	public Config findById(Long id) {
		return configRep.findOne(id);
	}

	@Override
	public Config findByKeyName(String keyName) {
		return configRep.findByKeyName(keyName);
	}

	@Override
	public Config save(Config bean) {
		return configRep.save(bean);
	}

	@Override
	public int update(Config bean) {
		return configRep.updateConfig(bean.getKeyName(), bean.getKeyValue(), bean.getDescription(), bean.getId());
	}

	@Override
	public void delete(Long id) {
		configRep.delete(id);
	}

	@Override
	public List<Config> findAll() {
		return (List<Config>) configRep.findAll();
	}

}
