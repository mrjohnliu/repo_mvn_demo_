package com.liuke.springbatch.demo.entity;

import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlRootElement(name = "details")
public class Details {
	@XmlElement(name = "detail")
	private List<Detail> detail;

	public List<Detail> getDetail() {
		return detail;
	}

	public void setDetail(List<Detail> detail) {
		this.detail = detail;
	}
    public String toString(){  
        StringBuilder sb=new StringBuilder();  
        for(Detail de:detail){
        	System.out.println("getDheader: "+de.getDheader().getDname()
        	+"getDaddress size: "+de.getDaddress().size()+" address email: "
        	+de.getDaddress().get(0).getEmail()
        	+"getDfooter: "+de.getDfooter().getDfSex()+" "+de.getDfooter().getDfage()
        	);
            sb.append(de.toString());  
        }  
        return sb.toString();  
    }  
}
