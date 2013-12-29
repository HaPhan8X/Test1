package com.tvo.puzzle.webservice.response;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

import com.tvo.puzzle.dto.PresentDTO;

@XmlRootElement(name="xml")
@XmlAccessorType(XmlAccessType.NONE)
public class PresentResponse extends BaseResponse{
	
	@XmlElement(name="present-detail")
	public PresentDTO presentDTO;

	public PresentDTO getPresentDTO() {
		return presentDTO;
	}

	public void setPresentDTO(PresentDTO presentDTO) {
		this.presentDTO = presentDTO;
	}
}
