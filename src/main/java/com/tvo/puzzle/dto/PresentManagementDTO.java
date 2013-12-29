package com.tvo.puzzle.dto;

import java.util.Date;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

import com.tvo.puzzle.entity.Product;

@XmlRootElement(name="presentManagement")
@XmlAccessorType(XmlAccessType.NONE)
public class PresentManagementDTO {

	@XmlElement
	private Integer id;
	
	@XmlElement
	private MemberDTO tblMember;
	
	@XmlElement
	private ProductDTO tblProduct;
	
	@XmlElement
	private String udid;
	
	@XmlElement
	private Date registerDate;
	
	@XmlElement
	private String memberName;
	
	@XmlElement
	private String postalCode;
	
	@XmlElement
	private String address;
	
	@XmlElement
	private String phoneNumber;
	
	@XmlElement
	private String mailAddress;
	
	@XmlElement
	private String gender;
	
	@XmlElement
	private String message;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public MemberDTO getTblMember() {
		return tblMember;
	}

	public void setTblMember(MemberDTO tblMember) {
		this.tblMember = tblMember;
	}

	public ProductDTO getTblProduct() {
		return tblProduct;
	}

	public void setTblProduct(ProductDTO tblProduct) {
		this.tblProduct = tblProduct;
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

	public String getMemberName() {
		return memberName;
	}

	public void setMemberName(String memberName) {
		this.memberName = memberName;
	}

	public String getPostalCode() {
		return postalCode;
	}

	public void setPostalCode(String postalCode) {
		this.postalCode = postalCode;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	public String getMailAddress() {
		return mailAddress;
	}

	public void setMailAddress(String mailAddress) {
		this.mailAddress = mailAddress;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}
}
