package com.liuke.springbatch.demo.config;

import java.util.Arrays;
import java.util.List;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.StepContribution;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.scope.context.ChunkContext;
import org.springframework.batch.core.step.tasklet.Tasklet;
import org.springframework.batch.item.ItemReader;
import org.springframework.batch.item.ItemWriter;
import org.springframework.batch.item.support.ListItemReader;
import org.springframework.batch.repeat.RepeatStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.liuke.springbatch.demo.com.ChunkListener;
import com.liuke.springbatch.demo.com.MyItemWriter;
import com.liuke.springbatch.demo.com.MyListner;
@Configuration
public class MyJobListener{
	@Autowired
	private JobBuilderFactory  jobBuilderFactory;
	
	@Autowired
	private StepBuilderFactory stepBuilderFactory;
	//多线程job
	@Bean
	public Job myJobListenerJob1() {
		return jobBuilderFactory.get("myJobListenerJob1")
				.start(lisStep2())
				.listener(new MyListner())
				.build();
	}
	@Bean
	public Step lisStep3() {
		return stepBuilderFactory.get("lisStep3").tasklet(new Tasklet() {
			
			@Override
			public RepeatStatus execute(StepContribution arg0, ChunkContext arg1) throws Exception {
				System.err.println("lisStep1");
				return RepeatStatus.FINISHED;
			}
		}).build();
	}
	@Bean
	public Step lisStep2() {
		return stepBuilderFactory.get("lisStep2")
				.<String,String>chunk(2).faultTolerant()
				.listener(new ChunkListener())
				.reader(reader())
				.writer(writer())
				.build();
				
				
	}
	private ItemWriter<String> writer() {
		return new ItemWriter<String>() {
			@Override
			public void write(List<? extends String> items) throws Exception {
				System.out.println("write success"+items);
				
			}
			
		};
	}
	private ItemReader<String> reader() {
		//ItemReader itemReader=new MyItemReader();
		return new ListItemReader<>
		(Arrays.asList("111","222","234"));
	}
	
}
