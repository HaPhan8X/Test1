package com.tvo.puzzle.webservice.response;

import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.XmlRootElement;

import com.tvo.puzzle.dto.ConfigMoneyToGoldDTO;

@XmlRootElement(name="xml")
@XmlAccessorType(XmlAccessType.NONE)
public class ConfigMoneyToGoldResponse extends BaseResponse{

	@XmlElementWrapper(name="configMoneyToGoldList")
	@XmlElement(name="configMoneyToGold")
	public List<ConfigMoneyToGoldDTO> dtos;
	@XmlAttribute
    Integer isMore;

	public Integer getIsMore() {
		return isMore;
	}

	public void setIsMore(Integer isMore) {
		this.isMore = isMore;
	}

	public List<ConfigMoneyToGoldDTO> getDtos() {
		return dtos;
	}

	public void setDtos(List<ConfigMoneyToGoldDTO> dtos) {
		this.dtos = dtos;
	}
}
