package com.liuke.springbatch.demo.entity;

import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "detail")
@XmlAccessorType(XmlAccessType.FIELD)
public class Detail {
	@XmlElement(name = "dheader")
	private Dheader dheader;
	@XmlElement(name = "daddress")
	private List<Daddress> daddress;
	@XmlElement(name = "dfooter")
	private Dfooter dfooter;

	public Dheader getDheader() {
		return dheader;
	}

	public void setDheader(Dheader dheader) {
		this.dheader = dheader;
	}

	public List<Daddress> getDaddress() {
		return daddress;
	}

	public void setDaddress(List<Daddress> daddress) {
		this.daddress = daddress;
	}

	public Dfooter getDfooter() {
		return dfooter;
	}

	public void setDfooter(Dfooter dfooter) {
		this.dfooter = dfooter;
	}

}
