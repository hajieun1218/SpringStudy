package com.sist.xml;
/*
<item>
	<title>'�ѻ�: ���� ����', ������-������-�ȼ��� �� ĳ���� Ȯ��... '��' ���� �մ´�</title>
	<link>
	http://www.stardailynews.co.kr/news/articleView.html?idxno=271563
	</link>
	<description>
	<![CDATA[
	 ��ȭ '��'�� ���ѹ� ������ �����ϴ� �̼��� 3���� �� �� ��° ������Ʈ�� '�ѻ�: ���� ����'�� �Ϻ���... ��ȭ '�ѻ�: ���� ����'�� �� ��ø 5�� ��, ������ ���� ������ ����ϱ� ���� �̼��� �屺�� ������������ ������...
	]]>
	</description>
	<pubDate>Fri, 15 May 2020 12:18:00 +0900</pubDate>
	<author>��Ÿ���ϸ�����</author>
	<category>���Ǿ���</category>
	<media:thumbnail url="https://imgnews.pstatic.net/image/thumb140/5401/2020/05/15/205217.jpg"/>
</item>
 */

// �±׸�� ������ ��ġ�ϰ�
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
