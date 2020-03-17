package com.liuke.springbatch.demo.entity;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
@XmlRootElement(name = "dheader")
@XmlAccessorType(XmlAccessType.FIELD)
public class Dheader {
	@XmlElement(name = "did")
	private String did;
	@XmlElement(name = "dname")
	private String dname;
	public String getDid() {
		return did;
	}
	public void setDid(String did) {
		this.did = did;
	}
	public String getDname() {
		return dname;
	}
	public void setDname(String dname) {
		this.dname = dname;
	}

}
