package com.world.weixin.help;

import com.world.utils.SHA1Utils;

public class CheckHelp {
	
	public static boolean checkEnter(String signature,String mySignature){
		return SHA1Utils.getSha1(mySignature).equals(signature);
	}
	
	
}
