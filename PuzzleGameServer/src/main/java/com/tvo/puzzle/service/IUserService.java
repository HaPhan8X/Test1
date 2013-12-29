package com.tvo.puzzle.service;

import java.util.List;

import com.tvo.puzzle.dto.RoleDTO;
import com.tvo.puzzle.dto.UserDTO;

public interface IUserService {
	int countUsers();
	List<UserDTO> findRangerUsers(Integer limit,Integer offset);
	int create(UserDTO dto);
	boolean update(UserDTO dto);
	UserDTO doLogin(String userName, String password);
	UserDTO findById(int userID);
	void deleteUsersSelect(List<Integer> ids);
	UserDTO getUserByUsernameAndPassword(String username, String password);
	List<RoleDTO> findRoleByUser(Integer userId);
}
