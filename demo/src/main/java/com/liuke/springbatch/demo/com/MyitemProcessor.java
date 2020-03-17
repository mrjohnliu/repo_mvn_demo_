package com.liuke.springbatch.demo.com;

import org.springframework.batch.item.ItemProcessor;
import org.springframework.context.annotation.Configuration;

import com.liuke.springbatch.demo.entity.Emp;

@Configuration
public class MyitemProcessor implements ItemProcessor<Emp, Emp> {

	@Override
	public Emp process(Emp item) throws Exception {
		Emp emp = new Emp();
		emp.setAddress(item.getAddress());
		emp.setAge(item.getAge());
		emp.setEmail(item.getEmail().toUpperCase());
		emp.setId(item.getId());
		emp.setName(item.getName());
		emp.setSex(item.getSex());
		// TODO Auto-generated method stub
		return emp;
	}

}
