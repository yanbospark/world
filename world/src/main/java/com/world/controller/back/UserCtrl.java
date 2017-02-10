package com.world.controller.back;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang.StringEscapeUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.world.entity.User;
import com.world.service.UserService;
import com.world.utils.ConstantUtils;
import com.world.utils.JsonUtils;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

@SuppressWarnings("all")
@RestController
public class UserCtrl extends ConstantUtils{
	
	Logger logger=Logger.getLogger(this.getClass());
	
	@Autowired 
	UserService userSvc;
	
	@RequestMapping(value="/back/user/save",method=RequestMethod.POST)
	public String add(User user,HttpServletRequest request){
		String basepath=(String) request.getServletContext().getAttribute("basepath");
		String imgUrl=basepath+imageURI+"/bg.png";//设置默认图片
		if(user!=null){
			if(user.getImg()==null||user.getImg().equals("")) user.setImg(imgUrl);
			userSvc.save(user);
		} 
		return JsonUtils.defaultJsonToString("0", "Add Success");
	}
	
	@RequestMapping(value="/back/user/edit",method=RequestMethod.POST)
	public String edit(User user){
		if(user!=null&&user.getId()>0){
			userSvc.update(user);
		}
		return JsonUtils.defaultJsonToString("0", "Update Success");
	}
	
	@RequestMapping(value="/back/user/del/{id}",method=RequestMethod.GET)
	public String delete(@PathVariable Long id,Model model){
		userSvc.delete(id);
		return JsonUtils.defaultJsonToString("0", "Delete Success");
	}
	
	@RequestMapping(value="/back/user/list")
	public String list(){
		List<User> users=userSvc.findAll();
		JSONArray array=new JSONArray();
		for (User user : users) {
			array.add(JsonUtils.objectToJson(user));
		}
		return JsonUtils.defaultJsonToString("0", URLEncoder.encode(array.toString()));
	}
	
	@RequestMapping("/back/user/one")
	public String findOne(@RequestParam(value="id",required=true) Long id){
		return JsonUtils.defaultJsonToString("0",URLEncoder.encode( JsonUtils.objectToJson(userSvc.findById(id)).toString()));
	}
	
	/** 异步下载图片*/
	@RequestMapping(value="/back/user/upload",method=RequestMethod.POST)
	public String upload(@RequestParam("image") MultipartFile file, @RequestParam("id") Long id,HttpServletRequest request) {
		String basepath=request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+request.getContextPath();
		//根据日期生成图片避免图片冲突
		String imageName=imageURI+sdf.format(new Date())+".jpg";
		try {
			if(id!=null&&id>0) imageName=imageURI+id+".jpg";
			if (!file.isEmpty()) {
				InputStream is=file.getInputStream();
				String url=path+imageName;
				System.out.println(url);
				FileOutputStream fos=new FileOutputStream(new File(url));
				int len=-1;
				byte[] b=new byte[1024];
				while ((len = is.read(b)) != -1) {
					fos.write(b, 0, len);
				}
				fos.close();
				is.close();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return JsonUtils.defaultJsonToString("0", basepath+imageName);
	}
	
	@RequestMapping(value="/back/user/byUsername/{username}",produces={"text/html","application/json"})
	public String findByUsername(@PathVariable String username){
		System.out.println(URLDecoder.decode(username));
		username=URLDecoder.decode(username);
		if(username==null) return JsonUtils.defaultJsonToString("0", "");
		List<User> users=userSvc.findByUsernameLike("%"+username+"%");
		JSONArray array=new JSONArray();
		for (User user : users) {
			array.add(JsonUtils.objectToJson(user));
		}
		return JsonUtils.defaultJsonToString("0", URLEncoder.encode(array.toString()));
	}
	
	public UserCtrl(){
		logger.info("===========Create UserCtrl!");
	}
}
