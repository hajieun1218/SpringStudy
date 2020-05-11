package com.sist.naver;

//���̹� �˻� API ���� - blog �˻�
import java.io.*;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.Map;

public class MainClass {

	 public static void main(String[] args) throws Exception {
	     String clientId = "SVonKgLyERsAjGSU07BC"; //���ø����̼� Ŭ���̾�Ʈ ���̵�"
	     String clientSecret = "kMgaZrew6x"; //���ø����̼� Ŭ���̾�Ʈ ��ũ����"
	
	     String text = null;
	     try {
	         text = URLEncoder.encode("���� ������", "UTF-8");
	     } catch (UnsupportedEncodingException e) {
	         throw new RuntimeException("�˻��� ���ڵ� ����",e);
	     }
	
	     //String apiURL = "https://openapi.naver.com/v1/search/blog?query=" + text;    // json ���
	     String apiURL = "https://openapi.naver.com/v1/search/blog.xml?display=100&start=1&query="+ text; // xml ���
	
	     Map<String, String> requestHeaders = new HashMap<>();
	     requestHeaders.put("X-Naver-Client-Id", clientId);
	     requestHeaders.put("X-Naver-Client-Secret", clientSecret);
	     String responseBody = get(apiURL,requestHeaders);
	
	     System.out.println(responseBody);
	 }
	
	 private static String get(String apiUrl, Map<String, String> requestHeaders) throws Exception{
	     HttpURLConnection con = connect(apiUrl);
	     try {
	         con.setRequestMethod("GET");
	         for(Map.Entry<String, String> header :requestHeaders.entrySet()) {
	             con.setRequestProperty(header.getKey(), header.getValue());
	         }
	
	         int responseCode = con.getResponseCode();
	         if (responseCode == HttpURLConnection.HTTP_OK) { // ���� ȣ��
	             return readBody(con.getInputStream());
	         } else { // ���� �߻�
	             return readBody(con.getErrorStream());
	         }
	     } catch (IOException e) {
	         throw new RuntimeException("API ��û�� ���� ����", e);
	     } finally {
	         con.disconnect();
	     }
	 }
	
	 private static HttpURLConnection connect(String apiUrl) throws Exception{
	     try {
	         URL url = new URL(apiUrl);
	         return (HttpURLConnection)url.openConnection();
	     } catch (MalformedURLException e) {
	         throw new RuntimeException("API URL�� �߸��Ǿ����ϴ�. : " + apiUrl, e);
	     } catch (IOException e) {
	         throw new RuntimeException("������ �����߽��ϴ�. : " + apiUrl, e);
	     }
	 }
	
	 private static String readBody(InputStream body) throws Exception {
	     InputStreamReader streamReader = new InputStreamReader(body, "UTF-8");
	
	     try (BufferedReader lineReader = new BufferedReader(streamReader)) {
	         StringBuilder responseBody = new StringBuilder();
	
	         String line;
	         while ((line = lineReader.readLine()) != null) {
	             responseBody.append(line);
	         }
	
	         return responseBody.toString();
	     } catch (IOException e) {
	         throw new RuntimeException("API ������ �дµ� �����߽��ϴ�.", e);
	     }
	 }
}