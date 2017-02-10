package com.world.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.world.repository.SerialNumberRepository;
import com.world.service.SerialNumberService;

@Service
@Transactional
public class SerialNumberServiceImpl implements SerialNumberService {

	@Autowired
	private SerialNumberRepository serialNumberRep;
	
	@Override
	public int getIDValue(String tableName) {
		return serialNumberRep.getIDValue(tableName);
	}
	
	/**以指定的value值增长**/
	public int updateIDValue(int value,String tableName) {
		return serialNumberRep.updateIDValue(value,tableName);
	}

	/**以指定的value值增长**/
	public synchronized int getAndAdd(int value, String tableName) {
		int idValue=serialNumberRep.getIDValue(tableName);
		serialNumberRep.updateIDValue(value, tableName);
		return idValue;
	}
	
	/**以1自增**/
	public int getAndIncreament(String tableName) {
		return getAndAdd(1, tableName);
	}

}
