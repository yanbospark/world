package com.world.controller.weixin;

import java.util.Arrays;

import org.apache.log4j.Logger;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.world.weixin.constant.WXConfigConstant;
import com.world.weixin.help.CheckHelp;

@RestController
public class WXEnterCtrl extends WXConfigConstant{
	
	Logger logger=Logger.getLogger(getClass());
	
	@RequestMapping(value="/weixin/enter",method={RequestMethod.GET})
	public String enter(@RequestParam(name="signature",required=true) String signature,
			@RequestParam(name="timestamp",required=true) String timestamp,
			@RequestParam(name="nonce",required=true) String nonce,
			@RequestParam(name="echostr",required=true) String echostr){
		logger.info("signature="+signature+"\rnonce="+nonce+"\rechostr="+echostr+"\rtimestamp="+timestamp);
		String[] arr={TOKEN,nonce,timestamp};
		Arrays.sort(arr);
		StringBuffer sb=new StringBuffer(arr.length);
		for (int i = 0; i < arr.length; i++) {
			sb.append(arr[i]);
		}
		if(CheckHelp.checkEnter(signature, sb.toString())){
			return echostr;
		}
		return "";
	}
	
	
	
}
