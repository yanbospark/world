package com.world.controller.back;

import java.net.URLEncoder;
import java.util.List;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.world.entity.Award;
import com.world.service.AwardService;
import com.world.utils.JsonUtils;
import net.sf.json.JSONArray;

@SuppressWarnings("all")
@RestController
public class AwardCtrl {
	
	Logger logger=Logger.getLogger(this.getClass());
	
	@Autowired
	AwardService awardSvc;
	
	@RequestMapping("/back/award/list")
	public String list(){
		List<Award> awards=awardSvc.findAll();
		JSONArray array=new JSONArray();
		for (Award award : awards) {
			array.add(JsonUtils.objectToJson(award));
		}
		logger.info(array.toString());
		String returnValue=URLEncoder.encode(array.toString());
		logger.info("returnValue="+returnValue);
		return JsonUtils.defaultJsonToString("0", returnValue);
	}
	
	@RequestMapping(value="/back/award/edit",method=RequestMethod.POST)
	public String edit(@RequestParam("id") Long id,
			@RequestParam(value="name",required=true) String name,
			@RequestParam("amount") Integer amount,
			@RequestParam("price") Float price){
		if(id>0){
			awardSvc.update(name, price, id);				
		}
		return JsonUtils.defaultJsonToString("0", "Success");
	}
	
	@RequestMapping(value="/back/award/save",method=RequestMethod.POST)
	public String add(Award award){
		System.out.println(award.toString());
		if(award!=null) awardSvc.save(award);
		return JsonUtils.defaultJsonToString("0", "Success");
	}
	
	
	@RequestMapping("/back/award/delete/{id}")
	public String delete(@PathVariable Long id){
		awardSvc.delete(id);
		return JsonUtils.defaultJsonToString("0", "Success");
	}

	@RequestMapping("/back/award/one")
	public String findOne(@RequestParam(value="id",required=true) Long id){
		if(id>0){
			Award award=awardSvc.findById(id);
			return JsonUtils.defaultJsonToString("0", URLEncoder.encode(JsonUtils.objectToJson(award).toString()));
		}
		return JsonUtils.defaultJsonToString("1", "{}");
	}
	
}
