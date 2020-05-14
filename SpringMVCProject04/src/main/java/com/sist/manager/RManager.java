package com.sist.manager;

import java.sql.*;
import java.io.*;

import org.rosuda.REngine.Rserve.RConnection;
import org.springframework.stereotype.Component;

/*
 * 		Spring --> 수집(Hadoop) --> 분석(mapreduce,spark)-mongoDB --> 그래프(R) --> 출력 (React,Andriod)
 */
@Component
public class RManager {
	/*public static void main(String[] args) {
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			String url="jdbc:oracle:thin:@localhost:1521:XE";
			Connection conn=DriverManager.getConnection(url,"hr","happy");
			String sql="SELECT empno,ename,job,sal FROM emp";
			PreparedStatement ps=conn.prepareStatement(sql);
			ResultSet rs=ps.executeQuery();
			String temp="empno,ename,job,sal\n";
			while(rs.next()) {
				temp+=rs.getInt(1)+","+rs.getString(2)+","+rs.getString(3)+","+rs.getInt(4)+"\n";
			}
			rs.close();
			ps.close();
			conn.close();
			temp=temp.substring(0,temp.lastIndexOf("\n"));
			
			FileWriter fw=new FileWriter("c:\\upload\\emp.csv");
			fw.write(temp);
			fw.close();
			
			//---------------------------------------------------------
			
			RConnection rc=new RConnection();
			// R명령어를 보냄 -> Rserve에서 실행
			// seq : 구분자
			rc.voidEval("emp<-read.csv(\"c:/upload/emp.csv\",header=T,seq=\",\")");
			
		} catch(Exception ex) {
			ex.printStackTrace();
		}
	}*/
	
	
	// C:\springDev\springStudy\.metadata\.plugins\org.eclipse.wst.server.core\tmp0\wtpwebapps\SpringMVCProject04\board
	public void rGraph(int no) {
		try {
			// R 연결 -> Rserve가 동작하고 있어야 연결 가능
			RConnection rc=new RConnection();  // default:localhost
			
			// import
			rc.voidEval("library(rJava)");
			rc.voidEval("library(KoNLP)");
			
			// 그림그릴 준비 - 저장위치에 그림을 png로 그려라
			rc.voidEval("png(\"C:/springDev/springStudy/.metadata/.plugins/org.eclipse.wst.server.core/tmp0/wtpwebapps/SpringMVCProject04/board/"+no+".png\",width=700,height=450)");
			
			// voidEval : 명령어를 보냄 
			// 한줄씩 읽어서 실행
			rc.voidEval("data<-readLines(\"c:/data/board.txt\")");
			
			// 단어를 잘라서 모아두는 방식
			rc.voidEval("data2<-sapply(data,extractNoun,USE.NAMES = F)");
			// 1차원 배열로 바꿀때
			rc.voidEval("data3<-unlist(data2)");
			// 알파벳이랑 숫자 지우기
			// gsub : replace
			rc.voidEval("data3<-gsub(\"[A-Za-z]\",\"\",data3)");
			rc.voidEval("data3<-gsub(\"[0-9]\",\"\",data3)");
			// 두글자 이상만 가져오기
			rc.voidEval("data3<-Filter(function(x){nchar(x)>=2},data3)"); 
			// 단어의 횟수
			rc.voidEval("data4<-table(data3)");
			// 핵심단어 10개
			rc.voidEval("data5<-head(sort(data4,decreasing = T),10)");
			// 막대그래프
			rc.voidEval("barplot(data5,col=rainbow(15))");
			
			rc.voidEval("dev.off()");
			rc.close();
			
		} catch(Exception ex) {
			ex.printStackTrace();
		}
	}
}
