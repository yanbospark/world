package com.world.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.world.entity.Show;
import com.world.repository.ShowRepository;
import com.world.service.ShowService;

@Service
@Transactional
public class ShowServiceImpl implements ShowService{

	@Autowired
	ShowRepository showRep;
	
	@Override
	public Show findById(Long id) {
		return showRep.findOne(id);
	}
	/**按照节目得分排名*/
	public List<Show> findOrderByCounts(){
		return showRep.findOrderByCounts();
	}
	
	/**根据表演顺序查询**/
	public Show findBySerial(int serial){
		return showRep.findBySerial(serial);
	}
	
	/**查询正在进行中的节目**/
	public Show findProcessing(){
		return showRep.findProcessing();
	}
	
	/**根据节目表演状态查询**/
	public List<Show> findByShowState(int showState){
		return showRep.findByShowState(showState);
	}
	/**根据投票状态查询**/
	public List<Show> findByState(int voteState){
		return showRep.findByState(voteState);
	}
	/**查询小于指定节目排序*/
	public List<Show> findBySerialLessThan(int maxSerial){
		return showRep.findBySerialLessThan(maxSerial);
	}
	/**查询大于指定节目排序*/
	public List<Show> findBySerialMoreThan(int minSerial){
		return showRep.findBySerialGreaterThan(minSerial);
	}
	
	@Override
	public Show save(Show bean) {
		return showRep.save(bean);
	}

	/**修改节目的基本信息**/
	public int update(Show bean) {
		return showRep.update(bean.getName(), bean.getPerformers(), bean.getSerial(), bean.getState(),bean.getShowState(),bean.getTeamImg(), bean.getDepartment(),bean.getBase(),bean.getUserIds(),bean.getId());
	}
	/**修改节目表演开始时间
	 * 节目状态：进行中**/
	public int updateStartTime(String startTime,int showState,long id){
		return showRep.updateStartTime(startTime, showState, id);
	}
	/**修改节目表演结束时间
	 * 节目状态:已结束
	 * 投票状态:进行中**/
	public int updateShowEndTime(String endTime,int showState,int voteState,long id){
		return showRep.updateShowEndTime(endTime, showState, voteState, id);
	}
	/**修改投票数
	 * 投票状态：已结束**/
	public int updateVoteState(int count,int voteState,long id){
		return showRep.updateVoteState(count, voteState, id);
	}
	
	/**修改排名状态**/
	public int updateRank(int rank,long id){
		return showRep.updateRank(rank, id);
	}
	
	@Override
	public void delete(Long id) {
		showRep.delete(id);
	}

	@Override
	public List<Show> findAll() {
		return (List<Show>) showRep.findAll();
	}
	@Override
	public int maxSerail() {
		return showRep.maxSerail();
	}

}
