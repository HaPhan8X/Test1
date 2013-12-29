package com.tvo.puzzle.webservice.response;

import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.XmlRootElement;

import com.tvo.puzzle.dto.ConfigMoneyToCoinDTO;

@XmlRootElement(name="xml")
@XmlAccessorType(XmlAccessType.NONE)
public class ConfigMoneyToCoinResponse extends BaseResponse {

	@XmlElementWrapper(name="configMoneyToCoinList")
	@XmlElement(name="configMoneyToCoin")
	public List<ConfigMoneyToCoinDTO> dtos;
	@XmlAttribute
    Integer isMore;

	public Integer getIsMore() {
		return isMore;
	}

	public void setIsMore(Integer isMore) {
		this.isMore = isMore;
	}

	public List<ConfigMoneyToCoinDTO> getDtos() {
		return dtos;
	}

	public void setDtos(List<ConfigMoneyToCoinDTO> dtos) {
		this.dtos = dtos;
	}
}
