package com.sist.manager;

import java.sql.*;
import java.io.*;

import org.rosuda.REngine.Rserve.RConnection;
import org.springframework.stereotype.Component;

/*
 * 		Spring --> ����(Hadoop) --> �м�(mapreduce,spark)-mongoDB --> �׷���(R) --> ��� (React,Andriod)
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
			// R��ɾ ���� -> Rserve���� ����
			// seq : ������
			rc.voidEval("emp<-read.csv(\"c:/upload/emp.csv\",header=T,seq=\",\")");
			
		} catch(Exception ex) {
			ex.printStackTrace();
		}
	}*/
	
	
	// C:\springDev\springStudy\.metadata\.plugins\org.eclipse.wst.server.core\tmp0\wtpwebapps\SpringMVCProject04\board
	public void rGraph(int no) {
		try {
			// R ���� -> Rserve�� �����ϰ� �־�� ���� ����
			RConnection rc=new RConnection();  // default:localhost
			
			// import
			rc.voidEval("library(rJava)");
			rc.voidEval("library(KoNLP)");
			
			// �׸��׸� �غ� - ������ġ�� �׸��� png�� �׷���
			rc.voidEval("png(\"C:/springDev/springStudy/.metadata/.plugins/org.eclipse.wst.server.core/tmp0/wtpwebapps/SpringMVCProject04/board/"+no+".png\",width=700,height=450)");
			
			// voidEval : ��ɾ ���� 
			// ���پ� �о ����
			rc.voidEval("data<-readLines(\"c:/data/board.txt\")");
			
			// �ܾ �߶� ��Ƶδ� ���
			rc.voidEval("data2<-sapply(data,extractNoun,USE.NAMES = F)");
			// 1���� �迭�� �ٲܶ�
			rc.voidEval("data3<-unlist(data2)");
			// ���ĺ��̶� ���� �����
			// gsub : replace
			rc.voidEval("data3<-gsub(\"[A-Za-z]\",\"\",data3)");
			rc.voidEval("data3<-gsub(\"[0-9]\",\"\",data3)");
			// �α��� �̻� ��������
			rc.voidEval("data3<-Filter(function(x){nchar(x)>=2},data3)"); 
			// �ܾ��� Ƚ��
			rc.voidEval("data4<-table(data3)");
			// �ٽɴܾ� 10��
			rc.voidEval("data5<-head(sort(data4,decreasing = T),10)");
			// ����׷���
			rc.voidEval("barplot(data5,col=rainbow(15))");
			
			rc.voidEval("dev.off()");
			rc.close();
			
		} catch(Exception ex) {
			ex.printStackTrace();
		}
	}
}
