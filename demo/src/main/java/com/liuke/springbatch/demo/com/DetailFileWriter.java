package com.liuke.springbatch.demo.com;

import java.util.List;

import org.springframework.batch.item.ItemWriter;

import com.liuke.springbatch.demo.entity.Detail;

public class DetailFileWriter implements ItemWriter<Detail>{
	@Override
	public void write(List<? extends Detail> items) throws Exception {

		System.out.println("print writer:");
		
		for (Detail emp : items) {
			System.out.println(" write success name:"+emp.getDaddress().get(0).getEmail());
		}
		
	}
}
