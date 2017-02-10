package com.world.service;

public interface SerialNumberService {

	public int getIDValue(String tableName);
	
	public int updateIDValue(int value,String tableName);
	
	public int getAndIncreament(String tableName);
	
	public int getAndAdd(int value,String tableName);
}
