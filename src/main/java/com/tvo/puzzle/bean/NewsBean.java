package com.tvo.puzzle.bean;

import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;
import javax.faces.event.ValueChangeEvent;

import com.tvo.puzzle.dto.NewsDTO;
import com.tvo.puzzle.service.INewsService;
import com.tvo.puzzle.util.PagingUtil;

@ManagedBean(name = "newsBean")
@SessionScoped
public class NewsBean extends BaseBean {
	private static final long serialVersionUID = 1L;
	private String searchKeyword;
	private String type;
	private String newType;
	private NewsDTO newNewsDTO;
	List<NewsDTO> listNewsDTO;	
	@ManagedProperty(name = "newsService", value = "#{newsService}")
	INewsService newsService;	
	
	public NewsDTO getNewNewsDTO() {
		return newNewsDTO;
	}
	public void setNewNewsDTO(NewsDTO newNewsDTO) {
		this.newNewsDTO = newNewsDTO;
	}
	public String getNewType() {
		return newType;
	}
	public void setNewType(String newType) {
		this.newType = newType;
	}
	public INewsService getNewsService() {
		return newsService;
	}
	public void setNewsService(INewsService newsService) {
		this.newsService = newsService;
	}
	public List<NewsDTO> getListNewsDTO() {
		return listNewsDTO;
	}
	public void setListNewsDTO(List<NewsDTO> listNewsDTO) {
		this.listNewsDTO = listNewsDTO;
	}
	
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String getSearchKeyword() {
		return searchKeyword;
	}
	public void setSearchKeyword(String searchKeyword) {
		this.searchKeyword = searchKeyword;
	}
	
	public String doSearch()
	{
		doSetPaging();
		return "news";
	}
	
	public void cbbNewsTypeChange(ValueChangeEvent event) {
		type = event.getNewValue().toString();
		doSetPaging();
	}
	
	public void initNewsScreen()
	{
		searchKeyword="";
		type = "";
		doSetPaging();
		redirect(getExternalContext().getRequestContextPath()
				+ "/pages/admin/news/news.xhtml");
	}
	
	public void add(){
		
	}
	
	public void delete()
	{
		
	}
	
	public void update()
	{}
	
	public void edit(){}
	
	public void reset(){}
	
	public String cancel(){
		return "news";
	}
	
	public void createNewsItem()
	{}
	
	public void doSetPaging(){
		totalRecords = newsService.countNewsByKeywordAndType(searchKeyword, type);
		if(totalRecords==0)
		{
			listNewsDTO = null;
		}
		else
		{
			offset = PagingUtil.getOffset(getCurrentPage(), limit, totalRecords);
			listNewsDTO = newsService.findRangeNewsByKeywordAndType(searchKeyword, type, limit, offset);
		}
	}
}
