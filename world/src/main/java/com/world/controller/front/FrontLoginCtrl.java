package com.world.controller.front;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.world.entity.User;
import com.world.service.UserService;

/**
 * 前端登陆控制
 * @author yanbo
 */
@Controller
public class FrontLoginCtrl {
	
	Logger logger=Logger.getLogger(getClass());
	
	@Autowired
	UserService userSvc;
	
	@RequestMapping("/front/login")
	public String login(HttpSession session){
		if(session.getAttribute("user")!=null) return "/front/choujiang";
		return "/front/login";
	}
	
	@RequestMapping("/front/home")
	public String home(HttpSession session){
		if(session.getAttribute("user")==null) return "/front/login";
		return  "/front/choujiang";
	}
	
	//手机端登录
	/**姓名、手机号、身份证后四位登陆**/
	@RequestMapping(value="/front/dologin",method={RequestMethod.POST,RequestMethod.GET})
	public String loginByUsername(@RequestParam(name="username",required=true) String username,HttpSession session){
		if(username!=null){
			User user=null;
			if(username.length()==11){//手机号登陆
				user=userSvc.findByTel(username);
			}else if(username.length()<=3){//姓名
				user=userSvc.findByUsername(username);
			}else if(username.length()==4){//身份证登陆
				List<User> users=userSvc.findByIdCardLike("%"+username);
				if(users.size()==0) return "/front/login";
				if(users.size()==1){
					user=users.get(0);
				}else{
					logger.error("username=\t"+username+" length=\t"+users.size());
				}
			}
			if(user!=null){
				session.setAttribute("user", user);
				session.setMaxInactiveInterval(2*60*60);
				return "/front/choujiang";
			}
		}
		return "/front/login";
	}
}
