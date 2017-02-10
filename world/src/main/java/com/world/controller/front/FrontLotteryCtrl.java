package com.world.controller.front;

import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.world.entity.Config;
import com.world.entity.Gamer;
import com.world.entity.User;
import com.world.service.ConfigService;
import com.world.service.GamerService;
import com.world.service.SerialNumberService;
import com.world.service.UserService;
import com.world.service.UserVoteService;
import com.world.utils.ConstantUtils;
import com.world.utils.JsonUtils;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

/**
 * 抽奖控制器
 * 
 * @author yanbo
 */
@Controller
public class FrontLotteryCtrl extends ConstantUtils {

	@Autowired
	ConfigService configSvc;
	@Autowired
	UserService userSvc;
	@Autowired
	UserVoteService userVoteSvc;
	@Autowired
	SerialNumberService serialNumberSvc;
	@Autowired
	GamerService gamerSvc;
	
	// 欢乐大放送
	@RequestMapping("/front/lottery/rewardSerial")
	@ResponseBody
	public String reward(@RequestParam(name = "ids") String ids, @RequestParam(name = "numbers") Integer numbers,
			@RequestParam(name = "password") String password) throws UnsupportedEncodingException {
		if (!"ddn123".equals(password))
			return JsonUtils.defaultJsonToString("0", "Password error!");
		int success = 0;
		ids = URLDecoder.decode(ids, "utf8");
		if (ids != null && !"".equals(ids)) {
			ids = ids.replaceAll("，", ",");
			String[] sid = ids.split(",");
			Long[] id = new Long[sid.length];
			for (int i = 0; i < sid.length; i++) {
				if (!"".equals(sid[i]))
					id[i] = Long.valueOf(sid[i]);
			}
			success = userSvc.rewardSerialnumbers(numbers, id);
		}
		return JsonUtils.defaultJsonToString("0", "Success: " + success);
	}

	// 点击游戏抽奖：当点击结束的时候获取前N名
	@RequestMapping("/front/lottery/clickGame")
	@ResponseBody
	public String clickGame() {
		// 获取最大的
		List<User> users = userSvc.findAllClick();
		// 获得前几名有奖
		Config config = configSvc.findByKeyName("click_rank");
		int value = config == null ? 3 : Integer.parseInt(config.getKeyValue());
		JSONArray arrays = new JSONArray();
		for (int i = 0; i < users.size(); i++) {
			if (i < value)
				arrays.add(JsonUtils.objectToJson(users.get(i)));
			else
				break;
		}
		return arrays.toString();
	}

	// 上传图片
	@RequestMapping(value = "/front/user/upload", method = RequestMethod.POST)
	public String upload(@RequestParam("image") MultipartFile file, HttpSession session, HttpServletRequest request) {
		String basepath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort()
				+ request.getContextPath();
		if (session.getAttribute("user") == null)
			return JsonUtils.defaultJsonToString("0", "Please login first!");
		User user = (User) session.getAttribute("user");
		long id = user.getId();
		// 根据日期生成图片避免图片冲突
		String imageName = imageURI + sdf.format(new Date()) + ".jpg";
		try {
			if (id > 0)
				imageName = imageURI + id + ".jpg";
			if (!file.isEmpty()) {
				InputStream is = file.getInputStream();
				System.out.println("图片大小：" + file.getSize());
				String url = path + imageName;
				System.out.println(url);
				FileOutputStream fos = new FileOutputStream(new File(url));
				int len = -1;
				byte[] b = new byte[1024];
				while ((len = is.read(b)) != -1) {
					fos.write(b, 0, len);
				}
				fos.close();
				is.close();
				user.setImg(basepath + imageName);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		userSvc.update(user);
		return "redirect:/front/home";
	}

	//游戏首页
	@RequestMapping("/front/lottery/gamerHome")
	public String gamerHome(HttpSession session){
		if(session.getAttribute("user")==null) return "/front/login";
		return "/front/game";
	}
	
	// 点击参与游戏，前十五名才有中奖码
	@RequestMapping("/front/lottery/gamer")
	@ResponseBody
	public String gamer(HttpSession session) {
		User user=(User)session.getAttribute("user");
		if(user==null) return "/front/login";
		Gamer gamer=gamerSvc.findByUserId(user.getId());
		if(gamer==null){
			if(gamerSvc.getAmount()<15){
				gamer=new Gamer();
				gamer.setId(0);
				gamer.setUser(user);
				gamerSvc.save(gamer);
				return JsonUtils.defaultJsonToString("0", "Congratulations!");
			}else{
				return JsonUtils.defaultJsonToString("0", "Sorry,you are late!");
			}
		}
		return JsonUtils.defaultJsonToString("0", "You already success!");
	}
	@RequestMapping("/front/lottery/allGamer")
	@ResponseBody
	public String allGamer() throws UnsupportedEncodingException {
		List<Gamer> gamers=gamerSvc.findAllByOrderDesc();
		String username="";
		for (int i = 0; i < gamers.size(); i++) {
			Gamer gamer=gamers.get(i);
			if(i<15){
				username+=","+gamer.getUser().getUsername();
			}
		}
		if(!"".equals(username)) username=username.substring(1);
		return JsonUtils.defaultJsonToString("0",URLEncoder.encode(username,"utf8"));
	}
	
	@RequestMapping("/front/lottery/rewardGameScore")
	@ResponseBody
	public String rewardGameScore(){
		List<Gamer> gamers=gamerSvc.findAllByOrderDesc();
		Long[] ids=null;
		if(gamers.size()>15) ids=new Long[15];
		else ids=new Long[gamers.size()];
		for (int i = 0; i < gamers.size(); i++) {
			Gamer gamer=gamers.get(i);
			if(i<15){
				User user=gamer.getUser();
				ids[i]=user.getId();
			}
		}
		int success=userSvc.rewardSerialnumbers(3, ids);
		return JsonUtils.defaultJsonToString("0", "Success "+success+"!");
	}
	
	
	public static void main(String[] args) {
		
		
	}

}
