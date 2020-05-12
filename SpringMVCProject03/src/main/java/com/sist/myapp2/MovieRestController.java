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
		case 1: //���� �ڽ����ǽ�
			url="searchMainDailyBoxOffice.do";
			break;
		case 2: //�ǽð� ������
			url="searchMainRealTicket.do";
			break;
		case 3: //�¼�����������
			url="searchMainDailySeatTicket.do";
			break;
		case 4: //�¶��λ󿵰� ���� 
			url="searchMainOnlineDailyBoxOffice.do";
			break;
		}
		
		String json=mm.movieGetJson(url);
		
		return json;
	}
}
