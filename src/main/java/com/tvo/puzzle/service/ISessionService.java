package com.tvo.puzzle.service;

import java.util.List;

import com.tvo.puzzle.dto.SessionDTO;

public interface ISessionService
{
	List<SessionDTO> getByPlayer(int playerId);

	SessionDTO getBySession(String sessionId);
	
	String create(int playerId);

	void removeAll(int playerId);
}
