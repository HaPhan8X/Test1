package com.tvo.puzzle.bean;
import java.io.Serializable;
import java.util.Arrays;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.RequestScoped;

import com.tvo.puzzle.application.AuthenticationService;
import com.tvo.puzzle.dto.RoleDTO;
import com.tvo.puzzle.dto.UserDTO;
import com.tvo.puzzle.service.IUserService;
import com.tvo.puzzle.util.JsfUtil;

@ManagedBean(name = "loginBean")
@RequestScoped
public class LoginBean implements Serializable
{
	private static final long serialVersionUID = 1L;
	
	private static final String[] WEB_ACCESS_ROLES = {"Super Administrator", "Administrator"};

	private String username;
	private String password;

	@ManagedProperty(name = "authenticationService", value = "#{authenticationService}")
	private AuthenticationService authenticationService;
	@ManagedProperty(name = "userService", value = "#{userService}")
	private IUserService userService;

	@ManagedProperty(name = "userSession", value = "#{userSession}")
	private UserSession userSession;

	public String login()
	{

		boolean success = authenticationService.login(username, password);

		if (success)
		{
			UserDTO userDTO = userService.getUserByUsernameAndPassword(username,password);
			List<RoleDTO> roles = userService.findRoleByUser(userDTO.getId());
			
			if (checkPermission(roles))
			{
				userSession.setCurrentUser(userDTO);
				userSession.setRoles(roles);
				// return to application but being logged now
				return "welcome";
			}
			else
			{
				JsfUtil.addErrorMessage("Access Denied!");
				return "login";
			}
		}
		else
		{
			JsfUtil.addErrorMessage("Wrong login or password");
			return "login";
		}
	}
	
	private boolean checkPermission(List<RoleDTO> roles)
	{
		List<String> accessPerms = Arrays.asList(WEB_ACCESS_ROLES);
		for (RoleDTO role : roles)
		{
			if (accessPerms.contains(role.getRoleName()))
			{
				return true;
			}
		}
		return false;
	}

	public String getUsername()
	{
		return username;
	}

	public void setUsername(String username)
	{
		this.username = username;
	}

	public String getPassword()
	{
		return password;
	}

	public void setPassword(String password)
	{
		this.password = password;
	}

	public void setAuthenticationService(AuthenticationService authenticationService)
	{
		this.authenticationService = authenticationService;
	}
	
	public void setUserService(IUserService userService)
	{
		this.userService = userService;
	}

	public UserSession getUserSession()
	{
		return userSession;
	}

	public void setUserSession(UserSession userSession)
	{
		this.userSession = userSession;
	}

}
