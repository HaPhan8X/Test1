package com.tvo.puzzle.webservice.response;

import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.XmlRootElement;

import com.tvo.puzzle.dto.ConfigGoldToCoinDTO;
@XmlRootElement(name="xml")
@XmlAccessorType(XmlAccessType.NONE)
public class ConfigGoldToCoinResponse extends BaseResponse {

	@XmlElementWrapper(name="configGoldToCoinList")
	@XmlElement(name="configGoldToCoin")
	public List<ConfigGoldToCoinDTO> dtos;
	@XmlAttribute
    Integer isMore;


	public Integer getIsMore() {
		return isMore;
	}

	public void setIsMore(Integer isMore) {
		this.isMore = isMore;
	}

	public List<ConfigGoldToCoinDTO> getDtos() {
		return dtos;
	}

	public void setDtos(List<ConfigGoldToCoinDTO> dtos) {
		this.dtos = dtos;
	}
}
