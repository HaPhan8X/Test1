package com.tvo.puzzle.dao;

import java.util.List;

import com.tvo.puzzle.entity.UserRole;

public interface UserRoleDao extends GenericDao<UserRole, Integer> 
{
	List<UserRole> getByUser(Integer userId);
	void deleteByUserID(Integer id);
}
