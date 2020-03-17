package com.liuke.springbatch.demo.entity;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.transform.stream.StreamSource;

import org.springframework.batch.item.xml.StaxEventItemReader;
import org.springframework.batch.item.xml.StaxEventItemWriter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.FileSystemResource;
import org.springframework.oxm.Marshaller;
import org.springframework.oxm.Unmarshaller;
import org.springframework.oxm.XmlMappingException;
import org.springframework.oxm.castor.CastorMarshaller;
import org.springframework.oxm.xstream.XStreamMarshaller;
import org.springframework.stereotype.Component;

@Component
public class TTT {
	private Marshaller marshaller;
	private Unmarshaller unmarshaller;
	@Autowired
    private DataBaseRep dataBaseRep;
	
	public Marshaller getMarshaller() {
		return marshaller;
	}

	public void setMarshaller(Marshaller marshaller) {
		this.marshaller = marshaller;
	}

	public Unmarshaller getUnmarshaller() {
		return unmarshaller;
	}

	public void setUnmarshaller(Unmarshaller unmarshaller) {
		this.unmarshaller = unmarshaller;
	}

	public static void main(String[] args) throws XmlMappingException, IOException {
		Unmarshaller us = null;
/*		//Unmarshaller us;
		File file = new File("C://Users//liuke//Downloads//demo//target//classes//emp.xml");
		JAXBContext jaxbC;
		try {
			
			jaxbC = JAXBContext.newInstance(Root.class);
			Unmarshaller us = jaxbC.createUnmarshaller();
			Root root = (Root) us.unmarshal(file);
			System.out.println(root.getDetails());
			
		} catch (JAXBException e) {
			e.printStackTrace();
		}*/
		////////////////////////////////////////////////////
	}
	
	
	
}
