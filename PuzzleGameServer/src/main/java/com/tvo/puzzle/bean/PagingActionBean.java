package com.tvo.puzzle.bean;

import java.io.Serializable;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;

@ManagedBean(name = "pagingActionBean")
@RequestScoped
public class PagingActionBean implements Serializable
{

	/**
	 * 
	 */
	private static final long serialVersionUID = -5174571650241935171L;

	private Object findBean(String beanName)
	{
		FacesContext context = FacesContext.getCurrentInstance();
		return context.getApplication().evaluateExpressionGet(context, "#{" + beanName + "}", Object.class);
	}
	
	public void changePage(String beanName)
	{
		BaseBean baseBean = (BaseBean) findBean(beanName);
		baseBean.changePage();
		baseBean.doSearch();
	}
}
