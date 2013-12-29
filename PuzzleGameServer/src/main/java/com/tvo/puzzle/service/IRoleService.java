package com.tvo.puzzle.service;

import java.util.List;

import com.tvo.puzzle.dto.RoleDTO;

public interface IRoleService {
	List<RoleDTO> getAll();
	List<RoleDTO> findRoles();
}
