package com.liuke.springbatch.demo.com;

import org.springframework.batch.core.JobExecution;
import org.springframework.batch.core.JobExecutionListener;
import org.springframework.batch.core.listener.JobExecutionListenerSupport;

public class MyListner implements JobExecutionListener{

	@Override
	public void afterJob(JobExecution jobExecution) {

		System.out.println(jobExecution.getJobId()+" instance:"+jobExecution.getJobInstance()+"do afterJob");
		
	}

	@Override
	public void beforeJob(JobExecution jobExecution) {
		System.out.println(jobExecution.getJobId()+" instance:"+jobExecution.getJobInstance()+"do beforeJob");
		
	}

}
