package com.tvo.puzzle.dto;

import java.io.Serializable;

public class RoleDTO implements Serializable
{
	/**
	 * 
	 */
	private static final long serialVersionUID = 3936300319747664941L;
	private Integer id;
	private String roleName;
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getRoleName() {
		return roleName;
	}
	public void setRoleName(String roleName) {
		this.roleName = roleName;
	}

}
