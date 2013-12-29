package com.tvo.puzzle.webservice.response;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;

@XmlAccessorType(XmlAccessType.NONE)
public class BaseResponse {

	@XmlElement(name = "result")
	private String result;
	@XmlElement(name = "errorcode")
	private String errCode;
	@XmlElement(name = "message")
	private String message;
	
	public BaseResponse() {
	}
	
	public BaseResponse(String result, String errCode, String message) {
		this.result = result;
		this.errCode = errCode;
		this.message = message;
	}

	public String getResult() {
		return result;
	}

	public void setResult(String result) {
		this.result = result;
	}

	public String getErrCode() {
		return errCode;
	}

	public void setErrCode(String errCode) {
		this.errCode = errCode;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

}
