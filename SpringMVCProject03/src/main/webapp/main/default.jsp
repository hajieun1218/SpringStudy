<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<!-- react  -->
<script src="https://cdnjs.cloudflare.com/ajax/libs/react/0.14.0/react.js"></script> 
<script src="https://cdnjs.cloudflare.com/ajax/libs/react/0.14.0/react-dom.js"></script> 
<script src="https://cdnjs.cloudflare.com/ajax/libs/babel-core/5.8.23/browser.min.js"></script> 
<script src="https://cdnjs.cloudflare.com/ajax/libs/jquery/2.1.1/jquery.min.js"></script> 
<!-- 서버와 연결하기 위해 필요한 라이브러리 -->
<script src="https://unpkg.com/axios/dist/axios.min.js"></script>
</head>
<body>
	<div id="root"></div>
	<!-- 변수를 생성할 때는 생성자에 생성해야한다 , 전역변수로 하면 안되고 꼭  생성자에서!
		서버와 연결해서 데이터를  componentWillMount()에 받아놓고, render() 에서 html 출력
		서버와 연결하기 위해 필요한 라이브러리 : axios
		Mount : 가상메모리에 html을 올려주는거
		출력할때 : {} 괄호 열고 닫고
	 -->
	<script type="text/babel">
		class MainMovieList extends React.Component {
			/* 생성자 - 변수를 생성할 때는 생성자에 생성해야한다 , 전역변수로 하면 안되고 꼭  생성자에서! */
			constructor(props)
			{
				super(props);
				this.state={
					movie:[],
					no:1
				}
			}
			/* 서버와 연결해서 데이터를  componentWillMount()에 받아놓고, render() 에서 html 출력 */
			componentWillMount()
			{
				axios.get("http://localhost/myapp2/main/def.do",{
						params:{
							no:this.state.no
						}
					}).then((res)=>{
					this.setState({movie:res.data})
					console.log(res.data);
				});
			}
			/*
artmovieYn: "Y"
audiCnt: 4118
director: "우디 앨런"
dtNm: "그린나래미디어(주),(주)버킷스튜디오"
endDate: "2020년 05월 11일(월)"
endYearDate: "2020.05.11"
genre: "코미디,멜로/로맨스"
indieYn: null
movieCd: "20207341"
movieNm: "레이니 데이 인 뉴욕"
movieNmEn: " A Rainy Day in New York"
moviePrdtStat: "개봉"
movieType: "장편"
multmovieYn: null
openDt: "20200506"
prNm: null
prdtYear: "2019"
rank: 1
rankInten: 0
rankOldAndNew: "OLD"
repNationCd: "미국"
rownum: 1
salesAmt: 34212660
scrCnt: 498
showCnt: 1041
showDt: "20200511"
showTm: "92"
showTs: "20"
startDate: "2020년 05월 11일(월)"
startYearDate: "2020.05.11"
synop: "상상해 봐요
↵막 떨어지기 시작한 빗방울
↵센트럴 파크 델라코트 시계 아래
↵누군가 당신을 기다리고 있다면…
↵
↵재즈를 사랑하는 ‘개츠비’(티모시 샬라메)
↵영화에 푹 빠진 ‘애슐리’(엘르 패닝)
↵낭만을 꿈꾸는 ‘챈’(셀레나 고메즈)
↵매력적인 세 남녀가 선사하는 낭만적인 하루!
↵
↵운명 같은 만남을 기대하며
↵봄비 내리는 뉴욕에서
↵로맨틱한 하루를 함께 하실래요? 
↵
↵"
thumbUrl: "/common/mast/movie/2020/04/thumb/thn_8c9f1268dc6448159b654c83702d9c97.jpg"
watchGradeNm: "15세이상관람가"

			*/
			render() 
			{	
				const html=this.state.movie.map((m)=>
					<tr>
						<td className="text-center">
							<img src={"http://www.kobis.or.kr"+m.thumbUrl} width="35" height="35"/>
						</td>
						<td className="text-center">{m.movieNm}</td>
						<td className="text-center">{m.genre}</td>
						<td className="text-center">{m.watchGradeNm}</td>
					</tr>
				)				

				return (
					<div className="row">
						<div className="col-sm-6">
							
						</div>
						<div className="col-sm-6">
							<table className="table table-hover">
								<thead>
									<tr>
										<th className="text-center"></th>
										<th className="text-center">영화명</th>
										<th className="text-center">장르</th>
										<th className="text-center">등급</th>
									</tr>
								</thead>
								<tbody>
									{html}
								</tbody>
							</table>
						</div>
					</div>
				);
			}
		}
		ReactDOM.render(<MainMovieList/>,document.getElementById('root'))
	</script>
</body>
</html>