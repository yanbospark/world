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

import com.world.entity.UserVote;
import com.world.service.UserVoteService;
import com.world.utils.JsonUtils;

import net.sf.json.JSONArray;

@SuppressWarnings("all")
@RestController
public class UserVoteCtrl {
	@Autowired
	UserVoteService userVoteSvc;
	
	@RequestMapping(value="/back/userVote/save",method=RequestMethod.POST)
	public String add(UserVote bean){
		if(bean!=null){
			userVoteSvc.save(bean);
		} 
		return JsonUtils.defaultJsonToString("0", "Add Success");
	}
	
	@RequestMapping(value="/back/userVote/edit",method=RequestMethod.POST)
	public String edit(UserVote bean){
		if(bean!=null){
			userVoteSvc.update(bean);
		}
		return JsonUtils.defaultJsonToString("0", "Update Success");
	}
	
	@RequestMapping(value="/back/userVote/del/{id}",method=RequestMethod.GET)
	public String delete(@PathVariable Long id,Model model){
		userVoteSvc.delete(id);
		return JsonUtils.defaultJsonToString("0", "Delete Success");
	}
	
	@RequestMapping(value="/back/userVote/list")
	public String list(){
		List<UserVote> beans=userVoteSvc.findAll();
		JSONArray array=new JSONArray();
		for (UserVote bean : beans) {
			array.add(JsonUtils.objectToJson(bean));
		}
		return JsonUtils.defaultJsonToString("0", URLEncoder.encode(array.toString()));
	}
	
	@RequestMapping("/back/userVote/one")
	public String findOne(@RequestParam(value="id",required=true) Long id){
		return JsonUtils.defaultJsonToString("0",URLEncoder.encode( JsonUtils.objectToJson(userVoteSvc.findById(id)).toString()));
	}
	
}
