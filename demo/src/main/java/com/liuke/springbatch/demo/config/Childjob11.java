package com.liuke.springbatch.demo.config;

import org.springframework.batch.core.Step;
import org.springframework.batch.core.StepContribution;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.scope.context.ChunkContext;
import org.springframework.batch.core.step.tasklet.Tasklet;
import org.springframework.batch.repeat.RepeatStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class Childjob11 {
	@Autowired
	private JobBuilderFactory  jobBuilderFactory;
	
	@Autowired
	private StepBuilderFactory stepBuilderFactory;
	
	@Bean
	public org.springframework.batch.core.Job childjob1() {
		return jobBuilderFactory.get("childjob1")
				.start(childjob1step1())
				.next(childjob1step2())
				.build();
		
	}

	@Bean
	public Step childjob1step2() {
		return stepBuilderFactory.get("childjob1step2").tasklet(new Tasklet() {
			
			@Override
			public RepeatStatus execute(StepContribution arg0, ChunkContext arg1) throws Exception {
				System.out.println("childjob1 step2");
				return RepeatStatus.FINISHED;
			}
		}).build();
	}

	@Bean
	public Step childjob1step1() {
		return stepBuilderFactory.get("childjob1step1").tasklet(new Tasklet() {
			
			@Override
			public RepeatStatus execute(StepContribution arg0, ChunkContext arg1) throws Exception {
				System.out.println("childjob1step1");
				return RepeatStatus.FINISHED;
			}
		}).build();
	}
}
