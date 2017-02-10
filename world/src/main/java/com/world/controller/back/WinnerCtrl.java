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

import com.world.entity.Winner;
import com.world.service.WinnerService;
import com.world.utils.JsonUtils;

import net.sf.json.JSONArray;

@SuppressWarnings("all")
@RestController
public class WinnerCtrl {
	@Autowired
	WinnerService winnerSvc;
	
	@RequestMapping(value="/back/winner/save",method=RequestMethod.POST)
	public String add(Winner bean){
		if(bean!=null){
			winnerSvc.save(bean);
		} 
		return JsonUtils.defaultJsonToString("0", "Add Success");
	}
	
	@RequestMapping(value="/back/winner/edit",method=RequestMethod.POST)
	public String edit(Winner bean){
		if(bean!=null){
			winnerSvc.update(bean);
		}
		return JsonUtils.defaultJsonToString("0", "Update Success");
	}
	
	@RequestMapping(value="/back/winner/del/{id}",method=RequestMethod.GET)
	public String delete(@PathVariable Long id,Model model){
		winnerSvc.delete(id);
		return JsonUtils.defaultJsonToString("0", "Delete Success");
	}
	
	@RequestMapping(value="/back/winner/list")
	public String list(){
		List<Winner> beans=winnerSvc.findAll();
		JSONArray array=new JSONArray();
		for (Winner bean : beans) {
			array.add(JsonUtils.objectToJson(bean));
		}
		return JsonUtils.defaultJsonToString("0", URLEncoder.encode(array.toString()));
	}
	
	@RequestMapping("/back/winner/one")
	public String findOne(@RequestParam(value="id",required=true) Long id){
		return JsonUtils.defaultJsonToString("0",URLEncoder.encode( JsonUtils.objectToJson(winnerSvc.findById(id)).toString()));
	}
	
}
