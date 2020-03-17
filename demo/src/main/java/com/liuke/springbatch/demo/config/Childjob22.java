
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
public class Childjob22 {
	@Autowired
	private JobBuilderFactory  jobBuilderFactory;
	
	@Autowired
	private StepBuilderFactory stepBuilderFactory;
	
	@Bean
	public org.springframework.batch.core.Job childjob2() {
		return jobBuilderFactory.get("childjob2")
				.start(childjob2step1())
				.next(childjob2step2())
				.build();
		
	}

	@Bean
	public Step childjob2step2() {
		return stepBuilderFactory.get("childjob2step2").tasklet(new Tasklet() {
			
			@Override
			public RepeatStatus execute(StepContribution arg0, ChunkContext arg1) throws Exception {
				System.out.println("childjob2 step2");
				return RepeatStatus.FINISHED;
			}
		}).build();
	}

	@Bean
	public Step childjob2step1() {
		return stepBuilderFactory.get("childjob2step1").tasklet(new Tasklet() {
			
			@Override
			public RepeatStatus execute(StepContribution arg0, ChunkContext arg1) throws Exception {
				System.out.println("childjob2 step1");
				return RepeatStatus.FINISHED;
			}
		}).build();
	}
}

