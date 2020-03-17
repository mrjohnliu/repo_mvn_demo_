package com.liuke.springbatch.demo.com;

import org.springframework.batch.core.JobExecution;
import org.springframework.batch.core.StepExecution;
import org.springframework.batch.core.job.flow.FlowExecutionStatus;
import org.springframework.batch.core.job.flow.JobExecutionDecider;

public class Mydecider implements JobExecutionDecider {
	private int count=0;
	@Override
	public FlowExecutionStatus decide(JobExecution arg0, StepExecution arg1) {
		count++;
		// TODO Auto-generated method stub
		if(count%2==1) {
			return new FlowExecutionStatus("even");
		}
		return new FlowExecutionStatus("add");
	}

}
