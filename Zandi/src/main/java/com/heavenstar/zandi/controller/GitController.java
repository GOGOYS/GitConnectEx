package com.heavenstar.zandi.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.heavenstar.zandi.config.GithubApi;


@Controller(value="/git")
public class GitController {
	
	@Autowired
	private GithubApi gitapi;
	
	
	@RequestMapping(value={"/",","},method=RequestMethod.GET)
	public String home() {
		
		gitapi.getCommits("GOGOYS");
		
		return "home";
		
	}
}