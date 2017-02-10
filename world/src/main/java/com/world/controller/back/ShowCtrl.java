package com.world.controller.back;

import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.net.URLEncoder;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.world.entity.Show;
import com.world.service.ShowService;
import com.world.utils.ConstantUtils;
import com.world.utils.JsonUtils;

import net.sf.json.JSONArray;

@SuppressWarnings("all")
@RestController
public class ShowCtrl extends ConstantUtils{
	@Autowired
	ShowService showSvc;
	
	@RequestMapping(value="/back/show/save",method=RequestMethod.POST)
	public String add(Show bean,HttpServletRequest request){
		String basepath=(String)request.getServletContext().getAttribute("basepath");
		String teamImg=basepath+imageURI+"/new.jpg";
		if(bean!=null){
			if(bean.getTeamImg()==null||bean.getTeamImg().equals("")) bean.setTeamImg(teamImg);
			showSvc.save(bean);
		} 
		return JsonUtils.defaultJsonToString("0", "Add Success");
	}
	
	@RequestMapping(value="/back/show/edit",method=RequestMethod.POST)
	public String edit(Show bean){
		if(bean!=null){
			showSvc.update(bean);
		}
		return JsonUtils.defaultJsonToString("0", "Update Success");
	}
	
	@RequestMapping(value="/back/show/del/{id}",method=RequestMethod.GET)
	public String delete(@PathVariable Long id,Model model){
		showSvc.delete(id);
		return JsonUtils.defaultJsonToString("0", "Delete Success");
	}
	
	@RequestMapping(value="/back/show/list")
	public String list(){
		List<Show> beans=showSvc.findAll();
		JSONArray array=new JSONArray();
		for (Show bean : beans) {
			array.add(JsonUtils.objectToJson(bean));
		}
		return JsonUtils.defaultJsonToString("0", URLEncoder.encode(array.toString()));
	}
	
	@RequestMapping("/back/show/one")
	public String findOne(@RequestParam(value="id",required=true) Long id){
		return JsonUtils.defaultJsonToString("0",URLEncoder.encode( JsonUtils.objectToJson(showSvc.findById(id)).toString()));
	}
	
	/** 异步下载图片*/
	@RequestMapping(value="/back/show/upload",method=RequestMethod.POST)
	public String upload(@RequestParam("image") MultipartFile file, @RequestParam("id") Long id,HttpServletRequest request) {
		String basepath=request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+request.getContextPath();
		//根据日期生成图片避免图片冲突
		String imageName=imageURI+sdf.format(new Date())+"_team.jpg";
		try {
			if(id!=null&&id>0) imageName=imageURI+id+"_team.jpg";
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
}
