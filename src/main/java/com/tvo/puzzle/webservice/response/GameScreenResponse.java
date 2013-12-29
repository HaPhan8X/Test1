package com.tvo.puzzle.webservice.response;

import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.XmlRootElement;

import com.tvo.puzzle.dto.GameDTO;

@XmlRootElement(name="xml")
@XmlAccessorType(XmlAccessType.NONE)
public class GameScreenResponse extends BaseResponse{

	@XmlElementWrapper(name="game-list")
	@XmlElement(name="game")
	public List<GameDTO> gameDTOs;

	public List<GameDTO> getGameDTOs() {
		return gameDTOs;
	}

	public void setGameDTOs(List<GameDTO> gameDTOs) {
		this.gameDTOs = gameDTOs;
	}
	
	
}
