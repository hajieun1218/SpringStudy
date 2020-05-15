package com.sist.xml;
/*
<item>
	<title>'한산: 용의 출현', 박해일-변요한-안성기 등 캐스팅 확정... '명량' 흥행 잇는다</title>
	<link>
	http://www.stardailynews.co.kr/news/articleView.html?idxno=271563
	</link>
	<description>
	<![CDATA[
	 영화 '명량'의 김한민 감독이 연출하는 이순신 3부작 중 두 번째 프로젝트인 '한산: 용의 출현'이 완벽한... 영화 '한산: 용의 출현'은 명량 대첩 5년 전, 수세에 몰린 조선을 방어하기 위한 이순신 장군과 조선수군들의 전략과...
	]]>
	</description>
	<pubDate>Fri, 15 May 2020 12:18:00 +0900</pubDate>
	<author>스타데일리뉴스</author>
	<category>섹션없음</category>
	<media:thumbnail url="https://imgnews.pstatic.net/image/thumb140/5401/2020/05/15/205217.jpg"/>
</item>
 */

// 태그명과 변수명 일치하게
public class Item {
	private String title;
	private String link;
	private String description;
	private String author;
	
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getLink() {
		return link;
	}
	public void setLink(String link) {
		this.link = link;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getAuthor() {
		return author;
	}
	public void setAuthor(String author) {
		this.author = author;
	}
}
