package com.world.controller.back;

import java.net.URLEncoder;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.world.entity.Config;
import com.world.service.ConfigService;
import com.world.utils.JsonUtils;

import net.sf.json.JSONArray;

@SuppressWarnings("all")
@RestController
public class ConfigCtrl {
	@Autowired
	ConfigService configSvc;
	
	@RequestMapping(value="/back/config/save",method=RequestMethod.POST)
	public String add(Config bean){
		if(bean!=null){
			configSvc.save(bean);
		} 
		return JsonUtils.defaultJsonToString("0", "Add Success");
	}
	
	@RequestMapping(value="/back/config/edit",method=RequestMethod.POST)
	public String edit(Config bean){
		if(bean!=null){
			configSvc.update(bean);
		}
		return JsonUtils.defaultJsonToString("0", "Update Success");
	}
	
	@RequestMapping(value="/back/config/del/{id}",method=RequestMethod.GET)
	public String delete(@PathVariable Long id,Model model){
		configSvc.delete(id);
		return JsonUtils.defaultJsonToString("0", "Delete Success");
	}
	
	@RequestMapping(value="/back/config/list")
	public String list(){
		List<Config> beans=configSvc.findAll();
		JSONArray array=new JSONArray();
		for (Config bean : beans) {
			array.add(JsonUtils.objectToJson(bean));
		}
		return JsonUtils.defaultJsonToString("0", URLEncoder.encode(array.toString()));
	}
	
	@RequestMapping("/back/config/one")
	public String findOne(@RequestParam(value="id",required=true) Long id){
		return JsonUtils.defaultJsonToString("0",URLEncoder.encode( JsonUtils.objectToJson(configSvc.findById(id)).toString()));
	}
	
}
