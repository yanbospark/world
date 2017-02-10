package com.world.service;

import java.util.List;

import com.world.entity.User;

public interface UserService {
	
	public User findById(Long id);
	
	public User findByUsername(String username);
	
	public List<String> findAllUsername(int maxWinTimes);
	
	public List<User> findByWinTimesGreaterThan(int min);
	
	public List<Object[]> findBySerialLike(String serials);
	
	public List<Object[]> findOrderbyNumbers();
	
	public List<User> findAllClick();
	
	public List<User> findByIdCardLike(String idCard);
	
	public User findByNickname(String nickname);
	
	public List<User> findByUsernameLike(String username);
	
	public List<String> findAllImages(int maxWinTimes);
	
	public User findByTel(String tel);
	
	public User save(User user);
	
	public int update(User user);
	
	public int updateSerialnumbers(String serialnumbers,long id);
	
	public int rewardSerialnumbers(Integer number,Long[] id);
	
	public int updateWinTimes(int winTimes,long id);
	
	public int updateClickCount(int clickCount,long id);
	
	public void delete(Long id);
	
	public List<User> findAll();
	
	public String fillSerialNumber(int value);
}
