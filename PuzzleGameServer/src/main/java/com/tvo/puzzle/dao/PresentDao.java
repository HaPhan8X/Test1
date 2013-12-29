package com.tvo.puzzle.dao;

import com.tvo.puzzle.entity.PresentManagement;



public interface PresentDao extends GenericDao<PresentManagement, Integer>{
	public void saveUserProfile(PresentManagement p);
	
}
