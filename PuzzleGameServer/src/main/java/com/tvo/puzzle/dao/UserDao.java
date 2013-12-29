package com.tvo.puzzle.dao;

import java.util.List;

import com.tvo.puzzle.entity.User;

public interface UserDao extends GenericDao<User, Integer>
{
	List<User> findRangerUsers(Integer limit,Integer offset);

	int countUsers();
	User searchByUsername(String userName);
	User doLogin(String userName, String password);
	public User getUserByUsernameAndPassword(String username, String password);

}
