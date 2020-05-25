package com.sist.web;

import java.io.IOException;

import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;
import java.util.*;
/*
 * 		LongWritable => long
 * 		Text => String
 * 		IntWritable => int
 * 
 * 		Java Java Oracle Oracle Ajax  => (Java,1)  => Sort => [Java,1,1] [Oracle,1,1] [Ajax,1]
   										 (Java,1)
   										 (Oracle,1)
   										 (Oracle,1)
   										 (Ajax,1)
 */
// 단어를 분리
// LongWritable(줄 번호), Text(한줄 문자열)
public class WordMapper extends Mapper<LongWritable, Text, Text, IntWritable>{
	private final IntWritable one=new IntWritable(1);
	private Text keyValue=new Text();
	
	// 한줄씩 처리
	@Override
	protected void map(LongWritable key, Text value, Mapper<LongWritable, Text, Text, IntWritable>.Context context)
			throws IOException, InterruptedException {
		StringTokenizer st=new StringTokenizer(value.toString()); // 공백으로 자르기
		while(st.hasMoreTokens()) {
			keyValue.set(st.nextToken()); // String => Text
			context.write(keyValue, one); // 자른 값을 저장
		}
	}
	
}
