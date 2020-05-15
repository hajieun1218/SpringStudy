package com.sist.news;

import org.rosuda.REngine.Rserve.RConnection;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.*;
import com.sist.xml.*;
import java.io.*;

@Controller
public class NewsController {
	@Autowired
	private NewsManager nm;
	// setting이 DI로 되어있으면 @Autowired
	// new 사용해도 됨 -> 소멸까지 담당해주기 때문에 @Autowired 사용
	
	@RequestMapping("main/news.do") 
	public String main_news(Model model,String fd) {
		if(fd==null) 
			fd="코로나";
		List<Item> list=nm.newsAllData(fd);
		model.addAttribute("list",list);
		
		// 파일 저장
		try {
			String temp="";
			for(Item i:list) {
				// 메모장은 \r\n 으로 줘야함
				temp+=i.getDescription()+"\r\n";
			}
			FileWriter fw=new FileWriter("c:\\data\\naver.txt");
			fw.write(temp);
			fw.close();
			
			rGraph();
		} catch(Exception ex) {}
		
		return "main/news";
	}
	
	// wordcloud
	// C:\springDev\springStudy\.metadata\.plugins\org.eclipse.wst.server.core\tmp0\wtpwebapps\SpringMVCProject06\main
	public void rGraph() {
		try {
			RConnection rc=new RConnection();
			rc.voidEval("library(rJava)");
			rc.voidEval("library(KoNLP)");
			rc.voidEval("png(\"C:/springDev/springStudy/.metadata/.plugins/org.eclipse.wst.server.core/tmp0/wtpwebapps/SpringMVCProject06/main/news.png\")");
			rc.voidEval("data<-readLines(\"c:/data/naver.txt\")");
			rc.voidEval("data2<-sapply(data,extractNoun,USE.NAMES = F)");
			rc.voidEval("data3<-unlist(data2)");
			rc.voidEval("data3<-gsub(\"[A-Za-z]\",\"\",data3)");
			rc.voidEval("data3<-gsub(\"[0-9]\",\"\",data3)");
			rc.voidEval("data4<-table(data3)");
			rc.voidEval("data5<-head(sort(data4,decreasing = T),100)");
			rc.voidEval("library(wordcloud)");
			rc.voidEval("wordcloud(names(data5),freq = data5,min.freq = 2,scale = c(5,1),rot.per = 0.25,random.order = F,colors = rainbow(15))");
			rc.voidEval("dev.off()");
			rc.close();
			
		} catch(Exception ex) {}
	}
}
