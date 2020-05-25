package com.sist.web;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;


public class MainClass {

	public static void main(String[] args) {
		try {
			// hadoop : 데이터 저장소
			// hadoop의 역할 => 여러 유저가 수집한 데이터를 저장하는 장소
			
			// hadoop을 불러온다
			Configuration conf=new Configuration();
			
			Job job=Job.getInstance(conf,"WordCount");
			job.setJarByClass(MainClass.class);
			job.setMapperClass(WordMapper.class);
			job.setReducerClass(WordReducer.class);
			
			job.setOutputKeyClass(Text.class);
			job.setOutputValueClass(IntWritable.class);
			
			// 어떤파일을 읽을건지, 파일명은 뭐라고 할건지
			FileInputFormat.addInputPath(job, new Path("./input.txt"));
			FileOutputFormat.setOutputPath(job, new Path("./ouput"));
			
			job.waitForCompletion(true);
			
		} catch(Exception ex) {}
	}

}
