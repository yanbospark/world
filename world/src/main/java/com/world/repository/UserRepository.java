package com.world.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import com.world.entity.User;

public interface UserRepository extends CrudRepository<User, Long>{
	
	/**
	 * 方式一：JP QL的查询规则
	 */
	public User findById(Long id);
	public User findByTel(String tel);
	@Query("select u from User u where u.clickCount > 0 order by u.clickCount desc")
	public List<User> findAllClick();
	@Query("select username from User where winTimes<?1 order by username desc")
	public List<String> findAllUsername(int maxWinTimes);
	/**查询已中奖的人**/
	@Query("from User where winTimes>=?1")
	public List<User> findByWinTimesGreaterThan(int min);
	/**根据微信昵称查询**/
	public User findByNickname(String nickname);
	
	@Query("select id,username,winTimes from User where serialnumbers like ?1")
	public List<Object[]> findBySerialLike(String serials);
	
	@Query("select username,serialnumbers,numbers,winTimes from User order by winTimes desc,numbers desc")
	public List<Object[]> findOrderbyNumbers();
	
	@Query("select a from User a where a.username = ?1")
	public User findByUsername(String username);
	
	@Query("select img from User where winTimes<?1 order by id asc")
	public List<String> findAllImages(int maxWinTimes);
	
	/**模糊查询身份证**/
	public List<User> findByIdCardLike(String idCard);
	
	/**模糊查询*/
	public List<User> findByUsernameLike(String username);
	
	@Modifying(clearAutomatically=true)
	@Query("update User set username=:username,department=:department,img=:img,tel=:tel,IDCard=:IDCard where id=:id")
	public int updateUser(@Param("username") String username,@Param("department") String department,@Param("img") String img,@Param("tel") String tel,@Param("IDCard") String IDCard,@Param("id") long id);
	
	@Modifying
	@Query("update User set serialnumbers=:serialnumbers,numbers=numbers+:numbers where id=:id")
	public int updateSerialnumbers(@Param("serialnumbers") String serialnumbers,@Param("numbers") int numbers,@Param("id") long id);
	
	@Modifying
	@Query("update User set winTimes=?1 where id=?2")
	public int updateWinTimes(int winTimes,long id);
	
	@Modifying
	@Query("update User set clickCount=?1 where id=?2")
	public int updateClickCount(int clickCount,long id);
	
	
}
