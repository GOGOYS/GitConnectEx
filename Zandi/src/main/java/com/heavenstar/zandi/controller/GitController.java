package com.heavenstar.zandi.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.json.simple.parser.ParseException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.heavenstar.zandi.model.GitCommitVO;
import com.heavenstar.zandi.model.ReadmeVO;
import com.heavenstar.zandi.model.RepoVO;
import com.heavenstar.zandi.model.UserVO;
import com.heavenstar.zandi.service.GitService;
import com.heavenstar.zandi.service.RepoService;
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
	
	@Autowired
	private RepoService repoService;
	
	@RequestMapping(value={"/",""},method=RequestMethod.GET)
	public String home(HttpSession session, Model model){
		
		UserVO username = (UserVO)session.getAttribute("USER");
		
		List<RepoVO> repoList = repoService.findByRepo(username.username);
		if(!(repoList == null)) {
			model.addAttribute("REPOLIST",repoList);
		}
		
		return "git/home";
	}
	
	@RequestMapping(value={"","/"},method=RequestMethod.POST)
	public String home(String repo, HttpSession session) throws IOException, ParseException{
		
		UserVO username = (UserVO)session.getAttribute("USER");
		
		RepoVO repoVO = new RepoVO();
		
		repoVO.setR_username(username.username);
		repoVO.setR_reponame(repo);
		log.debug(repoVO.toString());
		
		
		repoService.insert(repoVO);
		

		return "redirect:/git/home";
	}
	
	@RequestMapping(value="/detail_repo/{repo}",method=RequestMethod.GET)
	public String detail_repo(@PathVariable("repo") String repo,HttpSession session, Model model)throws IOException, ParseException {
		
		UserVO username = (UserVO)session.getAttribute("USER");
		
		//?????? ?????? ????????????
		GitCommitVO gitVO = gitService.oneCommit(username.getUsername(),repo);
		gitVO.setReponame(repo);
		model.addAttribute("REPO",gitVO);
		
		//????????? ????????????
		ReadmeVO readmeVO = gitService.getReadme(username.getUsername(),repo);
		model.addAttribute("README",readmeVO);
		
		//????????? ?????? ????????????
		List<GitCommitVO> gitList = gitService.allCommit(username.getUsername(), repo);
		model.addAttribute("GITLIST",gitList);
		
		//gitService.readmeTransate(readmeVO.getContent());
		
		return "git/detail/_repo";
	}
	
	
}
