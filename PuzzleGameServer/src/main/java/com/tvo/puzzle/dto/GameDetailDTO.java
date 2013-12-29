package com.tvo.puzzle.dto;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;


@XmlRootElement(name="gameDetail")
@XmlAccessorType(XmlAccessType.NONE)
public class GameDetailDTO {
	@XmlElement
	private Integer id;
	
	@XmlElement(name="Game")
	private GameDTO tblGame;
	
	@XmlElement
	private String resource;
	
	@XmlElement
	private String folderName;
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public GameDTO getTblGame() {
		return tblGame;
	}
	public void setTblGame(GameDTO tblGame) {
		this.tblGame = tblGame;
	}
	public String getResource() {
		return resource;
	}
	public void setResource(String resource) {
		this.resource = resource;
	}
	public String getFolderName() {
		return folderName;
	}
	public void setFolderName(String folderName) {
		this.folderName = folderName;
	}
	
}
