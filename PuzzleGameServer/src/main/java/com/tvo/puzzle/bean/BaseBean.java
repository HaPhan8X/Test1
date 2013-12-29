package com.tvo.puzzle.bean;

import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.Serializable;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.imageio.ImageIO;

import org.primefaces.context.RequestContext;
import org.primefaces.event.FileUploadEvent;
import org.primefaces.model.DefaultStreamedContent;
import org.primefaces.model.StreamedContent;

import com.tvo.puzzle.util.CommonUtils;
import com.tvo.puzzle.util.Constants;
import com.tvo.puzzle.util.FileUtils;
import com.tvo.puzzle.util.JsfUtil;

@ManagedBean(name = "baseBean")
@SessionScoped
public class BaseBean implements Serializable 
{
	/**
	 * 
	 */
	private static final long serialVersionUID = -7503542636887717191L;
	protected Integer totalRecords;
	protected Integer limit = Constants.Pager.RECORDS_PER_PAGE;
	protected Integer offset;
	protected Integer currentPage = 1;
	protected byte[] imageByte;
	protected StreamedContent imageData;
	protected String imageType;
	
	public String getImageType() {
		return imageType;
	}

	public void setImageType(String imageType) {
		this.imageType = imageType;
	}

	public byte[] getImageByte() {
		return imageByte;
	}

	public void setImageByte(byte[] imageByte) {
		this.imageByte = imageByte;
	}

	public StreamedContent getImageData()
	{
		if(imageByte!=null)
		{
			imageData =  new DefaultStreamedContent(
					new ByteArrayInputStream(imageByte), "image/"+imageType);
		}
		else
		{
			imageData = null;
		}
		return imageData;
	}

	public void setImageData(StreamedContent imageData) {
		this.imageData = imageData;
	}

	public String doSearch()
	{
		return null;
	}
	
	public void changePage()
	{
		String cPage = getParameter("currentPage");
		this.currentPage = cPage != null ? Integer.valueOf(cPage) : 1;
	}
	
	public String getParameter(String name)
	{
		return getExternalContext().getRequestParameterMap().get(name);
	}
	
	public Integer getEndPage()
	{
		if (this.totalRecords != null && this.limit != null)
		{
			return CommonUtils.getEndPage(this.totalRecords, limit);
		}
		else
		{
			return 1;
		}
	}
	
	public void handleFileUpload(FileUploadEvent event) 
	{
		try
		{
			if (!FileUtils.checkFileType(event.getFile().getFileName(), Constants.PICTURE_FORMAT)) 
			{
				JsfUtil.addErrorMessage("globalMessage","Not supported file type.");
				return;
			} 
			getTypeOfImage(event.getFile().getContentType());
			imageByte = event.getFile().getContents();
			imageData = new DefaultStreamedContent(
					event.getFile().getInputstream(), "image/" +imageType);
		} 
		catch (Exception e) 
		{
			e.printStackTrace();
		}
	}
	
	public void getTypeOfImage(String inputType)
	{
		if(Constants.FILE_GIF.equals(inputType))
		{
			imageType = "gif";
		}
		else if(Constants.FILE_JPG.equals(inputType))
		{
			imageType = "jpg";
		}
		else if(Constants.FILE_PNG.equals(inputType))
		{
			imageType = "png";
		}
	}
	
	public String getTypeOfImageByUrl(String fileName)
	
	{
		String type = "";
		if(FileUtils.checkFileType(fileName, "jpg"))
		{
			type= "jpg";
		}
		else if(FileUtils.checkFileType(fileName, "png"))
		{
			type= "png";
		}
		else if(FileUtils.checkFileType(fileName, "gif"))
		{
			type= "gif";
		}
		return type;
	}
	
	public void saveImage(InputStream uploadedInputStream,
			String uploadedFileLocation) {
		try {
			File dirJpg = new File(uploadedFileLocation+".jpg");
			File dirPng = new File(uploadedFileLocation+".png");
			File dirGif = new File(uploadedFileLocation+".gif");
			if (dirJpg.exists()) {
				dirJpg.delete();
			}
			if (dirPng.exists()) {
				dirPng.delete();
			}
			if (dirGif.exists()) {
				dirGif.delete();
			}
			BufferedImage originalImage = ImageIO.read(uploadedInputStream);
			ImageIO.write(originalImage, imageType, new File(uploadedFileLocation+"."+imageType));
		} catch (IOException e) {

			e.printStackTrace();
		}
	}
	
	public ExternalContext getExternalContext()
	{
		return FacesContext.getCurrentInstance().getExternalContext();
	}
	
	public RequestContext getRequestContext()
	{
		return RequestContext.getCurrentInstance();
	}
	
	public void redirect(String page)
	{
		try
		{
			getExternalContext().redirect(page);
		}
		catch (IOException e)
		{
			e.printStackTrace();
		}
	}
	
	public boolean validateInput(Object value, String controlName)
	 {
	  boolean retVal = true;
	  String inputText = (String)value;
	  if(value == null || value.equals(""))
	  {
		  JsfUtil.addErrorMessage(controlName+" is Required");
		  retVal = false;
	  }
	  else
	  {
		  inputText = inputText.trim();
		  if(inputText.isEmpty())
		  {
			  retVal = false;
			  JsfUtil.addErrorMessage(controlName + " is not blank");
		  }
	  }
	  return retVal;
	 }
	
	public boolean validateImage(String controlName)
	{
		if(imageData==null || imageByte==null)
		{
			JsfUtil.addErrorMessage("globalMessage",controlName+" Image is required");
			return false;
		}
		return true;
	}

	public Integer getTotalRecords() {
		return totalRecords;
	}

	public void setTotalRecords(Integer totalRecords) {
		this.totalRecords = totalRecords;
	}

	public Integer getLimit() {
		return limit;
	}

	public void setLimit(Integer limit) {
		this.limit = limit;
	}

	public Integer getOffset() {
		return offset;
	}

	public void setOffset(Integer offset) {
		this.offset = offset;
	}

	public Integer getCurrentPage() {
		return currentPage;
	}

	public void setCurrentPage(Integer currentPage) {
		this.currentPage = currentPage;
	}
}
