package com.liuke.springbatch.demo.com;

import java.util.HashMap;
import java.util.Map;

import org.springframework.batch.item.xml.StaxEventItemWriter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.FileSystemResource;
import org.springframework.oxm.xstream.XStreamMarshaller;

import com.liuke.springbatch.demo.entity.Emp;

@Configuration
public class MyXmlFileitemwriter {

	@Bean
	public StaxEventItemWriter<Emp> xmlfileWriter() {
		
		StaxEventItemWriter <Emp>staxEventItemWriter=new StaxEventItemWriter<Emp>();
		
		XStreamMarshaller marshaller=new XStreamMarshaller();
		 Map<String, Class<Emp>> aliasesMap=new HashMap<>();
		 aliasesMap.put("emp", Emp.class);
		 marshaller.setAliases(aliasesMap);
		 staxEventItemWriter.setMarshaller(marshaller);
		 staxEventItemWriter.setRootTagName("emps");
		 staxEventItemWriter.setResource(new FileSystemResource("C:\\Users\\liuke\\Documents\\emp.xml"));
		 try {
			staxEventItemWriter.afterPropertiesSet();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		 return staxEventItemWriter;
	}
}
