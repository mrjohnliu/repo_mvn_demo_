package com.liuke.springbatch.demo.com;

import org.springframework.batch.item.file.FlatFileItemWriter;
import org.springframework.batch.item.file.transform.LineAggregator;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.FileSystemResource;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.liuke.springbatch.demo.entity.Emp;
@Configuration
public class MyFileItemWriter  {

	@Bean
	public FlatFileItemWriter<Emp> fileItemWriter() throws Exception {
		FlatFileItemWriter<Emp> flatFileItemWriter=new FlatFileItemWriter<Emp>();
		//輸出到指定文件
		flatFileItemWriter.setResource(new FileSystemResource("C:\\Users\\liuke\\Documents\\emp.txt"));
		//寫出到文件
		//把對象轉換成字符串
		flatFileItemWriter.setLineAggregator(new LineAggregator<Emp>() {
			ObjectMapper objectMapper=new ObjectMapper();
			@Override
			public String aggregate(Emp item) {
				String str = "";
				try {
					str= objectMapper.writeValueAsString(item);
				} catch (JsonProcessingException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				return str;
			}
		});
		
		flatFileItemWriter.afterPropertiesSet();
		return flatFileItemWriter;
		
	}

}
