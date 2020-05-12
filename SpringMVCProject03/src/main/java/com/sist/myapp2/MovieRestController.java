package com.sist.myapp2;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.sist.mgr.MovieManager;

@RestController
public class MovieRestController {
	@Autowired
	private MovieManager mm;
	
	@RequestMapping("main/def.do")
	public String main_def(int  no) {
		
		String url="";
		
		// http://www.kobis.or.kr/kobis/business/main/searchMainDailyBoxOffice.do
		switch(no) {
		case 1: //일일 박스오피스
			url="searchMainDailyBoxOffice.do";
			break;
		case 2: //실시간 예매율
			url="searchMainRealTicket.do";
			break;
		case 3: //좌석점유율순위
			url="searchMainDailySeatTicket.do";
			break;
		case 4: //온라인상영관 일일 
			url="searchMainOnlineDailyBoxOffice.do";
			break;
		}
		
		String json=mm.movieGetJson(url);
		
		return json;
	}
}
