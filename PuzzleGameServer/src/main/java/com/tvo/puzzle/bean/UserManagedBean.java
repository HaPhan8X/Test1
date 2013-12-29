package com.tvo.puzzle.bean;

import java.util.ArrayList;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;

import org.primefaces.context.RequestContext;
import org.springframework.dao.DataIntegrityViolationException;

import com.tvo.puzzle.dto.RoleDTO;
import com.tvo.puzzle.dto.UserDTO;
import com.tvo.puzzle.dto.UserRoleDTO;
import com.tvo.puzzle.exception.DuplicateNameException;
import com.tvo.puzzle.service.IRoleService;
import com.tvo.puzzle.service.IUserRoleService;
import com.tvo.puzzle.service.IUserService;
import com.tvo.puzzle.util.JsfUtil;
import com.tvo.puzzle.util.PagingUtil;
import com.tvo.puzzle.util.PropertiesUtil;

@ManagedBean(name = "userManagedBean")
@SessionScoped
public class UserManagedBean extends BaseBean{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@ManagedProperty(name="userService",value="#{userService}")
	private IUserService userService;
	@ManagedProperty(name="roleService",value="#{roleService}")
	private IRoleService roleService;
	@ManagedProperty(name="userRoleService",value="#{userRoleService}")
	private IUserRoleService userRoleService;
	
	private List<UserDTO> lsUser;
	private Integer roleId;
	private UserDTO currentUser;
	private Integer selectedRole;
	private UserRoleDTO userRole;
	private List<RoleDTO> lsRole;
	
	public String doSearch()
	{
		totalRecords=userService.countUsers();
		offset = PagingUtil.getOffset(currentPage, limit, totalRecords);
		lsUser=userService.findRangerUsers(limit, offset);
		return "user";
	}
	
	private boolean isValidate(){
		if (!validateInput(currentUser.getUserName(),"User Name"))
		{
			return false;
		}
		return true;
	}
	
	public void prepareCreate()
	{
		currentUser=new UserDTO();
		lsRole = roleService.findRoles();
		if(lsRole!=null&&lsRole.size()>0)
		{
			selectedRole=lsRole.get(0).getId();
		}
	}
	public void create()
	{
		if(isValidate())
		{
			try
			{
				currentUser.setRoleId(selectedRole);
				userService.create(currentUser);
				RequestContext context = RequestContext.getCurrentInstance();
				JsfUtil.addSuccessMessage("frmListUser:globalMessage","Create success");
				context.addCallbackParam("createSuccess", true);
				reset();
				doSearch();
			}
			catch (DuplicateNameException e)
			{
				JsfUtil.addErrorMessage("User Name is existed! Please enter other Name");
			}
		}
		
	}
	public void prepareEdit()
	{
		String currUserId = getParameter("currUserId");
		currentUser = userService.findById(Integer.parseInt(currUserId));
		lsRole=roleService.findRoles();
		selectedRole=currentUser.getRoleId();
	}
	public void update()
	{
		if(isValidate())
		{
			try
			{
				currentUser.setRoleId(selectedRole);
				userService.update(currentUser);
				RequestContext context = RequestContext.getCurrentInstance();
				JsfUtil.addSuccessMessage("frmListUser:globalMessage","Update success");
				context.addCallbackParam("createSuccess", true);
				reset();
				doSearch();
			}
			catch (DataIntegrityViolationException e)
			{
				JsfUtil.addErrorMessage("User Name is existed! Please enter other Name");
			}
		}
	}
	
	public void reset()
	{
		currentUser = null;
	}
	
	public String delete()
	{
		List<Integer> deletedIds = new ArrayList<Integer>();
		if(lsUser!=null&&lsUser.size()>0)
		{
			for (UserDTO userDTO : lsUser)
			{
				if (userDTO.getSelect())
				{
					deletedIds.add(userDTO.getId());
				}
			}
		}
		if (deletedIds != null && deletedIds.size() > 0)
		{
			try
			{
				userService.deleteUsersSelect(deletedIds);
				JsfUtil.addSuccessMessage("frmListUser:globalMessage","Delete successful");
			}
			catch (Exception e) {
				JsfUtil.addErrorMessage("frmListUser:globalMessage","Error");
				return "";
			}
		}
		else
		{
			JsfUtil.addErrorMessage("frmListUser:globalMessage","You must choose record delete");
		}
		doSearch();
		return "user";
	}
	
	public String cancel()
	{
		reset();
		doSearch();
		return "user";
	}
	
	public List<UserDTO> getLsUser() {
		return lsUser;
	}
	public void setLsUser(List<UserDTO> lsUser) {
		this.lsUser = lsUser;
	}
	public Integer getRoleId() {
		return roleId;
	}
	public void setRoleId(Integer roleId) {
		this.roleId = roleId;
	}
	public List<RoleDTO> getLsRole() {
		return lsRole;
	}
	public void setLsRole(List<RoleDTO> lsRole) {
		this.lsRole = lsRole;
	}
	public UserDTO getCurrentUser() {
		return currentUser;
	}
	public void setCurrentUser(UserDTO currentUser) {
		this.currentUser = currentUser;
	}
	public void setUserService(IUserService userService) {
		this.userService = userService;
	}

	public void setRoleService(IRoleService roleService) {
		this.roleService = roleService;
	}

	public void setUserRoleService(IUserRoleService userRoleService) {
		this.userRoleService = userRoleService;
	}
	/**
	 * @return the selectedRole
	 */
	public Integer getSelectedRole() {
		return selectedRole;
	}

	/**
	 * @param selectedRole the selectedRole to set
	 */
	public void setSelectedRole(Integer selectedRole) {
		this.selectedRole = selectedRole;
	}
	public UserRoleDTO getUserRole() {
		return userRole;
	}
	public void setUserRole(UserRoleDTO userRole) {
		this.userRole = userRole;
	}
}
