package com.liuke.springbatch.demo.com;

import java.util.HashMap;
import java.util.Map;

import org.springframework.batch.item.xml.StaxEventItemWriter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.FileSystemResource;
import org.springframework.oxm.xstream.XStreamMarshaller;
import org.springframework.stereotype.Component;

import com.liuke.springbatch.demo.entity.Root;
@Component
public class MyXmlFileitemwriter2 {

	@Bean
	public StaxEventItemWriter<Root> xmlfileWriter2() {
		
		StaxEventItemWriter <Root>staxEventItemWriter=new StaxEventItemWriter<Root>();
		
		XStreamMarshaller marshaller=new XStreamMarshaller();
		 Map<String, Class<Root>> aliasesMap=new HashMap<>();
		 aliasesMap.put("Root", Root.class);
		 marshaller.setAliases(aliasesMap);
		 staxEventItemWriter.setMarshaller(marshaller);
		 staxEventItemWriter.setRootTagName("Root");
		 staxEventItemWriter.setResource(new FileSystemResource("C:\\Users\\liuke\\Documents\\emp4.xml"));
		 try {
			staxEventItemWriter.afterPropertiesSet();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		 return staxEventItemWriter;
	}
}
