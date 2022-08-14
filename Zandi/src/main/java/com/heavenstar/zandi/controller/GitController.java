package com.heavenstar.zandi.controller;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.heavenstar.zandi.model.UserVO;
import com.heavenstar.zandi.service.GitService;


@Controller
@RequestMapping(value="/git")
public class GitController {
	
	@Autowired
	private GitService gitService;
	
	@RequestMapping(value="/ex",method=RequestMethod.GET)
	public String gitcon() throws IOException {
		
		gitService.gitcommit("gogoys","Zandi");
		return "git/home";
	}
	
	@RequestMapping(value="/ex",method=RequestMethod.POST)
	public String gitcon(String username, String password) throws IOException {
		
	
		
		gitService.gitcommit("gogoys","Zandi");
		
		return "git/home";
	}
	
	
}
