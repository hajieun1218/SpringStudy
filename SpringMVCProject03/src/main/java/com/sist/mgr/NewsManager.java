package com.sist.mgr;

import java.util.*;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;
import org.springframework.stereotype.Component;

import java.text.*;

// DB에 저장하지 않고 실시간으로 데이터 가져옴
@Component
public class NewsManager {
	
	public List<NewsVO> newsAllData() {
		List<NewsVO> list=new ArrayList<NewsVO>();
		Date date=new Date();
		SimpleDateFormat sdf=new SimpleDateFormat("yyyyMMdd");
		String today=sdf.format(date);
		
		try {
			//for(int i=1;i<=18;i++) 
			{
				// doc => https://movie.daum.net/magazine/new 페이지 소스가 저장
				Document doc=Jsoup.connect("https://movie.daum.net/magazine/new?tab=nws&regdate="+today+"&page=1").get();
				Elements title=doc.select("ul.list_line strong.tit_line a");
				Elements poster=doc.select("ul.list_line a.thumb_line span.thumb_img");
				Elements link=doc.select("ul.list_line strong.tit_line a");
				Elements temp=doc.select("span.cont_line span.state_line"); // author + regdate
				Elements content=doc.select("span.cont_line a.desc_line");
				
				for(int j=0;j<title.size();j++) {
					System.out.println(title.get(j).text());
					String ss=poster.get(j).attr("style");
					ss=ss.substring(ss.indexOf("(")+1,ss.lastIndexOf(")"));
					System.out.println(ss);
					System.out.println(link.get(j).attr("href"));
					String str=temp.get(j).text();
					System.out.println(content.get(j).text());
					System.out.println("====================================");
					
					
					NewsVO vo=new NewsVO();
					vo.setTitle(title.get(j).text());
					vo.setLink(link.get(j).attr("href"));
					vo.setContent(content.get(j).text());
					vo.setPoster(ss);
					vo.setAuthor(str);
					
					list.add(vo);
				}
			}
		} catch(Exception ex) {
			System.out.println(ex.getMessage());
		}
		return list;
	}
}
