package com.tvo.puzzle.bean;

import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;
import javax.faces.event.ValueChangeEvent;

import com.tvo.puzzle.dto.PresentManagementDTO;
import com.tvo.puzzle.dto.ProductDTO;
import com.tvo.puzzle.dto.ProjectDTO;
import com.tvo.puzzle.model.PresentManagementModel;
import com.tvo.puzzle.service.IPresentManagementService;
import com.tvo.puzzle.service.IProductService;
import com.tvo.puzzle.service.IProjectService;
import com.tvo.puzzle.util.PagingUtil;

@ManagedBean(name = "presentBean")
@SessionScoped
public class PresentManagementBean extends BaseBean {
	private static final long serialVersionUID = 1L;

	@ManagedProperty(name = "presentManagementService", value = "#{presentManagementService}")
	IPresentManagementService presentManagementService;

	@ManagedProperty(name = "productService", value = "#{productService}")
	IProductService productService;
	
	@ManagedProperty(name = "projectService", value = "#{projectService}")
	IProjectService projectService;

	List<PresentManagementDTO> listPMDTO;
	List<ProductDTO> listProductDTO;
	List<ProjectDTO> listProjectDTO;
	private PresentManagementDTO pmDTO;
	private Integer selectedProductId;
	private Integer selectedProjectId;
	private String searchKeyword;

	public List<PresentManagementDTO> getListPMDTO() {
		return listPMDTO;
	}

	public void setListPMDTO(List<PresentManagementDTO> listPMDTO) {
		this.listPMDTO = listPMDTO;
	}

	public IPresentManagementService getPresentManagementService() {
		return presentManagementService;
	}

	public void setPresentManagementService(
			IPresentManagementService presentManagementService) {
		this.presentManagementService = presentManagementService;
	}

	public PresentManagementDTO getPmDTO() {
		return pmDTO;
	}

	public void setPmDTO(PresentManagementDTO pmDTO) {
		this.pmDTO = pmDTO;
	}

	public Integer getSelectedProductId() {
		return selectedProductId;
	}

	public void setSelectedProductId(Integer selectedProductId) {
		this.selectedProductId = selectedProductId;
	}

	public String getSearchKeyword() {
		return searchKeyword;
	}

	public void setSearchKeyword(String searchKeyword) {
		this.searchKeyword = searchKeyword;
	}

	public List<ProductDTO> getListProductDTO() {
		return listProductDTO;
	}

	public void setListProductDTO(List<ProductDTO> listProductDTO) {
		this.listProductDTO = listProductDTO;
	}

	public IProjectService getProjectService() {
		return projectService;
	}

	public void setProjectService(IProjectService projectService) {
		this.projectService = projectService;
	}

	public IProductService getProductService() {
		return productService;
	}

	public void setProductService(IProductService productService) {
		this.productService = productService;
	}	
	
	public Integer getSelectedProjectId() {
		return selectedProjectId;
	}

	public void setSelectedProjectId(Integer selectedProjectId) {
		this.selectedProjectId = selectedProjectId;
	}

	public List<ProjectDTO> getListProjectDTO() {
		return listProjectDTO;
	}

	public void setListProjectDTO(List<ProjectDTO> listProjectDTO) {
		this.listProjectDTO = listProjectDTO;
	}

	public void initPresentScreen()
	{
		searchKeyword="";
		selectedProductId = 0;
		selectedProjectId = 1;
		listProductDTO = productService.getAll();
		listProjectDTO = projectService.getAll();
		doSetPaging();
		redirect(getExternalContext().getRequestContextPath()
				+ "/pages/admin/gift/gift.xhtml");
	}

	public void doSetPaging() {
		totalRecords = presentManagementService.countPMByKeywordAndProduct(
				searchKeyword, selectedProductId,selectedProjectId);
		if (totalRecords == 0) {
			listPMDTO = null;
		} else {
			offset = PagingUtil
					.getOffset(getCurrentPage(), limit, totalRecords);
			listPMDTO = presentManagementService.findRangePMByKeywordAndProduct(searchKeyword,
							selectedProductId,selectedProjectId, limit, offset);
		}
	}

	public void cbbProductChange(ValueChangeEvent event) {
		selectedProductId = (Integer) event.getNewValue();
		doSetPaging();
	}
	
	public void cbbProjectChange(ValueChangeEvent event)
	{
		selectedProjectId = (Integer) event.getNewValue();
		doSetPaging();
	}
	
	public String doSearch()
	{
		doSetPaging();
		return "presentmanagement";
	}

	public void genCSVFile() {
		presentManagementService.getCVSFile(listPMDTO,"test.csv");
	}

}
