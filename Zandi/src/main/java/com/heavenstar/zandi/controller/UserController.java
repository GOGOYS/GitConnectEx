package com.heavenstar.zandi.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.heavenstar.zandi.model.UserVO;
import com.heavenstar.zandi.service.UserService;

@Controller
@RequestMapping(value="/user")
public class UserController {
	
	@Autowired
	private UserService userService;
	
	@RequestMapping(value="/join",method=RequestMethod.GET)
	public String join() {
		
		return null;
	}
	
	@RequestMapping(value="/join",method=RequestMethod.POST)
	public String join(UserVO userVO) {
			
		userService.insert(userVO);
		
		return "user/login";
	}
	
	@RequestMapping(value="/login",method=RequestMethod.GET)
	public String login() {
		
		return "user/login";
	}
	@RequestMapping(value="/login",method=RequestMethod.POST)
	public String login(UserVO userVO) {
		
		userService.insert(userVO);
		return "home";
	}

}
