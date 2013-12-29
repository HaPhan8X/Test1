package com.tvo.puzzle.dto;

import java.util.Date;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name="detail")
@XmlAccessorType(XmlAccessType.NONE)
public class MemberDTO {	
	@XmlElement
	private Integer id;
	
	@XmlElement
	private String udid;
	
	@XmlElement
	private Date registerDate;
	
	@XmlElement
	private String userAgent;
	
	@XmlElement
	private int currentPoint;
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getUdid() {
		return udid;
	}
	public void setUdid(String udid) {
		this.udid = udid;
	}
	public Date getRegisterDate() {
		return registerDate;
	}
	public void setRegisterDate(Date registerDate) {
		this.registerDate = registerDate;
	}
	public String getUserAgent() {
		return userAgent;
	}
	public void setUserAgent(String userAgent) {
		this.userAgent = userAgent;
	}
	public int getCurrentPoint() {
		return currentPoint;
	}
	public void setCurrentPoint(int currentPoint) {
		this.currentPoint = currentPoint;
	}
}
