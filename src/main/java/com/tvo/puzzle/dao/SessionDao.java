package com.tvo.puzzle.dao;

import java.util.List;

import com.tvo.puzzle.entity.Session;

public interface SessionDao extends GenericDao<Session, Integer> 
{
	List<Session> findByPlayer(int playerId);
	
	Session findBySession(String sessionId);
	
	void deleteByPlayer(int playerId);
}
