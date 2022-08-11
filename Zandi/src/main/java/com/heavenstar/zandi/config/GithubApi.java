package com.heavenstar.zandi.config;

import java.io.IOException;

import org.kohsuke.github.GHCommit;
import org.kohsuke.github.GHCommitSearchBuilder;
import org.kohsuke.github.GitHub;
import org.kohsuke.github.GitHubBuilder;
import org.kohsuke.github.PagedIterator;
import org.kohsuke.github.PagedSearchIterable;

public class GithubApi {
	
	
	  GitHub github;
	    String token = "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";
	    
	    public PagedIterator<GHCommit> getCommits(String userId) {
	    	try {
	        	connectToGithub(token); 
	        } 
	        catch (IOException e) { 
	        	throw new IllegalArgumentException("failed to connect gitHub");
	        }
	        
	        GHCommitSearchBuilder builder = github.searchCommits() 
	       		.author(userId)
	            	.sort(GHCommitSearchBuilder.Sort.AUTHOR_DATE); 
	        
	        PagedSearchIterable<GHCommit> commits = builder.list().withPageSize(7);
	        return commits._iterator(1);
	    } 
	    
	    private void connectToGithub(String token) throws IOException {
	    	github = new GitHubBuilder().withOAuthToken(token).build();
	        github.checkApiUrlValidity(); 
	    }

}
