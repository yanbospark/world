package com.world.controller.back;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * 视图展现层
 * 
 * @author yanbo
 */
@Controller
public class ViewsCtrl {
	
	@RequestMapping("/login")
	public String login() {
		return "back/login";
	}

	@RequestMapping("/index")
	public String index() {
		return "back/index";
	}

	@RequestMapping("/view/user/list")
	public String user() {
		return "back/user/list";
	}

	@RequestMapping("/view/user/edit")
	public String user_edit() {
		return "back/user/edit";
	}

	@RequestMapping("/view/award/list")
	public String award() {
		return "back/award/list";
	}

	@RequestMapping("/view/award/edit")
	public String award_edit() {
		return "back/award/edit";
	}

	@RequestMapping("/view/config/list")
	public String config() {
		return "back/config/list";
	}

	@RequestMapping("/view/config/edit")
	public String config_edit() {
		return "back/config/edit";
	}

	@RequestMapping("/view/show/list")
	public String show() {
		return "back/show/list";
	}

	@RequestMapping("/view/show/edit")
	public String show_edit() {
		return "back/show/edit";
	}

	@RequestMapping("/view/vote/list")
	public String vote() {
		return "back/vote/list";
	}

	@RequestMapping("/view/vote/edit")
	public String vote_edit() {
		return "back/vote/edit";
	}

	@RequestMapping("/view/winner/list")
	public String winner() {
		return "back/winner/list";
	}

	@RequestMapping("/view/winner/edit")
	public String winner_edit() {
		return "back/winner/edit";
	}

	@RequestMapping("/view/userVote/list")
	public String userVote() {
		return "back/userVote/list";
	}

	@RequestMapping("/view/userVote/edit")
	public String userVote_edit() {
		return "back/userVote/edit";
	}

}
