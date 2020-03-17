package com.liuke.springbatch.demo.config;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.StepContribution;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.job.builder.FlowBuilder;
import org.springframework.batch.core.job.builder.FlowBuilder.SplitBuilder;
import org.springframework.batch.core.job.flow.Flow;
import org.springframework.batch.core.scope.context.ChunkContext;
import org.springframework.batch.core.step.tasklet.Tasklet;
import org.springframework.batch.repeat.RepeatStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.task.SimpleAsyncTaskExecutor;

@Configuration
public class SpliDemo {
	@Autowired
	private JobBuilderFactory  jobBuilderFactory;
	
	@Autowired
	private StepBuilderFactory stepBuilderFactory;
	
	//多线程job
	@Bean
	public Job spliDemoJob() {
		return jobBuilderFactory.get("spliDemoJob")
				.start(spliDemo1())
				.split(new SimpleAsyncTaskExecutor())
				.add(spliDemo2())
				.end()
				.build();
	}
	@Bean
	public Step spliStep1() {
		return stepBuilderFactory.get("spliStep1").tasklet(new Tasklet() {
			
			@Override
			public RepeatStatus execute(StepContribution arg0, ChunkContext arg1) throws Exception {
				System.err.println("spliStep1");
				return RepeatStatus.FINISHED;
			}
		}).build();
	}
	@Bean
	public Step spliStep3() {
		return stepBuilderFactory.get("spliStep3").tasklet(new Tasklet() {
			
			@Override
			public RepeatStatus execute(StepContribution arg0, ChunkContext arg1) throws Exception {
				System.err.println("spliStep3");
				return RepeatStatus.FINISHED;
			}
		}).build();
	}
	@Bean
	public Step spliStep2() {
		return stepBuilderFactory.get("spliStep2").tasklet(new Tasklet() {
			@Override
			public RepeatStatus execute(StepContribution arg0, ChunkContext arg1) throws Exception {
				System.err.println("spliStep2");
				return RepeatStatus.FINISHED;
			}
		}).build();
	}
	
	@Bean
	public Flow spliDemo1() {
		//flow 是有step 组成的
		return (Flow)new FlowBuilder("spliDemo1").start(spliStep1()).next(spliStep3())
				.build();
	}
	@Bean
	public Flow spliDemo2() {
		//flow 是有step 组成的
		return (Flow)new FlowBuilder("spliDemo2").start(spliStep2())
				.build();
	}
	
	
}
