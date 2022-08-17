package com.heavenstar.zandi.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.json.simple.parser.ParseException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.heavenstar.zandi.model.GitCommitVO;
import com.heavenstar.zandi.model.UserVO;
import com.heavenstar.zandi.service.GitService;
import com.heavenstar.zandi.service.UserService;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
@RequestMapping(value="/git")
public class GitController {
	
	@Autowired
	private GitService gitService;
	
	@Autowired
	private UserService userService;
	
	@RequestMapping(value="/home",method=RequestMethod.GET)
	public String home(){
		
		return "git/home";
	}
	
	@RequestMapping(value="/home",method=RequestMethod.POST)
	public String home(String repo, HttpSession session,Model model) throws IOException, ParseException{
		
		UserVO username = (UserVO)session.getAttribute("USER");
		
		GitCommitVO gitVO = gitService.oneCommit(username.getUsername(),repo);
		gitVO.setReponame(repo);
		model.addAttribute("REPO",gitVO);
		
		List<GitCommitVO> gitList = gitService.allCommit(username.getUsername(), repo);
		model.addAttribute("GITLIST",gitList);
		


		return "git/home";
	}
	
	
}
