package com.tvo.puzzle.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.tvo.puzzle.dao.RoleDao;
import com.tvo.puzzle.dto.RoleDTO;
import com.tvo.puzzle.entity.Role;
import com.tvo.puzzle.service.IRoleService;

@Service("roleService")
public class RoleServiceImpl implements IRoleService {

	@Autowired
	RoleDao roleDao;
	@Override
	public List<RoleDTO> getAll() {
		List<Role> roles = new ArrayList<Role>();
		roles= roleDao.findAll();
		if(null!=roles){
			List<RoleDTO> dtos = new ArrayList<RoleDTO>();
			for(Role r: roles){
				RoleDTO dto = new RoleDTO();
				dto.setId(r.getId());
				dto.setRoleName(r.getRoleName());
				dtos.add(dto);
			}
			return dtos;
		}
		return null;
	}
	@Transactional
	public List<RoleDTO> findRoles() {
		List<Role> roles = new ArrayList<Role>();
		roles= roleDao.findRoles();
		if(null!=roles){
			List<RoleDTO> dtos = new ArrayList<RoleDTO>();
			for(Role r: roles){
				RoleDTO dto = new RoleDTO();
				dto.setId(r.getId());
				dto.setRoleName(r.getRoleName());
				dtos.add(dto);
			}
			return dtos;
		}
		return null;
	}

}
