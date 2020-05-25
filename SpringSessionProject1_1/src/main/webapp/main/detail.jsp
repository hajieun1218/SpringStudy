<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/css/bootstrap.min.css">
<style type="text/css">
.row {
   margin: 0px auto;
   width:900px;
}
h1 {
  text-align: center;
}
</style>
<!-- React -->
<script src="https://cdnjs.cloudflare.com/ajax/libs/react/0.14.0/react.js"></script> 
<script src="https://cdnjs.cloudflare.com/ajax/libs/react/0.14.0/react-dom.js"></script> 
<script src="https://cdnjs.cloudflare.com/ajax/libs/babel-core/5.8.23/browser.min.js"></script> 
<script src="https://cdnjs.cloudflare.com/ajax/libs/jquery/2.1.1/jquery.min.js"></script> 
<script src="https://unpkg.com/axios/dist/axios.min.js"></script>
<script type="text/javascript">
$(function(){
	$('.spans').click(function(){
		$('.replys').hide();
		$('.replys_update').hide();
		var no=$(this).attr("data-no");
		$('#m'+no).show();
	})
	$('.span_update').click(function(){
		$('.replys').hide();
		$('.replys_update').hide();
		var no=$(this).attr("data-no");
		$('#u'+no).show();
	})
});
</script>
</head>
<body>
	<div class="container" id="root"></div>
	<script type="text/babel">
		// ====================== [Detail]: 아래의 3개 클래스를 조립★★★  ======================
		// - Detail: list.jsp에서의 App과 같은 역할. 
		class Detail extends React.Component{
			constructor(props)
			{
				super(props);
				this.state={
					mno:${mno},
					// detail:{}  // <== [방법1] JSP의 componenetDidMount()가 detail_data.do?mno=..에서 얻어온 데이터가 여기에 저장됨
					detail:${json}  // <== [방법2] MainController에서 model에 싣은 값을 JSP에서 EL 사용해서 받아옴 
				}
			}

			// [방법1] - componentDidMount 이용O (주석해제)
			// [방법2] - componentDidMount 이용X (주석처리)
			/* componentDidMount()
			{
				// 서버 연결
				axios.get("http://localhost/web/main/detail_data.do",{
					params:{
						mno:this.state.mno
					}
				}).then((result)=>{
					// 저장 
					// render를 호출해야 (다시 render해야) ==> setState해서 다시 render하면 됨  
					this.setState({detail:result.data}); 
					console.log(this.state.detail); 
				});
				// http://localhost/web/main/detail_data.do?mno=1 : JSON 출력되고 있음 
			}
			*/

			// render함수 안: JSX (JavaScript+XML, ES6 이상에서 사용)
			render(){
				return (
					<div className="row">
						<h1 className="text-center">음악 상세보기(${sessionScope.id})</h1>
						<table className="table">
							<tr>
								<td colSpan="2" className="text-center">
									<iframe src={"http://youtube.com/embed/"+this.state.detail.key} width="900" height="450"></iframe>
								</td>
							</tr>
							<tr>
								<td><b>노래명</b></td>
								<td>{this.state.detail.title}</td>
							</tr>
							<tr>
								<td><b>앨범</b></td>
								<td>{this.state.detail.album}</td>
							</tr>
							<tr>
								<td><b>가수명</b></td>
								<td>{this.state.detail.singer}</td>
							</tr>
						</table> 
						<table className="table">
							<tr>
								<td className="text-right">
									<c:if test="${sessionScope.id!=null}">
										<a href="logout.do" className="btn btn-sm btn-danger">로그아웃</a>
									</c:if>
									<a href="list.do" className="btn btn-sm btn-primary">목록</a>
								</td>
							</tr>
						</table>
					</div>
				)
			}
		}


		// ====================== [ReactDOM render] : Detail을 render ======================
		// - div #root에다가 detail 리턴값 포함시킨다.
		ReactDOM.render(<Detail/>, document.getElementById('root'));
	</script>
	<c:if test="${sessionScope.id!=null }">
		<div class="container">
			<div class="row">
				<h2 class="text-center">댓글</h2>
				<table class="table">
					<c:forEach var="rvo" items="${rList }">
						<tr>
							<td class="text-left">
								<c:if test="${rvo.group_tab>0 }">
									<c:forEach var="i" begin="1" end="${rvo.group_tab }">
										&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
									</c:forEach>
									<img src="icon_reply.gif">
								</c:if>
								<b style="color: blue;"">◐${rvo.id }</b>&nbsp;(${rvo.dbday })
							</td>
							<td class="text-right">
								<c:if test="${sessionScope.id==rvo.id }">
									<span class="btn btn-xs btn-danger span_update" data-no="${rvo.no }">수정</span>
									<a href="reply_delete.do?no=${rvo.no }&mno=${mno}" class="btn btn-xs btn-info">삭제</a>
								</c:if>
								<span class="btn btn-xs btn-success spans" data-no="${rvo.no }">댓글</span>
							</td>
						</tr>
						<tr>
							<td colspan="2">
								<c:if test="${rvo.group_tab>0 }">
									<c:forEach var="i" begin="1" end="${rvo.group_tab }">
										&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
									</c:forEach>
								</c:if>
								${rvo.msg }
							</td>
						</tr>
						<tr id="m${rvo.no }" style="display: none;" class="replys">
							<td colspan="2">
								<form method="post" action="reply_reply_insert.do">
									<textarea rows="3" cols="100" name="msg" style="float: left;"></textarea>
									<input type="hidden" name="mno" value="${mno }">
									<input type="hidden" name="pno" value="${rvo.no }">
									<button class="btn btn-sm btn-success" style="float: left; height: 65px;">댓글쓰기</button>
								</form>
							</td>
						</tr>
						<tr id="u${rvo.no }" style="display: none;" class="replys_update">
							<td colspan="2">
								<form method="post" action="reply_update.do">
									<textarea rows="3" cols="100" name="msg" style="float: left;">${rvo.msg }</textarea>
									<input type="hidden" name="mno" value="${mno }">
									<input type="hidden" name="no" value="${rvo.no }">
									<button class="btn btn-sm btn-success" style="float: left; height: 65px;">수정</button>
								</form>
							</td>
						</tr>
					</c:forEach>
				</table>
				<table class="table">
					<tr>
						<td>
							<form method="post" action="reply_insert.do">
								<textarea rows="3" cols="110" name="msg" style="float: left;"></textarea>
								<input type="hidden" name="mno" value="${mno }">
								<button class="btn btn-sm btn-success" style="float: left; height: 65px;">댓글쓰기</button>
							</form>
						</td>
					</tr>
				</table>
			</div>
		</div>
	</c:if>

</body>
</html>






