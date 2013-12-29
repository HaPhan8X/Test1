package com.tvo.puzzle.dto;

import java.io.Serializable;

public class UserDTO implements Serializable
{
	private Integer id;
	private String userName;
	private String password;
	private Integer roleId;
	private String roleName;
	private Boolean select = false;
	
	public Boolean getSelect() {
		return select;
	}
	public void setSelect(Boolean select) {
		this.select = select;
	}
	public Integer getRoleId() {
		return roleId;
	}
	public void setRoleId(Integer roleId) {
		this.roleId = roleId;
	}
	public String getRoleName() {
		return roleName;
	}
	public void setRoleName(String roleName) {
		this.roleName = roleName;
	}
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	/**
	 * @return the select
	 */
	public boolean isSelect() {
		return select;
	}
	/**
	 * @param select the select to set
	 */
	public void setSelect(boolean select) {
		this.select = select;
	}

}
