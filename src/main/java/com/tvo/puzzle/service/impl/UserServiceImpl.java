package com.tvo.puzzle.service.impl;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.tvo.puzzle.dao.RoleDao;
import com.tvo.puzzle.dao.UserDao;
import com.tvo.puzzle.dto.RoleDTO;
import com.tvo.puzzle.dto.UserDTO;
import com.tvo.puzzle.entity.Role;
import com.tvo.puzzle.entity.User;
import com.tvo.puzzle.service.IUserRoleService;
import com.tvo.puzzle.service.IUserService;
import com.tvo.puzzle.util.PasswordSupport;

@Service("userService")
public class UserServiceImpl implements IUserService {

	@Autowired
	UserDao userDao;
	@Autowired
	RoleDao roleDao;
	@Inject
	private IUserRoleService userRoleService;	
	@Override
	public List<UserDTO> findRangerUsers(Integer limit, Integer offset) {
		List<User> users= userDao.findRangerUsers(limit, offset);
		if(null!=users&&users.size()>0){
			List<UserDTO> dtos = new ArrayList<UserDTO>();
			for(User entity:users){
				UserDTO dto = new UserDTO();
				entityToDTO(entity, dto);
				dtos.add(dto);
			}
			return dtos;
		}
		return null;
	}

	@Override
	@Transactional
	public int create(UserDTO dto)
	{
		User entity = new User();
		dtoToEntity(dto, entity);
		return userDao.save(entity);
	}

	@Override
	@Transactional
	public boolean update(UserDTO dto) {
		try{
		User entity = new User();
		dtoToEntity(dto, entity);
		userDao.update(entity);
		return true;
		}catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}



	@Transactional
	public void deleteUsersSelect(List<Integer> ids) {
		for(Integer i :ids)
		{
			//delete user role via id of user
			userRoleService.deleteByUserID(i);
			//delete user
			User u  = userDao.findById(i);
			userDao.delete(u);
		}
	}
	
	private void entityToDTO(User entity,UserDTO dto){
		dto.setId(entity.getId());
		dto.setUserName(entity.getUserName());
		dto.setPassword(entity.getPassword());
		if(entity.getLsRole()!=null && entity.getLsRole().size()>0)
		{
			dto.setRoleId(entity.getLsRole().get(0).getId());
			dto.setRoleName(entity.getLsRole().get(0).getRoleName());
		}
	}
	private void dtoToEntity(UserDTO dto,User entity)
	{
		entity.setId(dto.getId());
		entity.setUserName(dto.getUserName());
		entity.setPassword(PasswordSupport.getMD5Hash(dto.getPassword()));
		List<Role> lsRoles = new ArrayList<Role>();
		Role role = new Role();
		role.setId(dto.getRoleId());
		lsRoles.add(role);
		entity.setLsRole(lsRoles);
	}

	@Override
	public int countUsers() 
	{
		return userDao.countUsers();
	}

	@Override
	public UserDTO doLogin(String userName, String password) {
		UserDTO dto =null;
		User u = userDao.doLogin(userName,password);
		if(null!=u&&u.getId()>0){
			dto = new UserDTO();
			entityToDTO(u, dto);
		}
		return dto;
	}

	@Transactional
	public UserDTO findById(int userID) {
		UserDTO dto =null;
		User u = userDao.findById(userID);
		if(null!=u&&u.getId()>0){
			dto = new UserDTO();
			entityToDTO(u, dto);
		}
		return dto;
	}

	@Transactional
	public UserDTO getUserByUsernameAndPassword(String username, String password) {
		User user = userDao.getUserByUsernameAndPassword(username, password);
		UserDTO dto =null;
		if(null!=user&&user.getId()>0){
			dto = new UserDTO();
			entityToDTO(user, dto);
		}
		return dto;
	}

	@Transactional
	public List<RoleDTO> findRoleByUser(Integer userId) {
		List<Role> lsRoles = roleDao.getRoleByUser(userId);
		List<RoleDTO> lsRoleDTOs = new ArrayList<RoleDTO>();
		
		for (Role role : lsRoles)
		{
			RoleDTO roleDTO = new RoleDTO();
			BeanUtils.copyProperties(role, roleDTO);
			lsRoleDTOs.add(roleDTO);
		}
		
		return lsRoleDTOs;
	}
}
