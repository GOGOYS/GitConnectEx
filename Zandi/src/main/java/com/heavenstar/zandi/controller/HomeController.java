package com.heavenstar.zandi.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.heavenstar.zandi.config.GithubConfig;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
public class HomeController {
	
	@Autowired
	private GithubConfig gitApi; 
	

	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String home() {

		
		
		return "home";
	}
	
}
