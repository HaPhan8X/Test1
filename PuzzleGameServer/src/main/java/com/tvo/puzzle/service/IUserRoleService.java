package com.tvo.puzzle.service;

import java.util.List;

import com.tvo.puzzle.dto.UserRoleDTO;

public interface IUserRoleService {
	List<UserRoleDTO> getByUser(Integer userId);
	int create(UserRoleDTO dto);
	boolean update(UserRoleDTO dto);
	void deleteByUserID(Integer id);
}
