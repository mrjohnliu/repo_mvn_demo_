package com.liuke.springbatch.demo.config;

import java.util.Arrays;
import java.util.Map;

import org.springframework.batch.core.ExitStatus;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobParameter;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.StepContribution;
import org.springframework.batch.core.StepExecution;
import org.springframework.batch.core.StepExecutionListener;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.scope.context.ChunkContext;
import org.springframework.batch.core.step.tasklet.Tasklet;
import org.springframework.batch.item.ItemReader;
import org.springframework.batch.item.support.ListItemReader;
import org.springframework.batch.repeat.RepeatStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.liuke.springbatch.demo.com.MyListner;

@Configuration
public class JobParemetersDemo implements StepExecutionListener {
	@Autowired
	private JobBuilderFactory  jobBuilderFactory;
	
	@Autowired
	private StepBuilderFactory stepBuilderFactory;
	
	private Map<String, JobParameter> paremetes;
	@Bean
	public Job jobParemetersDemoJob2() {
		return jobBuilderFactory.get("jobParemetersDemoJob2")
				.start(jobParemeterStep())
				.listener(new MyListner())
				.build();
	}
	private Step jobParemeterStep() {
		return stepBuilderFactory.get("jobParemeter")
		.listener(this).tasklet(new Tasklet() {
			
			@Override
			public RepeatStatus execute(StepContribution contribution, ChunkContext chunkContext) throws Exception {
				// TODO Auto-generated method stub
				//輸出參數：
				/*if (chunkContext.getStepContext().
						getStepExecution().
						getStatus().isUnsuccessful()) {
					System.out.println(paremetes.get("name").getValue()); 
				}*/
				System.out.println("finish..................");
				return RepeatStatus.FINISHED;
			}
		})
		.build();
	}

	@Override
	public void beforeStep(StepExecution stepExecution) {
		paremetes=stepExecution.getJobParameters().getParameters();
		
	}
	@Override
	public ExitStatus afterStep(StepExecution stepExecution) {
		// TODO Auto-generated method stub
		return null;
	}
}
