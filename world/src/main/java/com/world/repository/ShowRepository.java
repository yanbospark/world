package com.world.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import com.world.entity.Show;

public interface ShowRepository extends CrudRepository<Show, Long>{

	//根据表演顺序查询
	public Show findBySerial(int serial);
	
	public List<Show> findByShowState(int showState);
	
	public List<Show> findByState(int voteState);
	
	@Query("from Show order by counts desc")
	public List<Show> findOrderByCounts();
	
	//查询正在进行中的节目
	@Query("from Show where showState=1")
	public Show findProcessing();
	
	public List<Show> findBySerialLessThan(int maxSerial);
	
	public List<Show> findBySerialGreaterThan(int minSerial);
	
	@Query("select count(id) from Show")
	public int maxSerail();
	
	@Modifying
	@Query("update Show set name=?1,performers=?2,serial=?3,state=?4,showState=?5,teamImg=?6,department=?7,base=?8,userIds=?9 where id=?10")
	public int update(String name,String performers,Integer serial,Integer state,int showState,String teamImg,String department,float base,String userIds,Long id);
	
	@Modifying
	@Query("update Show set startTime=?1,showState=?2 where id=?3")
	public int updateStartTime(String startTime,int showState,long id);
	
	@Modifying
	@Query("update Show set endTime=?1,showState=?2,state=?3 where id=?4")
	public int updateShowEndTime(String endTime,int showState,int voteState,long id);
	
	@Modifying
	@Query("update Show set counts=counts+?1,state=?2 where id=?3")
	public int updateVoteState(int count,int state,long id);
	
	@Modifying
	@Query("update Show set rank=?1 where id=?2")
	public int updateRank(int rank,long id);
	
}