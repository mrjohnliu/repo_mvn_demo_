package com.liuke.springbatch.demo.com;

import java.io.File;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.Unmarshaller;

import org.springframework.batch.item.ItemReader;
import org.springframework.batch.item.NonTransientResourceException;
import org.springframework.batch.item.ParseException;
import org.springframework.batch.item.UnexpectedInputException;
import org.springframework.context.annotation.Configuration;

import com.liuke.springbatch.demo.entity.Root;


@Configuration
public class MyXmlItemReader2 implements ItemReader<Root> {


	@Override
	public Root read() throws Exception, UnexpectedInputException, ParseException, NonTransientResourceException {

		Root root=null;
		File file = new File("C://Users//liuke//Downloads//demo//target//classes//emp.xml");
		JAXBContext jaxbC;
		try {
			
			jaxbC = JAXBContext.newInstance(Root.class);
			Unmarshaller us = jaxbC.createUnmarshaller();
			 root = (Root) us.unmarshal(file);
			System.out.println(root.getDetails());
			
		}catch (Exception e) {
			e.printStackTrace();
		}
		return root;
	}	
}
