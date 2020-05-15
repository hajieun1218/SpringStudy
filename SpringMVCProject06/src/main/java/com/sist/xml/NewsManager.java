package com.sist.xml;
import java.util.*;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.Unmarshaller;

import org.springframework.stereotype.Component;

import java.net.*; // 인코딩 하기 위해

// where=rss : xml
// newssearch.naver.com/search.naver?where=rss&query=영화
@Component
public class NewsManager {
	public List<Item> newsAllData(String fd) {
		List<Item> list=new ArrayList<Item>();
		try {
			// XML의 최상위 태그를 넘겨줌
			JAXBContext jc=JAXBContext.newInstance(Rss.class);
			//  xml -> java : unmarchal
			Unmarshaller un=jc.createUnmarshaller();
			URL url=new URL("http://newssearch.naver.com/search.naver?where=rss&query="+URLEncoder.encode(fd,"UTF-8"));
			Rss rss=(Rss)un.unmarshal(url);
			
			list=rss.getChannel().getItem();
		} catch(Exception ex) {}
		return list;
	}
}
