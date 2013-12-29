package com.tvo.puzzle.dto;

import java.util.Date;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name="pointManagement")
@XmlAccessorType(XmlAccessType.NONE)
public class PointManagementDTO {
	
	@XmlElement
	private Integer id;
	
	@XmlElement
	private MemberDTO tblMember;
	
	@XmlElement
	private ProjectDTO tblProject;
	
	@XmlElement
	private Date createdDate;
	
	@XmlElement
	private int pointValue;
	
	@XmlElement
	private String code;

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

	public Date getCreatedDate() {
		return createdDate;
	}

	public void setCreatedDate(Date createdDate) {
		this.createdDate = createdDate;
	}

	public int getPointValue() {
		return pointValue;
	}

	public void setPointValue(int pointValue) {
		this.pointValue = pointValue;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public ProjectDTO getTblProject() {
		return tblProject;
	}

	public void setTblProject(ProjectDTO tblProject) {
		this.tblProject = tblProject;
	}
}
