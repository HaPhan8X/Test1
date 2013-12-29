package com.tvo.puzzle.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.tvo.puzzle.dao.UserRoleDao;
import com.tvo.puzzle.dto.RoleDTO;
import com.tvo.puzzle.dto.UserDTO;
import com.tvo.puzzle.dto.UserRoleDTO;
import com.tvo.puzzle.entity.Role;
import com.tvo.puzzle.entity.User;
import com.tvo.puzzle.entity.UserRole;
import com.tvo.puzzle.service.IUserRoleService;
@Service("userRoleService")
public class UserRoleServiceImpl implements IUserRoleService {

	@Autowired
	UserRoleDao userRoleDao;
	@Override
	public List<UserRoleDTO> getByUser(Integer userId) {
		List<UserRole> entities = userRoleDao.getByUser(userId);
		if(null!=entities){
			List<UserRoleDTO> dtos = new ArrayList<UserRoleDTO>();
			for(UserRole entity:entities){
				UserRoleDTO dto = new UserRoleDTO();
				entityToDTO(entity,dto);
				dtos.add(dto);
			}
			return dtos;
		}
		return null;
	}
	private void entityToDTO(UserRole entity, UserRoleDTO dto) {
		dto.setId(entity.getId());
		UserDTO uDto = new UserDTO();
		uDto.setId(entity.getTblUser().getId());
		dto.setUserDTO(uDto);
		RoleDTO rDto = new RoleDTO();
		rDto.setId(entity.getTblUser().getId());
		rDto.setRoleName(entity.getTblRole().getRoleName());
		dto.setRoleDTO(rDto);
		
	}
	private void dtoToEntity(UserRoleDTO dto,UserRole entity){
		entity.setId(entity.getId());
		User u = new User();
		u.setId(dto.getUserDTO().getId());
		entity.setTblUser(u);
		Role r = new Role();
		r.setId(dto.getRoleDTO().getId());
		entity.setTblRole(r);
	}
	@Override
	@Transactional
	public int create(UserRoleDTO dto) {
		try{
			UserRole ur = new UserRole();
			dtoToEntity(dto, ur);
			return userRoleDao.save(ur);
			
		}catch (Exception e) {
			e.printStackTrace();
		}
		return 0;
	}
	@Override
	@Transactional
	public boolean update(UserRoleDTO dto) {
		try{
			UserRole ur = new UserRole();
			dtoToEntity(dto, ur);
			userRoleDao.update(ur);
			return true;
		}catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}
	@Transactional
	public void deleteByUserID(Integer id) {
		userRoleDao.deleteByUserID(id);
	}

	
}
