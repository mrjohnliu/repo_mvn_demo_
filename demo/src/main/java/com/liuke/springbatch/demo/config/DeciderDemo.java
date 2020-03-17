package com.liuke.springbatch.demo.config;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.StepContribution;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.job.flow.JobExecutionDecider;
import org.springframework.batch.core.scope.context.ChunkContext;
import org.springframework.batch.core.step.tasklet.Tasklet;
import org.springframework.batch.repeat.RepeatStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.task.SimpleAsyncTaskExecutor;

import com.liuke.springbatch.demo.com.Mydecider;

@Configuration
public class DeciderDemo {
	@Autowired
	private JobBuilderFactory  jobBuilderFactory;
	
	@Autowired
	private StepBuilderFactory stepBuilderFactory;
	
	//决策器的使用
	@Bean
	public Job deciderDemoJob() {
		return jobBuilderFactory.get("deciderDemoJob1")
				.start(decidertep1())
				.next(decider())
				.from(decider()).on("even").to(decidertep3())
				.from(decider()).on("add").to(deciderStep2())
				//执行step3完了无论遇到什么都执行decider
				//.from(decidertep3()).on("*").to(decider())
				.end()
				.build();
	}
	
	@Bean
	public Step decidertep1() {
		return stepBuilderFactory.get("decidertep1").tasklet(new Tasklet() {
			
			@Override
			public RepeatStatus execute(StepContribution arg0, ChunkContext arg1) throws Exception {
				System.err.println("spliStep1");
				return RepeatStatus.FINISHED;
			}
		}).build();
	}
	@Bean
	public Step decidertep3() {
		return stepBuilderFactory.get("decidertep3").tasklet(new Tasklet() {
			
			@Override
			public RepeatStatus execute(StepContribution arg0, ChunkContext arg1) throws Exception {
				System.err.println("spliStep3");
				return RepeatStatus.FINISHED;
			}
		}).build();
	}
	@Bean
	public Step deciderStep2() {
		return stepBuilderFactory.get("deciderStep2").tasklet(new Tasklet() {
			@Override
			public RepeatStatus execute(StepContribution arg0, ChunkContext arg1) throws Exception {
				System.err.println("spliStep2");
				return RepeatStatus.FINISHED;
			}
		}).build();
	}
	
	@Bean
	public JobExecutionDecider decider() {
		
		return new Mydecider();
	}
}
