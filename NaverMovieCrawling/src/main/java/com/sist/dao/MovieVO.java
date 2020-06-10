package com.sist.dao;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class MovieVO {
	private int mno;
	private String title;
	private String poster;
	private String genre;
	private String grade;
	private String director;
	private String actor;
	private int like;
	private String story;	
	private int type; // 1:조회순, 2:평점순(현재상영영화), 3:평점순(모든영화)
}
