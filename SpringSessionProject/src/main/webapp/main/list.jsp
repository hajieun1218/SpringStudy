<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
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
/*
	<App name="홍길동"> => new App("홍길동"); ==> 변경할 수 없다
	props : name="홍길동" ==> 변경할 수 없다
	state : 변경이 가능

	=> <App/> App호출 
		1) constructor() : 초기화(변수 설정)
		2) render() : 데이터를 받아서 화면 출력 (HTML)
		3) componentDidMount() : 완료가 된 상태 => 브라우저에 출력
				=> 완료가 된 다음 => 화면을 변경(리렌더링 - setState()) => render()를 다시 호출해서 다시 그림을 그린다

	Mount : 가상돔 (가상메모리)에 올려주는것
		-> 변경,추가된 부분만 가상메모리에서 비교해서 실제 메모리에 출력 
		-> 깜빡거림이 없음, 속도가 빠름
	

	함수
		function H() {}
		var H=function() {}
		var H=()=>{} 
*/

// render()함수를 계속 호출 -> 메모리가 누적
const H=()=>{
	const color=['red','green','blue','yellow','pink'];
	let rand=parseInt(Math.random()*5);
	let s={"color":color[rand]}
	return(
		<h1 className="text-center" style={s}>뮤직 Top200</h1>
	)
}

// 한번만 호출하고 그 다음부터는 처음 호출한 메모리를 가져옴 (최적화) -> 버전이 낮아서 사용 못함
/*const H2=React.memo(()=>{
	const color=['red','green','blue','yellow','pink'];
	let rand=parseInt(Math.random()*5);
	let s={"color":color[rand]}
	return(
		<h1 className="text-center" style={s}>뮤직 Top200</h1>
	)
});*/

// App : MusicTable,MusicRow,SearchBar 를 조립 --> main
class App extends React.Component{
	// 생성자
	constructor(props){
		super(props); // 속성값 받기
		// 서버에서 들어오는 데이터를 받아서 저장
		this.state={
			music:[],
			ss:''
		}
		// react에서 이벤트 등록
		this.handlerUserInput=this.handlerUserInput.bind(this);
	}
	handlerUserInput(ss)
	{
		this.setState({ss:ss});
	}

	// componentDidMount()가 호출되는 순간 화면이 뜬다 (브라우저 출력)
	// componentDidMount()에서 데이터를 가져와서 this.setState({})를 하면 render를 다시 호출해서 html을 다시 그려줌
	componentDidMount() {
		// 서버 연결
		// http://localhost/web/main/music.do을 실행한 값을 result에서 받는다 => 콜백함수
		axios.get('http://localhost/web/main/music.do').then((result)=>{
			console.log(result.data);
			//this.state.music=result.data; // 이렇게 하면 값 저장만 되고 render() 호출은 안됨
			this.setState({music:result.data}) // state
		})
	}
	
	// 화면 출력 (HTML)
	render() {
		return (
			<div className="row">
				<H/>
				<SearchBar ss={this.state.ss} onUserInput={this.handlerUserInput}/> {/* 데이터와 이벤트가 속성으로 넘어가야한다 */}
				<div style={{"height":"30px"}}></div>
				<MusicTable music2={this.state.music} ss={this.state.ss}/> {/* MusicTable을 호출, props(속성) */}
			</div>
		)
	}
}
class MusicTable extends React.Component{
	render() {
		let rows=[];
		// for(MusicVO vo:list)
		// [{title: ,mno: ,..},{},{},..]
		this.props.music2.forEach((m,key)=>{
			if(m.title.indexOf(this.props.ss)===-1) {
				return;
			}
			rows.push(<MusicRow music={m}/>)
		})
		return (
			<table className="table">
				<thead>
					<tr className="danger">
						<th className="text-center">순위</th>
						<th className="text-center">등폭</th>
						<th className="text-center"></th>
						<th className="text-center">노래명</th>
						<th className="text-center">가수명</th>
					</tr>
				</thead>
				<tbody>
					{rows}
				</tbody>
			</table>
		)
	}
}
class MusicRow extends React.Component{
	render() {
		return (
			<tr>
				<td className="text-center">{this.props.music.mno}</td>
				<td className="text-center">
					{/* 다중조건문 */}					
					{
						this.props.music.state==="상승" && 
						<span style={{"color":"red"}}>▲{this.props.music.idcrement}</span>
					}
					{
						this.props.music.state==="하강" && 
						<span style={{"color":"blue"}}>▼{this.props.music.idcrement}</span>
					}
					{
						this.props.music.state==="유지" && 
						<span style={{"color":"gray"}}>-</span>
					}
				</td>
				<td className="text-center">
					<img src={this.props.music.poster} width="30" height="30"/>
				</td>
				<td><a href={"detail.do"}>{this.props.music.title}</a></td>
				<td>{this.props.music.singer}</td>
			</tr>
		)
	}
}
class SearchBar extends React.Component{
	constructor(props) {
		super(props);
		this.onChange=this.onChange.bind(this);
	}
	onChange(e) {
		this.props.onUserInput(e.target.value)
	}
	render(){
		return (
			<input type="text" size="30" className="input-sm" placeholder="검색"
				value={this.props.ss} onChange={this.onChange}
			/>
		)
	}
}
ReactDOM.render(<App/>,document.getElementById('root'));
// <App/> : 자동으로 render의 return값을 가져온다
// String data=new App().render(); ==> $('#root').html(data)
</script>

</body>
</html>