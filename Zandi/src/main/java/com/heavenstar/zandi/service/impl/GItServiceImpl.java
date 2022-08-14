package com.heavenstar.zandi.service.impl;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLEncoder;

import org.json.JSONArray;
import org.json.JSONObject;
import org.json.XML;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.heavenstar.zandi.config.GithubConfig;
import com.heavenstar.zandi.service.GitService;

import lombok.extern.slf4j.Slf4j;


@Slf4j
@Service
public class GitServiceImpl implements GitService{
	
	@Autowired
	private GithubConfig token;

	@Override
	public String gitcommit(String id, String repo) throws IOException {
		
		
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
        
        JSONObject json = XML.toJSONObject(retString);
        String jsonStr = json.toString(4);
        
        JSONObject jObject = new JSONObject(jsonStr);
        
      //response 안으로
        JSONObject response = jObject.getJSONObject("commit");
        //log.debug(response.toString(4));
        
        // body 안으로
        JSONObject body = response.getJSONObject("body");
        //log.debug(body.toString(4));
        
        //items 안으로
        JSONObject items = body.getJSONObject("items");
        //log.debug(items.toString(4));
        
        //item은 배열로 생성
        JSONArray item = items.getJSONArray("item");
        JSONObject it = item.getJSONObject(0);
        String itto = it.toString();
        
        ObjectMapper mapper = new ObjectMapper();

		return "a";
	
	}
	
	

}
