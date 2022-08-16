package com.heavenstar.zandi.service.impl;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.heavenstar.zandi.model.GitCommitVO;
import com.heavenstar.zandi.service.GitService;

import lombok.extern.slf4j.Slf4j;


@Slf4j
@Service
public class GitServiceImpl implements GitService{
	

	@Override
	public GitCommitVO gitcommit(String id, String repo) throws IOException, ParseException {
		//가장 최근 커밋
		
		
		String url = " https://api.github.com/repos/" + id + "/" + repo + "/commits";
		
		//파일 읽어들이기
        URL realUrl = new URL(url);
        HttpURLConnection conn = (HttpURLConnection) realUrl.openConnection();
        conn.setRequestMethod("GET");
        conn.setRequestProperty("Content-type", "application/json");
//        // log.debug("Response code: " + conn.getResponseCode());
        BufferedReader rd;
        if(conn.getResponseCode() >= 200 && conn.getResponseCode() <= 300) {
            rd = new BufferedReader(new InputStreamReader(conn.getInputStream()));
        } else {
            rd = new BufferedReader(new InputStreamReader(conn.getErrorStream()));
        }
        
        String retString = "";
        String line;
        while((line = rd.readLine()) != null) {
        	retString += line;
        }
        rd.close();
        conn.disconnect();
        
        log.debug("아아아아: {}",retString);
        
        //문자열을  JSON 객체로 변환
        JSONParser parse = new JSONParser();
        JSONArray arr = (JSONArray)parse.parse(retString);
        log.debug("에에에: {}",arr);
        
        log.debug("오오오오: {}", arr.get(0));
        JSONObject obj = (JSONObject)arr.get(0);
       
        JSONObject commit = (JSONObject)obj.get("commit");
        log.debug("커밋뭉치 : {}",commit);
        
        String json = commit.toString();
        log.debug("미미:{}",json);
        
        ObjectMapper mapper = new ObjectMapper();
        GitCommitVO gitVO = mapper.readValue(json, GitCommitVO.class);
        log.debug("마마마:{}",gitVO.toString());

		return gitVO;
	
	}

	@Override
	public String dataTransate(String date) {
		LocalDateTime localDateTime = LocalDateTime.now();
		
		String strDate2 = "2022-08-15T07:27:29Z";
		DateTimeFormatter format2 = DateTimeFormatter.ofPattern("yyyy-MM-ddTHH:mm");

		LocalDate strToLocalDateTime2 = LocalDate.parse(strDate2, format2);
		log.debug("strToLocalDateTime2 : " + strToLocalDateTime2);
		return null;
	}
	
	
	
	

}
