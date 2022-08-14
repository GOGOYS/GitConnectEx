package com.heavenstar.zandi.service;

import java.io.IOException;

import org.json.simple.parser.ParseException;

public interface GitService {
	
	public String gitcommit(String id, String repo) throws IOException, ParseException;
	
}
