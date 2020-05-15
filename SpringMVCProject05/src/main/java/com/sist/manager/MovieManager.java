package com.sist.manager;
import java.util.*;

import javax.naming.directory.DirContext;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import com.sist.dao.MovieDAO;
import com.sist.dao.MovieVO;

public class MovieManager {
	/*
	 * <td class="title">
			<div class="tit5">
				<a href="/movie/bi/mi/basic.nhn?code=171539" title="그린 북">그린 북</a>
			</div>
		</td>
	 */
	public List<String> movieLinkData() {
		List<String> list=new ArrayList<String>();
		
		try {
			for(int i=1;i<=40;i++) {
				Document doc=Jsoup.connect("https://movie.naver.com/movie/sdb/rank/rmovie.nhn?sel=pnt&date=20200513&page="+i).get();
				Elements link=doc.select("td.title div.tit5 a");
				for(int j=0;j<link.size();j++) {
					String strLink=link.get(j).attr("href");
					list.add("https://movie.naver.com"+strLink);
				}
			}
		} catch(Exception ex) {}
		
		return list;
	}
	
	/*
	 *  private int mno;
		private String title;
		private String poster;
		private String genre;
		private String grade;
		private String director;
		private String actor;
		private int like;
		private String story;
	 */
	public void movieAllData() {
		try {
			MovieDAO dao=new MovieDAO();
			List<String> list=movieLinkData();
			// https://movie.naver.com/movie/bi/mi/basic.nhn?code=171539
			int k=1;
			for(String url:list) {
				// 안에서 try~catch절을 쓰지 않으면 중간에 에러났을때 종료함
				// for문 안에서 try~catch절을 사용하면 에러난거 넘어가고 다시 for문 돌아감
				try {
					Document doc=Jsoup.connect(url).get();
					
					Element title=doc.selectFirst("div.mv_info h3.h_movie a");
					Element poster=doc.selectFirst("div.poster a img");
					Element genre=doc.selectFirst("dl.info_spec span a");
					Element grade=doc.select("dl.info_spec dd").get(3);
					Element director=doc.selectFirst("div.info_spec2 dl.step1 a");
					Element actor=doc.selectFirst("div.info_spec2 dl.step2 a");
					Element story=doc.selectFirst("div.video div.story_area p.con_tx");
					
					MovieVO vo=new MovieVO();
					vo.setMno(Integer.parseInt(url.substring(url.lastIndexOf("=")+1)));
					
					System.out.println("=================== 영화정보 ===================");
					System.out.println("k="+k);
					System.out.println("제목: "+title.text());
					System.out.println("포스터: "+poster.attr("src"));
					System.out.println("장르: "+genre.text());
					System.out.println("등급: "+grade.text());
					System.out.println("감독: "+director.text());
					System.out.println("출연: "+actor.text());
					System.out.println("스토리: "+story.text());
					
					vo.setTitle(title.text());
					vo.setPoster(poster.attr("src"));
					vo.setGenre(genre.text());
					vo.setGrade(grade.text());
					vo.setDirector(director.text());
					vo.setActor(actor.text());
					vo.setStory(story.text());
					dao.movieInsert(vo);
					k++;
				} catch(Exception ex) {}
			}
		} catch(Exception ex) {}
	}
	
	public static void main(String[] args) {
		MovieManager m=new MovieManager();
		m.movieAllData();
	}
}
