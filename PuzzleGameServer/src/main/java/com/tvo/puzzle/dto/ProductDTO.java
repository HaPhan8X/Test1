package com.tvo.puzzle.dto;

import java.util.Date;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name="product")
@XmlAccessorType(XmlAccessType.NONE)
public class ProductDTO {
		
	@XmlElement
	private Integer id;
	
	@XmlElement
	private String productCode;
	
	@XmlElement
	private String productName;
	
	@XmlElement
	private Date createDate;
	
	@XmlElement
	private int requiredPoint;
	
	@XmlElement
	private Date startTime;
	
	@XmlElement
	private Date endTime;
	
	@XmlElement
	private boolean valid;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getProductCode() {
		return productCode;
	}

	public void setProductCode(String productCode) {
		this.productCode = productCode;
	}

	public String getProductName() {
		return productName;
	}

	public void setProductName(String productName) {
		this.productName = productName;
	}

	public Date getCreateDate() {
		return createDate;
	}

	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}

	public int getRequiredPoint() {
		return requiredPoint;
	}

	public void setRequiredPoint(int requiredPoint) {
		this.requiredPoint = requiredPoint;
	}

	public Date getStartTime() {
		return startTime;
	}

	public void setStartTime(Date startTime) {
		this.startTime = startTime;
	}

	public Date getEndTime() {
		return endTime;
	}

	public void setEndTime(Date endTime) {
		this.endTime = endTime;
	}

	public boolean isValid() {
		return valid;
	}

	public void setValid(boolean valid) {
		this.valid = valid;
	}
}
