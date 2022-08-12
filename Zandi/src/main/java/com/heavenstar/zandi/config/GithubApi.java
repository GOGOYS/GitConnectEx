package com.heavenstar.zandi.config;

import java.util.logging.Logger;

import org.kohsuke.github.GitHub;
import org.kohsuke.github.GitHubBuilder;
import org.springframework.stereotype.Controller;


@Controller
public class GithubApi {
	 	private static final String personalToken = "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";
	 	private static final Logger LOG = Logger.getGlobal();
	    private GitHub github;
	
	    public GithubApi() {
	        try {// 깃허브 객체 생성
	            this.github = new GitHubBuilder().withOAuthToken(personalToken).build();
	            LOG.info("깃 토큰 연결 성공");
	        } catch (Exception e) {
	            LOG.info("깃 토큰 연결 실패");
	        }
	    }

	    public GitHub getConnection() {
	        return github;
	    }
	    
	    public Logger getLog() {
	        return LOG;
	    }
	        

}
