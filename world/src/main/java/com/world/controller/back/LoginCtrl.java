package com.world.controller.back;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class LoginCtrl {
	
	@RequestMapping(value="/user/login",method=RequestMethod.POST)
	public String doLogin(@RequestParam("name") String name,@RequestParam("password") String password,HttpServletRequest request,HttpSession session){
		String basepath=request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+request.getContextPath();
		request.getServletContext().setAttribute("basepath", basepath);
		System.out.println(name+" = "+password);
		if("yanbo".equals(name)&&"ddn123".equals(password)){
			session.setAttribute("username", name);
			return "redirect:/index";
		}else{
			return "redirect:/login";
		}
	}
	
	public LoginCtrl() {
		System.out.println("<><><><><><>终于。。。。。");
	}
	
}
