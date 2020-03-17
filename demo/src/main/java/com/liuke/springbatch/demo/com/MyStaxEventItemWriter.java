package com.liuke.springbatch.demo.com;

import java.io.IOException;
import java.util.List;

import org.springframework.batch.item.xml.StaxEventItemWriter;
import org.springframework.oxm.XmlMappingException;

import com.liuke.springbatch.demo.entity.Emp;

import net.bytebuddy.asm.Advice.This;

public class MyStaxEventItemWriter extends StaxEventItemWriter<Emp> {

	@Override
	public void write(List<? extends Emp> items) throws XmlMappingException, IOException {
		
		for (Emp emp : items) {
			System.out.println("Class name:"+This.class.getName()+emp.getName()+emp.getEmail()+emp.getAge());
		}
	}

}
