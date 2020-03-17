package com.liuke.springbatch.demo.config;


import java.io.File;
import java.util.HashMap;
import java.util.Map;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.Unmarshaller;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.item.ItemWriter;
import org.springframework.batch.item.xml.StaxEventItemReader;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.oxm.xstream.XStreamMarshaller;

import com.liuke.springbatch.demo.com.DetailFileWriter;
import com.liuke.springbatch.demo.com.FlatFileWriter;
import com.liuke.springbatch.demo.com.MyStaxEventItemWriter;
import com.liuke.springbatch.demo.com.MyXmlFileitemwriter;
import com.liuke.springbatch.demo.com.MyXmlFileitemwriter2;
import com.liuke.springbatch.demo.com.MyXmlItemReader2;
import com.liuke.springbatch.demo.entity.Detail;
import com.liuke.springbatch.demo.entity.Emp;
import com.liuke.springbatch.demo.entity.Root;
@Configuration
public class XMlReaderDemo{
	@Autowired
	private JobBuilderFactory  jobBuilderFactory;
	
	@Autowired
	private StepBuilderFactory stepBuilderFactory;
	
	@Autowired
	@Qualifier("xmlfileWriter2")
	private  ItemWriter<Root>  xmlfileWriter2;
	
	@Bean
	public Job xmlReaderJob8() {
		return jobBuilderFactory.get("xmlReaderJob8")
				.start(xmlReaderJobStep8())
				.build();
	}
	
	public Step xmlReaderJobStep8() {
		return stepBuilderFactory
				.get("xmlReaderJobStep8")
				.<Root,Root>chunk(2)
				.reader(myReader())
				.writer(xmlfileWriter2)
				.build();
	}
/*	@Bean
	public FlatFileWriter systemOutWriter() {
		return new FlatFileWriter();
	}*/
	@Bean
	public StaxEventItemReader<Root> staxEventItemReadermethod() {
/*		StaxEventItemReader<Emp> staxEventItemReader=new StaxEventItemReader<Emp>();
		staxEventItemReader.setResource(new ClassPathResource("emp.xml"));
		//指定根元素
		staxEventItemReader.setFragmentRootElementName("emp");
		
		
		XStreamMarshaller marshaller=new XStreamMarshaller();
		
		Map<String, Class> map=new HashMap<>();
		map.put("emp", Emp.class);
		marshaller.setAliases(map);
		staxEventItemReader.setUnmarshaller(marshaller);*/
		
		StaxEventItemReader<Root> staxEventItemReader=new StaxEventItemReader<Root>();
		staxEventItemReader.setResource(new ClassPathResource("emp.xml"));
		//指定根元素
		staxEventItemReader.setFragmentRootElementName("Root");
		XStreamMarshaller marshaller=new XStreamMarshaller();
		Map<String, Class> map=new HashMap<>();
		map.put("Root", Root.class);
		marshaller.setAliases(map);
		staxEventItemReader.setUnmarshaller(marshaller);
		return staxEventItemReader;
	}
	
	
	
	@Bean
	public MyXmlItemReader2 myReader(){
		
		return new MyXmlItemReader2();
	}
	
	
	
}
