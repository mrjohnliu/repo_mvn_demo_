package com.liuke.springbatch.demo.com;

import java.util.List;

import org.springframework.batch.item.ItemWriter;

import com.liuke.springbatch.demo.entity.Emp;

public class MyItemWriter implements ItemWriter<Emp> {

	@Override
	public void write(List<? extends Emp> items) throws Exception {
		//items 對象就是要寫出的數據
		for (Emp emp : items) {
			System.out.println(" write success"+emp.getName()+emp.getEmail()+emp.getAge());
		}
		
	}

}
