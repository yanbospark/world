
package com.world.controller.front;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.world.entity.Config;
import com.world.entity.Show;
import com.world.entity.User;
import com.world.service.ConfigService;
import com.world.service.SerialNumberService;
import com.world.service.ShowService;
import com.world.service.UserService;
import com.world.service.UserVoteService;
import com.world.utils.ConstantUtils;
import com.world.utils.JsonUtils;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

/**
 * 抽奖页面公共控制器
 * 
 * @author yanbo
 */
@Controller
public class FrontCommonShowCtrl extends ConstantUtils{
	
	Logger logger=Logger.getLogger(getClass());

	@Autowired
	UserService userSvc;
	@Autowired
	UserVoteService userVoteSvc;
	@Autowired
	ShowService showSvc;
	@Autowired
	ConfigService configSvc;
	@Autowired
	SerialNumberService serialNumberSvc;
	/**PC端首页  */
	@RequestMapping("/front/index")
	public String index(){
		return "/front/index";
	}
	
	@RequestMapping("/front/control")
	public String control(){
		return "/front/control";
	}
	
	//TODO 改变抽奖的方式
	@RequestMapping("/front/commonShow/change")
	@ResponseBody
	public String changeStyle(@RequestParam(name="type",required=true) String type){
		//type=0是中奖码、1是图片、2是姓名
		Config bean=configSvc.findByKeyName("win_type");
		bean.setKeyValue(type);
		configSvc.update(bean);
		return JsonUtils.defaultJsonToString("0", bean.getKeyValue());
	}
	
	//TODO 更改排名状态：默认未开始
	@RequestMapping("/front/commonShow/changeRankState")
	@ResponseBody
	public String changeRankState(){
		//更改投票为结束状态，已经结束了评选
		//如果成立表示已经结束了评选
		Config over=configSvc.findByKeyName("show_over");//节目是否已经结束了评选
		if(over!=null&&"1".equals(over.getKeyValue())) return JsonUtils.defaultJsonToString("0", "Already Finish!");
		//根据总的票数计算排名、并给对应用户加中奖码、以，号拆分
		List<Show> shows=showSvc.findOrderByCounts();
		for (int i = 0; i < shows.size(); i++) {
			Show show=shows.get(i);
			long showId=show.getId();
			String userIds=show.getUserIds();
			if(StringUtils.isNotEmpty(userIds)){
				userIds=userIds.replaceAll("，", ",").replace(" ", "");
				String[] u_ids=userIds.split(",");
				Long[] ids=new Long[u_ids.length];
				for (int j = 0; j < u_ids.length; j++) {
					ids[j]=Long.valueOf(u_ids[j]);
				}
				if(i==0){//第一名加6分
					userSvc.rewardSerialnumbers(6, ids);
				}else if(i==1){//第二名加4分
					userSvc.rewardSerialnumbers(4, ids);
				}else if(i>1){//第三名加2分
					userSvc.rewardSerialnumbers(2, ids);
				}
			}
			showSvc.updateRank(i+1, showId);
		}
		over.setKeyValue("1");//排名统计结束
		configSvc.update(over);
		return JsonUtils.defaultJsonToString("0", "Success");
	}
	
	//TODO 点击开始下一个节目
	@RequestMapping("/front/commonShow/nextShow")
	@ResponseBody
	public String nextShow(){
		//查询正在进行的节目并标注为：演出结束，投票开始
		String time=simpleTime.format(new Date());
		Show processing=showSvc.findProcessing();
		//查询出为空的情况：要么没开始，要么都结束了
		if(processing==null){
			processing=showSvc.findBySerial(1);
			if(processing.getShowState()==0) showSvc.updateStartTime(time, 1, processing.getId());
		}else{
			int maxSerial=showSvc.maxSerail();
			//最后一个节目,没有下一个，得将所有的节目状态和投票状态改为结束
			if(processing.getSerial()==maxSerial){
				List<Show> shows=showSvc.findByState(1);
				for (Show show : shows) {
					showSvc.updateVoteState(0, 2, show.getId());
				}
				showSvc.updateShowEndTime(time, 2, 2, processing.getId());
			}else{
				//把当前的节目标识为已结束，投票进行中
				showSvc.updateShowEndTime(time, 2, 1, processing.getId());
				//下一个节目的序号
				int nextShowSerial=processing.getSerial()+1;
				Show nextShow=showSvc.findBySerial(nextShowSerial);
				if(nextShow!=null) {
					showSvc.updateStartTime(time, 1, nextShow.getId());
					//将最后一个投票状态改为正在进行中
					if(nextShow.getSerial()==maxSerial) showSvc.updateVoteState(0,1,nextShow.getId());
					//将比前三个还前的节目投票状态改为已结束
					if(nextShowSerial>3){
						Show beforeShow=showSvc.findBySerial(nextShowSerial-3);
						if(beforeShow!=null) showSvc.updateVoteState(0, 2, beforeShow.getId());
					}
				}
			}
		}
		return JsonUtils.defaultJsonToString("0", "Success");
	}
	
	// TODO 查询所有已经点击过的人:滚动显示，每3秒钟刷新一次
	@RequestMapping("/front/commonShow/rankShow")
	@ResponseBody
	public String rankShow() {
		List<User> users = userSvc.findAllClick();
		JSONArray arrays = new JSONArray();
		for (User user : users) {
			arrays.add(user);
		}
		return JsonUtils.defaultJsonToString("0", URLEncoder.encode(arrays.toString()));
	}

	//TODO 显示用户图片/中奖码供抽奖
	@RequestMapping("/front/commonShow/imgShow")
	@ResponseBody
	public String imgShow() throws UnsupportedEncodingException {
		Config bean=configSvc.findByKeyName("win_type");
		String type=bean.getKeyValue();
		JSONObject obj = new JSONObject();
		Config config=configSvc.findByKeyName("max_win");
		if(config==null) config=new Config(0,"","1","");
		int max_win=Integer.valueOf(config.getKeyValue());
		if("1".equals(type)){//图片抽奖
			List<String> images = userSvc.findAllImages(max_win);
			obj.put("images", images);//个人头像
			obj.put("type", "1");
		}else if("0".equals(type)){//中奖码抽奖
			List<User> users=userSvc.findByWinTimesGreaterThan(max_win);
			List<String> deleteSerial=new ArrayList<String>();
			for (User user : users) {
				String[] sm= user.getSerialnumbers().split(",");
				for (int i = 0; i < sm.length; i++) {
					deleteSerial.add(sm[i]);
				}
			}
			int len=serialNumberSvc.getIDValue("User")-1;
			List<String> serialnumbers=new ArrayList<String>();
			for (int i = 1; i <= len; i++) {
				serialnumbers.add(userSvc.fillSerialNumber(i));
			}
			//移除多余的
			for(int j=0;j< deleteSerial.size();j++){
				serialnumbers.remove(deleteSerial.get(j));
			}
			logger.info("总中奖码："+len+" 已中过"+deleteSerial.size()+" 实际个数："+serialnumbers.size());
			Collections.shuffle(serialnumbers);
			obj.put("images", serialnumbers);//中奖码
			obj.put("type", "0");
		}else{
			List<String> allUsername=userSvc.findAllUsername(max_win);
			obj.put("images", URLEncoder.encode(allUsername.toString(), "utf8").replace("+", "%20"));
			obj.put("type", "2");
		}
		return obj.toString();
	}

	
	//TODO 节目信息
	@RequestMapping("/front/commonShow/teamShow")
	@ResponseBody
	public String teamShow() {
		Config config=configSvc.findByKeyName("show_over");//排名状态
		String state="0";
		if(config!=null) state=config.getKeyValue();
		JSONArray arrays = new JSONArray();
		List<Show> shows=new ArrayList<>();
		if(state.equals("0")){//节目未结束
			shows=showSvc.findAll();
			for (Show vote: shows) {
				arrays.add(vote);
			}
		}else{
			shows=showSvc.findOrderByCounts();
			if(shows.size()>3){
				arrays.add(shows.get(0));
				arrays.add(shows.get(1));
				arrays.add(shows.get(2));
			}
		}
		return JsonUtils.defaultJsonToString(state, URLEncoder.encode(arrays.toString()));
	}
	
	//TODO 用户信息
	@RequestMapping("/front/commonUser/userShow")
	@ResponseBody
	public String userShow() throws UnsupportedEncodingException{
		List<Object[]> users=userSvc.findOrderbyNumbers();
		JSONArray arrays = new JSONArray();
		JSONObject json=new JSONObject();
		for (int i = 0; i < users.size(); i++) {
			if(i<15){
				Object[] user=users.get(i);
				json.put("username", user[0]);
				json.put("serials", user[1]);
				json.put("numbers", user[2]);
				json.put("winTimes", user[3]);
				arrays.add(json);
			}
		}
		return JsonUtils.defaultJsonToString("0", URLEncoder.encode(arrays.toString(),"utf8"));
	}
	
	//中奖后双击获取中奖人姓名
	@RequestMapping("/front/commonShow/username")
	@ResponseBody
	public String username(@RequestParam(name="serial",required=true) String serial) throws UnsupportedEncodingException{
		List<Object[]> users=userSvc.findBySerialLike("%"+serial+"%");
		String username="";
		System.out.println(serial+"<>一个<>"+users.size());
		if(users.size()>0) {
			Object[] obj=users.get(0);
			long id=(Long)obj[0];
			username=(String)obj[1];
			int winTimes=(Integer)obj[2];
			userSvc.updateWinTimes(winTimes+1, id);
		}
		return JsonUtils.defaultJsonToString("0", URLEncoder.encode(username, "utf8"));
	}
	//名字中奖的中奖次数操作
	@RequestMapping("/front/commonShow/addWinTimes")
	@ResponseBody
	public String addWinTimes(@RequestParam(name="username",required=true) String username) throws UnsupportedEncodingException{
		username=URLDecoder.decode(username,"utf8");
		User user=userSvc.findByUsername(username);
		if(user!=null) {
			long id=user.getId();
			int winTimes=user.getWinTimes();
			userSvc.updateWinTimes(winTimes+1, id);
		}
		return JsonUtils.defaultJsonToString("0", "");
	}
}
