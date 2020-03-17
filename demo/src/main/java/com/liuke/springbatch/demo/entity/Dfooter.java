package com.liuke.springbatch.demo.entity;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
@XmlAccessorType(XmlAccessType.FIELD)
@XmlRootElement(name = "dfooter")
public class Dfooter {
	@XmlElement(name = "dfSex")
	private String dfSex;
	@XmlElement(name = "dfage")
	private String dfage;
	public String getDfSex() {
		return dfSex;
	}
	public void setDfSex(String dfSex) {
		this.dfSex = dfSex;
	}
	public String getDfage() {
		return dfage;
	}
	public void setDfage(String dfage) {
		this.dfage = dfage;
	}
	
}
