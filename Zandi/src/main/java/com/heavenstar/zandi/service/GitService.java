package com.heavenstar.zandi.service;

import java.io.IOException;

import org.json.simple.parser.ParseException;

import com.heavenstar.zandi.model.GitCommitVO;

public interface GitService {
	
	public GitCommitVO gitcommit(String id, String repo) throws IOException, ParseException;
	public String dataTransate(String date);
	
}
