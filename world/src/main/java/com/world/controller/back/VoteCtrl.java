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

import com.world.entity.Vote;
import com.world.service.VoteService;
import com.world.utils.JsonUtils;

import net.sf.json.JSONArray;

@SuppressWarnings("all")
@RestController
public class VoteCtrl {
	@Autowired
	VoteService voteSvc;
	
	@RequestMapping(value="/back/vote/save",method=RequestMethod.POST)
	public String add(Vote bean){
		if(bean!=null){
			voteSvc.save(bean);
		} 
		return JsonUtils.defaultJsonToString("0", "Add Success");
	}
	
	@RequestMapping(value="/back/vote/edit",method=RequestMethod.POST)
	public String edit(Vote bean){
		if(bean!=null){
			voteSvc.update(bean);
		}
		return JsonUtils.defaultJsonToString("0", "Update Success");
	}
	
	@RequestMapping(value="/back/vote/del/{id}",method=RequestMethod.GET)
	public String delete(@PathVariable Long id,Model model){
		voteSvc.delete(id);
		return JsonUtils.defaultJsonToString("0", "Delete Success");
	}
	
	@RequestMapping(value="/back/vote/list")
	public String list(){
		List<Vote> beans=voteSvc.findAll();
		JSONArray array=new JSONArray();
		for (Vote bean : beans) {
			array.add(JsonUtils.objectToJson(bean));
		}
		return JsonUtils.defaultJsonToString("0", URLEncoder.encode(array.toString()));
	}
	
	@RequestMapping("/back/vote/one")
	public String findOne(@RequestParam(value="id",required=true) Long id){
		return JsonUtils.defaultJsonToString("0",URLEncoder.encode( JsonUtils.objectToJson(voteSvc.findById(id)).toString()));
	}
	
}
