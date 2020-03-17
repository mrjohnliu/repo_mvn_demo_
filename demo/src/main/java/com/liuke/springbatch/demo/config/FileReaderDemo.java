package com.liuke.springbatch.demo.config;


import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.item.ItemWriter;
import org.springframework.batch.item.file.FlatFileItemReader;
import org.springframework.batch.item.file.mapping.DefaultLineMapper;
import org.springframework.batch.item.file.mapping.FieldSetMapper;
import org.springframework.batch.item.file.transform.DelimitedLineTokenizer;
import org.springframework.batch.item.file.transform.FieldSet;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.validation.BindException;

import com.liuke.springbatch.demo.entity.Emp;
@Configuration
public class FileReaderDemo{
	@Autowired
	private JobBuilderFactory  jobBuilderFactory;
	
	@Autowired
	private StepBuilderFactory stepBuilderFactory;
	
	@Autowired
	@Qualifier("writeIntodb")
	private ItemWriter<Emp> writeIntodb;
	
	
	@Bean
	public Job fileReaderJob2() {
		return jobBuilderFactory.get("fileReaderJob2")
				.start(fileReaderJobStep1())
				.build();
	}
	
	public Step fileReaderJobStep1() {
		return stepBuilderFactory
				.get("fileReaderJobStep1")
				.<Emp,Emp>chunk(2)
				.reader(flatFileItemReader())
				.writer(writeIntodb)
				.build();
	}

	@Bean
	public FlatFileItemReader<Emp> flatFileItemReader() {
		FlatFileItemReader<Emp> flatFileItemReader=new FlatFileItemReader<Emp>();
		flatFileItemReader.setResource(new ClassPathResource("fileReaddemo.txt"));
		flatFileItemReader.setLinesToSkip(0);//跳過第幾行
		//解析數據 只能用逗號分割的數據
		DelimitedLineTokenizer  delimitedLineTokenizer=new DelimitedLineTokenizer();
		delimitedLineTokenizer.setNames(new String[] {"id","name","sex","age","address","email"});
		//映射數據
		DefaultLineMapper<Emp> defaultLineMapper=new 
				DefaultLineMapper<Emp>();
		defaultLineMapper.setLineTokenizer(delimitedLineTokenizer);
		defaultLineMapper.setFieldSetMapper(new FieldSetMapper<Emp>() {
			
			@Override
			public Emp mapFieldSet(FieldSet fieldSet) throws BindException {
				Emp emp=new Emp();
				emp.setAddress(fieldSet.readString("address"));
				//emp.setId(fieldSet.readInt("id"));
				emp.setAge(fieldSet.readInt("age"));
				emp.setEmail(fieldSet.readString("email"));
				emp.setSex(fieldSet.readString("sex"));
				emp.setName(fieldSet.readString("name"));
				return emp;
			}
		});
		
		defaultLineMapper.afterPropertiesSet();
		flatFileItemReader.setLineMapper(defaultLineMapper);
		
		return flatFileItemReader;
	}
}
