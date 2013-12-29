package com.tvo.puzzle.dao.impl;

import org.springframework.stereotype.Repository;

import com.tvo.puzzle.dao.PointManagementDao;
import com.tvo.puzzle.entity.PointManagement;

@Repository
public class PointManagementDaoImpl extends GenericDaoImpl<PointManagement, Integer> implements PointManagementDao {

	public PointManagementDaoImpl() {
		super(PointManagement.class);
	}
	
}
