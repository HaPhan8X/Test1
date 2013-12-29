package com.tvo.puzzle.dao;

import java.util.List;

import com.tvo.puzzle.entity.Role;

public interface RoleDao extends GenericDao<Role, Integer> 
{
	List<Role> findRoles();
	List<Role> getRoleByUser(Integer userId);
}
