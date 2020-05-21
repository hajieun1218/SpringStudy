<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/css/bootstrap.min.css">
<!-- react 라이브러리 -->
<script src="https://cdnjs.cloudflare.com/ajax/libs/react/0.14.0/react.js"></script> 
<script src="https://cdnjs.cloudflare.com/ajax/libs/react/0.14.0/react-dom.js"></script> 
<script src="https://cdnjs.cloudflare.com/ajax/libs/babel-core/5.8.23/browser.min.js"></script> 
<script src="https://cdnjs.cloudflare.com/ajax/libs/jquery/2.1.1/jquery.min.js"></script> 
<!-- 서버와 연결하기 위해 필요한 라이브러리 -->
<script src="https://unpkg.com/axios/dist/axios.min.js"></script>
</head>
<body>

<div class="container" id="root"></div>
<script type="text/babel">
class Detail extends React.Component{
	constructor(props) {
		super(props);
		this.state={    // state변수에 필요한 변수를 저장해야한다 => 일반변수는 내용이 바뀌었을때 setState()로 화면을 바꿀 수 없다
			mno:${mno}, // MainController에서 보낸 mno
			detail:${json} 
		};
	}
	
	/* detail ==>  detail_data.do에서 받지 않고 MainController에서 json을 받아서 출력도 가능함 
		=> javascript파일로 따로 뺀다면 ${}로 받을 수 없음 => componentDidMount()에서 받아야함
	*/
	/*componentDidMount() {
		// 내용이나 페이지가 바뀌면 axios를 이용해서 데이터를 다시 바꿔서 다시 출력해야한다
		axios.get("http://localhost/web/main/detail_data.do",{
			params:{ 
				mno:this.state.mno
			}
		}).then((result)=>{
			this.setState({detail:result.data});
			console.log(this.state.detail);
		});
	}*/

	// JSX => Javascript+XML (ES6~)
	render(){
		return (
			<div className="row">
				<h1 className="text-center">뮤직상세보기 (${sessionScope.id}) </h1>
				<table className="table">
					<tr>
						<td colSpan="2" className="text-center">
							<iframe src={"http://youtube.com/embed/"+this.state.detail.key}
								width="900" height="450"></iframe>
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
							<c:if text="${sessionScope.id!=null}">
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
ReactDOM.render(<Detail/>,document.getElementById('root'))
</script>
<c:if test="${sessionScope.id!=null }">
	<div class="container">
		<div class="row">
			<h2 class="text-center">댓글</h2>
		</div>
	</div>
</c:if>
</body>
</html>