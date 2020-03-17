package com.liuke.springbatch.demo.entity;

import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "Root")
@XmlAccessorType(XmlAccessType.FIELD)
public class Root {
	@XmlElement(name = "dheader")
	private Feader header;
	@XmlElement(name = "details")
	private Details details;
	@XmlElement(name = "fooder")
	private Fooder fooder;
	public Feader getHeader() {
		return header;
	}
	public void setHeader(Feader header) {
		this.header = header;
	}
	public Details getDetails() {
		return details;
	}
	public void setDetails(Details details) {
		this.details = details;
	}
	public Fooder getFooder() {
		return fooder;
	}
	public void setFooder(Fooder fooder) {
		this.fooder = fooder;
	}

	
}
