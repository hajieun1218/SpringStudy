package com.sist.mgr;

import lombok.Getter;
import lombok.Setter;

/*
 * <ul class="list_line #list">

							<li>
				<a href="http://v.movie.daum.net/v/20200512115920097" class="thumb_line bg_noimage2 @1">
																				<span class="thumb_img" style="background-image:url(//img1.daumcdn.net/thumb/S320x200/?fname=https://t1.daumcdn.net/news/202005/12/10asia/20200512115920437knxr.jpg);"></span>
				</a>
				<span class="cont_line">
					<strong class="tit_line"><a href="http://v.movie.daum.net/v/20200512115920097" class="link_txt @1">'�ʹ��� ���ɻ�' ���μ���ġŸ, ī�޶� �Ȱ� �� 180�� �ٸ� '�ɹ�'</a></strong>
					<a href="http://v.movie.daum.net/v/20200512115920097" class="desc_line @1">
						��ȭ '�ʹ��� ���ɻ�'�� ���μ��� ġŸ(������)�� ��������� ��� ���̺�� �ü��� �����Ҵ�.        '�ʹ��� ���ɻ�'�� ���� ��� Ƥ ������ �ѱ� ���� �� �Ϸ� ������ �ذ� �� ����� �������� �߰���. ������ ����ŷ ��ƿ ��, ȯ�ϰ� ���� �ִ� ���μ��� ġŸ�� ��������� ������ ��� �ɹ̸� ǳ��� �־� ������ ����.�� �� ���� �⸧ ���� ���ݴ� ����
					</a>
					<span class="state_line">
						�پƽþ�<span class="txt_dot"></span><span class="screen_out">��������</span>20.05.12
					</span>
				</span>
			</li>
 */
@Getter
@Setter
public class NewsVO {
	private String title;
	private String poster;
	private String content;
	private String link;
	private String author;
}
