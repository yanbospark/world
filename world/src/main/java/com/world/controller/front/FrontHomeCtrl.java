package com.world.controller.front;

import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.world.entity.Show;
import com.world.entity.User;
import com.world.entity.UserVote;
import com.world.service.ShowService;
import com.world.service.UserService;
import com.world.service.UserVoteService;
import com.world.utils.CacheUtils;
import com.world.utils.JsonUtils;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

@SuppressWarnings("all")
@RestController
public class FrontHomeCtrl {
	
	Logger logger=Logger.getLogger(getClass());
	@Autowired
	UserService userSvc;
	@Autowired
	ShowService showSvc;
	@Autowired
	UserVoteService userVoteSvc;
	
	//获取用户信息
	@RequestMapping("/front/home/userinfo")
	public String userinfo(HttpSession session){
		User user=(User)session.getAttribute("user");
		if(user!=null){
			user=userSvc.findById(user.getId());
			return JsonUtils.defaultJsonToString("0", URLEncoder.encode(JsonUtils.objectToJson(user).toString()));
		}else{
			return JsonUtils.defaultJsonToString("1", "User is not exist!");
		}
	}
	
	//获取节目信息
	@RequestMapping("/front/home/showinfo/{maxSerial}")
	public String showinfo(@PathVariable Integer maxSerial,HttpSession session){
		Show show=null;
		List<Show> showes=new ArrayList<>();
		JSONArray array=new JSONArray();
		int nextMaxSerial=maxSerial;
		if(maxSerial!=null&&maxSerial>2){//已经加载过
			show=showSvc.findBySerial(nextMaxSerial);
			if(show!=null&&show.getShowState()<=1){//说明节目在进行中,或者未开始
				showes=showSvc.findBySerialLessThan(nextMaxSerial+1);//更新到这个正在进行的节目
			}else if(show!=null&&show.getShowState()==2){//节目结束了，就继续查下一个节目
				nextMaxSerial+=1;
				showes=showSvc.findBySerialLessThan(nextMaxSerial+1);//显示到这个正在进行的节目
			}
		}else if(maxSerial==2){
			//查询正在进行的节目
			show=showSvc.findProcessing();
			if(show!=null){
				int serial=show.getSerial();//这是第几个节目在进行中
				if(serial<3){
					nextMaxSerial=2;
					showes=showSvc.findBySerialLessThan(3);//显示到第二个节目
				}else{
					nextMaxSerial=serial;
					showes=showSvc.findBySerialLessThan(serial+1);//显示到这个正在进行的节目
				}
			}else{
				nextMaxSerial=2;
				showes=showSvc.findBySerialLessThan(3);//显示到第二个节目
			}
		}
		User user=(User)session.getAttribute("user");
		List<UserVote> userVotes=new ArrayList<>();
		if(user!=null){
			userVotes=userVoteSvc.haveVoted(user.getId());
		}
		//将用户投了这个节目的分数传过去
		for (Show show2 : showes) {
			JSONObject json=JsonUtils.objectToJson(show2);
			for (UserVote userVote : userVotes) {
				if(show2.getId()==userVote.getShow().getId()){
					json.put("score", userVote.getScore());
					continue;
				}
			}
			array.add(json);
		}
		return JsonUtils.defineJsonToString("code","0","message",URLEncoder.encode(array.toString()),"maxSerial",String.valueOf(nextMaxSerial));
	}
	
	//点击次数统计
	//如果用户已经点过开始了，就不能通过刷新这种方式重来:简称为  一旦开始就不能回头
	@RequestMapping(value="/front/home/click",method=RequestMethod.POST)
	public String click(@RequestParam(name="count",required=false) Integer count,HttpSession session){
		User user=(User)session.getAttribute("user");
		if(user!=null){
			long id=user.getId();
			//判断是否已经开始了,避免重复刷新这种情况
			if(count==null){
				if (CacheUtils.getInt("start_" + id) == 0) {
					CacheUtils.put("start_" + id, 1, 20 * 60 * 1000);
					return JsonUtils.defaultJsonToString("0", "First Time!");
				}else{
					return JsonUtils.defaultJsonToString("1", "End Time!");
				}
			}else if(count!=null&&count>0){
				if (CacheUtils.getInt("start_" + id) == 0) {
					CacheUtils.put("start_" + id, 1, 20 * 60 * 1000);
					return JsonUtils.defaultJsonToString("0", "First Time!");
				} else if (CacheUtils.getInt("start_" + id) == 1) {
					logger.info("userId="+id+"\tcount="+count);
					userSvc.updateClickCount(count, id);
					//统计完将状态改为已抽过
					CacheUtils.put("start_"+id, 2,20*60*1000);
					return JsonUtils.defaultJsonToString("0", "Finish Time!");
				} else if (CacheUtils.getInt("start_" + id) == 2) {
					return JsonUtils.defaultJsonToString("0", "Finish Time!");
				}
			}
		}
		return JsonUtils.defaultJsonToString("1", "Login in time out!");
	}
	
	/**员工对节目进行投票**/
	@RequestMapping(value="/front/home/vote")
	public String vote(@RequestParam(name="showId",required=true) Long showId,
			@RequestParam(name="score",required=true) Integer score,
			HttpSession session){
		User user=(User)session.getAttribute("user");
		if(user==null) return JsonUtils.defaultJsonToString("1", "Please login in first!");
		long userId=user.getId();
		if(showId!=null){
			Show show=showSvc.findById(showId);
			if(show!=null){
				//当节目结束并且投票状态为正在进行中或者是最后一个节目，才能投票
				int maxSrial=showSvc.maxSerail();
				if((show.getShowState()==2||maxSrial==show.getSerial())&&show.getState()==1){
					//没投过票
					UserVote userVote=userVoteSvc.ifVoted(userId, showId);
					if(userVote==null){
						userVoteSvc.save(new UserVote(0, new Show(showId), user, score));
						//增加总得票数
						showSvc.updateVoteState(score, 1, showId);
						user=userSvc.findById(userId);
						String oldSerialNumber=user.getSerialnumbers();
						/** 增加额外中奖号*/
						userSvc.updateSerialnumbers(oldSerialNumber, userId);
					}else{
						userVoteSvc.update(score,userId,showId);
						//增加或者减少票数
						if(score-userVote.getScore()!=0) showSvc.updateVoteState(score-userVote.getScore(), 1, showId);
					}
				}else{
					return JsonUtils.defaultJsonToString("2", "Vote is over or not start!");
				}
			}
		}
		return JsonUtils.defaultJsonToString("0", "Success!");
	}
	
	
	
}
