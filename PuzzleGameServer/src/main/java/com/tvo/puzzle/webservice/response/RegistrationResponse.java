package com.tvo.puzzle.webservice.response;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.XmlRootElement;

import com.tvo.puzzle.dto.MemberDTO;

@XmlRootElement(name="xml")
@XmlAccessorType(XmlAccessType.NONE)
public class RegistrationResponse extends BaseResponse{
	
	@XmlElement(name="member")
	public MemberDTO memberDTO;

	public MemberDTO getMemberDTO() {
		return memberDTO;
	}

	public void setMemberDTO(MemberDTO memberDTO) {
		this.memberDTO = memberDTO;
	}
	
}
