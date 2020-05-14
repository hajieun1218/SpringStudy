<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/css/bootstrap.min.css">
<style type="text/css">
.row {
	margin: 0px auto;
	width: 600px;
}
h1 {
	text-align: center;
}
</style>
<script type="text/javascript" src="http://code.jquery.com/jquery.js"></script>
<script type="text/javascript">
$(function(){
	$('#updateBtn').click(function(){
		var name=$('#name').val();
		var subject=$('#subject').val();
		var content=$('#content').val();
		var pwd=$('#pwd').val();
		var no=$('#no').val();
		
		$.ajax({
			type:'POST',
			url:'update_ok.do',
			data:{"name":name,"subject":subject,"content":content,"pwd":pwd,"no":no},
			success:function(res) {
				if(res=="NOPWD") {
					alert("비밀번호가 틀립니다!!");
					$('#pwd').val("");
					$('#pwd').focus();
				}
				else {
					location.href="detail.do?no="+no;
				}
			}
		})
	})
});
</script>
</head>
<body>
	<div class="container">
		<h1>수정하기</h1>
		<div class="row">
			<!-- <form method="post" action="update_ok.do"> -->
			<table class="table table-hover">
				<tr>
					<th width="20%" class="text-right success">이름</th>
					<td width="80%">
						<input type="text" name="name" id="name" size="15" value=${vo.name } required/>
						<input type="hidden" name="no" id="no" value="${vo.no }">
					</td>
				</tr>
				
				<tr>
					<th width="20%" class="text-right success">제목</th>
					<td width="80%">
						<input type="text" name="subject" id="subject" size="50" value="${vo.subject }" required/>
					</td>
				</tr>
				
				<tr>
					<th width="20%" class="text-right success">내용</th>
					<td width="80%">
						<textarea rows="8" cols="55" name="content" id="content" required>${vo.content }</textarea>
					</td>
				</tr>
				
				<tr>
					<th width="20%" class="text-right success">비밀번호</th>
					<td width="80%">
						<input type="password" name="pwd" id="pwd" size="10" required/>
					</td>
				</tr>
				
				<tr>
					<td class="text-center" colspan="2">
						<input type="button" value="수정" class="btn btn-sm btn-primary" id="updateBtn"/>
						<input type="button" value="취소" class="btn btn-sm btn-danger"
							onclick="javascript:history.back()"/>
					</td>
				</tr>
			</table>
			<!-- </form> -->
		</div>
	</div>
</body>
</html>