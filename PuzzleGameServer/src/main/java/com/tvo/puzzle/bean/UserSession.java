package com.tvo.puzzle.bean;

import java.io.Serializable;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;

import org.primefaces.context.RequestContext;

import com.tvo.puzzle.dto.RoleDTO;
import com.tvo.puzzle.dto.UserDTO;
import com.tvo.puzzle.service.IUserService;
import com.tvo.puzzle.util.JsfUtil;
import com.tvo.puzzle.util.PasswordSupport;
import com.tvo.puzzle.util.PropertiesUtil;

@SessionScoped
@ManagedBean(name = "userSession")
public class UserSession extends BaseBean implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 6832127212850464987L;

	@ManagedProperty(name="userService",value="#{userService}")
	private IUserService userService;
	
	private UserDTO currentUser;
	private List<RoleDTO> roles;
	private String currentPassword;
	private String newPassword;
	private String reNewPassword;
	
	public String init()
	{
		return"account";
	}
	
	private boolean isValidate() 
	{
		if(!currentUser.getPassword().equals(PasswordSupport.getMD5Hash(currentPassword)))
		{
			JsfUtil.addErrorMessage("Current Password is not valid");
			return false;
		}
		if(!newPassword.equals(reNewPassword))
		{
			JsfUtil.addErrorMessage("Re type new password do not match");
			return false;
		}
		
		return true;
	}
	
	public void update()
	{
		try
		{
			if(isValidate())
			{
				currentUser.setPassword(newPassword);
				userService.update(currentUser);
				currentUser.setPassword(PasswordSupport.getMD5Hash(newPassword));
				RequestContext context = RequestContext.getCurrentInstance();
				JsfUtil.addSuccessMessage(PropertiesUtil.getMessage("update.successful"));
				context.addCallbackParam("createSuccess", true);
				cancel();
			}
		}
		catch (Exception e) 
		{
			e.printStackTrace();
			JsfUtil.addErrorMessage("Error!");
		}
	}
	public String cancel()
	{
		return"account";
	}

	public boolean hasRole(String name)
	{
		if (roles != null) {
			for (RoleDTO role : roles) {
				if (role.getRoleName().equalsIgnoreCase(name)) {
					return true;
				}
			}
		}
		return false;
	}
	
	public boolean hasAnyRole(String role1,String role2)
	{
		if (roles != null) {
			for (RoleDTO role : roles) {
				if (role.getRoleName().equalsIgnoreCase(role1)||role.getRoleName().equalsIgnoreCase(role2)) {
					return true;
				}
			}
		}
		return false;
	}

	public UserDTO getCurrentUser()
	{
		return currentUser;
	}

	public void setCurrentUser(UserDTO currentUser)
	{
		this.currentUser = currentUser;
	}

	public List<RoleDTO> getRoles()
	{
		return roles;
	}

	public void setRoles(List<RoleDTO> roles)
	{
		this.roles = roles;
	}
	
	public void setUserService(IUserService userService) {
		this.userService = userService;
	}
	public String getCurrentPassword() {
		return currentPassword;
	}

	public void setCurrentPassword(String currentPassword) {
		this.currentPassword = currentPassword;
	}

	public String getNewPassword() {
		return newPassword;
	}

	public void setNewPassword(String newPassword) {
		this.newPassword = newPassword;
	}

	public String getReNewPassword() {
		return reNewPassword;
	}

	public void setReNewPassword(String reNewPassword) {
		this.reNewPassword = reNewPassword;
	}


}
