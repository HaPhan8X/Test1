package com.tvo.puzzle.webservice.response;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name="xml")
@XmlAccessorType(XmlAccessType.NONE)
public class ResultResponse extends BaseResponse {

	public ResultResponse() {
		super();
	}
	
	public ResultResponse(String result, String errCode, String message) {
		super(result, errCode, message);
	}
	
}
