package com.liuke.springbatch.demo.com;

import org.springframework.batch.core.SkipListener;
import org.springframework.context.annotation.Configuration;

import com.liuke.springbatch.demo.entity.Emp;

@Configuration
public class MyskipListener implements SkipListener<Emp, Emp> {

	@Override
	public void onSkipInProcess(Emp arg0, Throwable arg1) {
		System.out.println("onSkipInProcess");
		
	}

	@Override
	public void onSkipInRead(Throwable arg0) {
		System.out.println("onSkipInRead");
		
	}

	@Override
	public void onSkipInWrite(Emp arg0, Throwable arg1) {
		System.out.println("onSkipInWrite");
		
	}

}
