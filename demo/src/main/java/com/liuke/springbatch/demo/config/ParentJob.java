package com.liuke.springbatch.demo.config;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.job.flow.JobExecutionDecider;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.batch.core.repository.JobRepository;
import org.springframework.batch.core.step.builder.JobStepBuilder;
import org.springframework.batch.core.step.builder.StepBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.transaction.PlatformTransactionManager;

@Configuration
public class ParentJob {
	
	@Autowired
	private JobBuilderFactory  jobBuilderFactory;
	
	@Autowired
	private StepBuilderFactory stepBuilderFactory;
	
	@Autowired
	public Job childjob1;
	
	@Autowired
	public Job childjob2;
	
	@Autowired
	public JobLauncher jobLauncher;
	@Bean
	public Job parentDemoJob2(JobRepository jobRepository, PlatformTransactionManager transactionManager) {
		return jobBuilderFactory.get("parentDemoJob2")
				.start(createChildjob1Step(jobRepository,transactionManager))
				.next(createChildjob2Step(jobRepository,transactionManager)).build();
		
	}
	//返回的特殊的job類型的step
	@Bean
	public Step createChildjob2Step(JobRepository jobRepository, PlatformTransactionManager transactionManager) {
		
		return new JobStepBuilder(new StepBuilder("createChildjob2Step"))
				.job(childjob2)
				.launcher(jobLauncher)
				.repository(jobRepository)
			    .transactionManager(transactionManager).
				build();
	}

	@Bean
	public Step createChildjob1Step(JobRepository jobRepository,PlatformTransactionManager transactionManager) {
		return new JobStepBuilder(new StepBuilder("createChildjob2Step"))
				.job(childjob1)
				.launcher(jobLauncher)
				.repository(jobRepository)
				.transactionManager(transactionManager)
				.build();
	}
}
