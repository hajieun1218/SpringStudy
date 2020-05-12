package com.sist.mgr;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.springframework.stereotype.Component;
import java.util.*;
import java.io.*;
import java.net.*;

@Component
public class MovieManager {
	
	public static void main(String[] args) {
		MovieManager m=new MovieManager();
		m.movieGetJson("searchMainDailyBoxOffice.do");
	}
	
	// json파일 전체 읽기
	public String movieGetJson(String url) {
		String json="";
		try {
			// json 정석으로 읽어오는 법 (데이터만 읽어옴)
			URL strUrl=new URL("http://www.kobis.or.kr/kobis/business/main/"+url);
			HttpURLConnection conn=(HttpURLConnection)strUrl.openConnection();
			StringBuffer sb=new StringBuffer();
			
			if(conn!=null) {
				BufferedReader br=new BufferedReader(new InputStreamReader(conn.getInputStream(),"UTF-8"));
				while(true) {
					String temp=br.readLine();
					if(temp==null)
						break;
					sb.append(temp);
				}
			}
			json=sb.toString();
			
			// 간단하게 짜는법 (<html>,<head>,<body>태그까지 읽어온다)
//			Document doc=Jsoup.connect("http://www.kobis.or.kr/kobis/business/main/"+url).get();
//			json=doc.toString();
			
			System.out.println(json);
		} catch(Exception ex) {}
		return json;
	}
}
