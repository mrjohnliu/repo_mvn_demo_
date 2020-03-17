package com.liuke.springbatch.demo.config;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.StepContribution;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.job.builder.FlowBuilder;
import org.springframework.batch.core.job.flow.Flow;
import org.springframework.batch.core.scope.context.ChunkContext;
import org.springframework.batch.core.step.tasklet.Tasklet;
import org.springframework.batch.repeat.RepeatStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class FlowDemo {
	
	@Autowired
	private JobBuilderFactory  jobBuilderFactory;
	
	@Autowired
	private StepBuilderFactory stepBuilderFactory;
	
	@Bean
	public Job flowDemoJob() {
		return jobBuilderFactory.get("flowDemoJob")
				.start(flowDemo1())
				.end()
				.build();
	}
	@Bean
	public Step flowStep1() {
		return stepBuilderFactory.get("flowStep1").tasklet(new Tasklet() {
			
			@Override
			public RepeatStatus execute(StepContribution arg0, ChunkContext arg1) throws Exception {
				System.err.println("flowStep1");
				return RepeatStatus.FINISHED;
			}
		}).build();
	}
	@Bean
	public Step flowStep2() {
		return stepBuilderFactory.get("flowStep2").tasklet(new Tasklet() {
			@Override
			public RepeatStatus execute(StepContribution arg0, ChunkContext arg1) throws Exception {
				System.err.println("flowStep2");
				return RepeatStatus.FINISHED;
			}
		}).build();
	}
	
	@Bean
	public Flow flowDemo1() {
		//flow 是有step 组成的
		return (Flow)new FlowBuilder("flowDemo1").start(flowStep2())
				.next(flowStep1())
				.build();
	}
	
}
