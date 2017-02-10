package com.world.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.world.entity.User;
import com.world.repository.UserRepository;
import com.world.service.SerialNumberService;
import com.world.service.UserService;
import com.world.utils.ConstantUtils;

@Service
@Transactional
public class UserServiceImpl implements UserService{

	@Autowired
	UserRepository userRep;
	@Autowired
	SerialNumberService serialSvc;
	
	private static String TABLENAME="User";
	
	public User findById(Long id) {
		return userRep.findById(id);
	}
	
	public UserServiceImpl(){
		System.out.println("=============create UserRepository!");
	}

	@Override
	public User findByUsername(String username) {
		return userRep.findByUsername(username);
	}
	
	public List<Object[]> findBySerialLike(String serials){
		return userRep.findBySerialLike(serials);
	}

	public List<User> findByWinTimesGreaterThan(int min){
		return userRep.findByWinTimesGreaterThan(min);
	}
	
	public List<String> findAllUsername(int maxWinTimes){
		return userRep.findAllUsername(maxWinTimes);
	}
	/**
	 * 中奖码个数排名
	 */
	public List<Object[]> findOrderbyNumbers(){
		return userRep.findOrderbyNumbers();
	}
	/**
	 * idCard的值形如：'%idCard%'进行模糊匹配
	 */
	public List<User> findByIdCardLike(String idCard){
		return userRep.findByIdCardLike(idCard);
	}
	
	/**用户名模糊查询*/
	public List<User> findByUsernameLike(String username){
		return userRep.findByUsernameLike(username);
	}
	
	@Override
	public User findByTel(String tel) {
		return userRep.findByTel(tel);
	}

	/**查询所有已经点击过的员工**/
	public List<User> findAllClick(){
		return userRep.findAllClick();
	}
	
	public User findByNickname(String nickname){
		return userRep.findByNickname(nickname);
	}
	
	@Override
	public User save(User user) {
		//生成中奖码
		int value=serialSvc.getAndIncreament(TABLENAME);
		user.setNumbers(1);
		user.setSerialnumbers(fillSerialNumber(value));
		return userRep.save(user);
	}

	/**修改常用可修改的信息*/
	public int update(User user) {
		return userRep.updateUser(user.getUsername(), user.getDepartment(), user.getImg(),user.getTel(), user.getIdCard(),user.getId());
	}
	
	/**
	 * 修改中奖序列码
	 * @param oldSerialNumber 原有的中奖号
	 * @param id  user主键
	 * */
	public int updateSerialnumbers(String oldSerialNumber,long id){
		int value=serialSvc.getAndIncreament(TABLENAME);
		String newSerialNumber=oldSerialNumber+","+fillSerialNumber(value);
		return userRep.updateSerialnumbers(newSerialNumber,1, id);
	}
	/**
	 * 赠送多个中奖码
	 * @param number
	 * @param id
	 * @return
	 */
	public int rewardSerialnumbers(Integer number,Long[] ids){
		int success=0;
		for (int i = 0; i < ids.length; i++) {
			User u=userRep.findById(ids[i]);
			int oldLen=serialSvc.getAndAdd(number, TABLENAME);
			String newSerialNumber=u.getSerialnumbers();
			for (int j = 0; j < number; j++) {
				newSerialNumber+=","+fillSerialNumber(oldLen+j);
			}
			System.out.println("先前最后一个序列："+oldLen+"<><>number="+number+"<><>用户"+u.getId()+"<><>newSerialnumber="+newSerialNumber);
			success+=userRep.updateSerialnumbers(newSerialNumber, number, ids[i]);
		}
		return success;
	}
	
	/**修改中奖次数*/
	public int updateWinTimes(int winTimes,long id){
		return userRep.updateWinTimes(winTimes, id);
	}
	/**修改点击次数**/
	public int updateClickCount(int clickCount,long id){
		return userRep.updateClickCount(clickCount, id);
	}
	@Override
	public void delete(Long id) {
		userRep.delete(id);
	}
	/**查询所有用户图片**/
	public List<String> findAllImages(int maxWinTimes){
		return userRep.findAllImages(maxWinTimes);
	}
	@Override
	public List<User> findAll() {
		return (List<User>) userRep.findAll();
	}
	
	/**
	 * 中奖号码生成规则
	 * @param value
	 * @return
	 */
	public String fillSerialNumber(int value){
		String serial="";
		int common=String.valueOf(ConstantUtils.MAX).length();//都是四位数
		String v=String.valueOf(value);
		while(common>v.length()){
			serial+="0";
			common--;
		}
		serial+=v;
		return serial;
	}

	public static void main(String[] args) {
		UserServiceImpl user=new UserServiceImpl();
		for (int i = 1; i <= ConstantUtils.MAX; i++) {
			System.out.println(user.fillSerialNumber(i));
		}
	}
}
