package com.tvo.puzzle.bean;

import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;
import javax.faces.event.ValueChangeEvent;
import javax.imageio.ImageIO;

import org.primefaces.event.DateSelectEvent;
import org.primefaces.event.FileUploadEvent;
import org.primefaces.model.DefaultStreamedContent;
import org.primefaces.model.StreamedContent;
import org.primefaces.model.UploadedFile;

import com.tvo.puzzle.entity.Game;
import com.tvo.puzzle.model.GameModel;
import com.tvo.puzzle.service.IGameService;
import com.tvo.puzzle.util.Constants;
import com.tvo.puzzle.util.FileUtils;
import com.tvo.puzzle.util.JsfUtil;
import com.tvo.puzzle.util.PagingUtil;
import com.tvo.puzzle.util.PropertiesUtil;

@ManagedBean(name = "gameBean", eager = true)
@SessionScoped
public class GameBean extends BaseBean {

	private static final long serialVersionUID = -6344518936342291838L;

	@ManagedProperty(name = "gameService", value = "#{gameService}")
	private IGameService gameService;
	private List<Game> gameList;
	private List<GameModel> detailList;
	private Integer selectedGame = 1;
	private UploadedFile file;
	private GameModel gameModels[] = new GameModel[3];
	private UploadedFile[] xmlFiles = new UploadedFile[3];
	private UploadedFile[] imgFiles = new UploadedFile[6];
	private boolean updateXml[] = new boolean[3];
	private boolean updateImg[] = new boolean[6];
	private String xml[] = new String[3];
	private String xmlErr[] = new String[3];
	private String imgMsg[] = new String[3];
	private String imgErr[] = new String[3];
	private byte[][] imageByteList = new byte[6][];
	private String imageTypes[] = new String[6];
	private StreamedContent[] imageDatas = new DefaultStreamedContent[6];
	private String destination = PropertiesUtil
			.get(Constants.UPLOAD_SCREENPLAY);
	private Date date = new Date();
	private GameModel gameModel;
	private String error;

	public void handleDateSelect(DateSelectEvent event) {
		date = event.getDate();
	}

	public void uploadImgHandle1(FileUploadEvent event) {
		handleImage(event, 0);
	}

	public void uploadImgHandle2(FileUploadEvent event) {
		handleImage(event, 1);
	}

	public void uploadImgHandle3(FileUploadEvent event) {
		handleImage(event, 2);
	}

	public void uploadImgHandle4(FileUploadEvent event) {
		handleImage(event, 3);
	}

	public void uploadImgHandle5(FileUploadEvent event) {
		handleImage(event, 4);
	}

	public void uploadImgHandle6(FileUploadEvent event) {
		handleImage(event, 5);
	}

	public void uploadXMLHandle1(FileUploadEvent event) {
		uploadXMLHandle(event, 0);
	}

	public void uploadXMLHandle2(FileUploadEvent event) {
		uploadXMLHandle(event, 1);
	}

	public void uploadXMLHandle3(FileUploadEvent event) {
		uploadXMLHandle(event, 2);
	}

	public void handleImage(FileUploadEvent event, int order) {
		try {
			for (int i = 0; i < 6; i++) {
				if (i != order
						&& imgFiles[i] != null
						&& imgFiles[i].getFileName().equalsIgnoreCase(
								event.getFile().getFileName())) {
					imgErr[order / 2] = "Image " + imgFiles[i].getFileName()
							+ " existed";
					return;
				}
			}
			if (!FileUtils.checkFileType(event.getFile().getFileName(),
					Constants.PICTURE_FORMAT)) {
				JsfUtil.addErrorMessage("globalMessage",
						"Not supported file type.");
				return;
			}
			imageTypes[order] = getImageType(event.getFile().getContentType());

			if (imageTypes[order].equals("invalid")) {
				imgErr[order / 2] = "Invalid image";
				return;
			}

			imageDatas[order] = new DefaultStreamedContent(event.getFile()
					.getInputstream(), "image/" + imageTypes[order]);
			imageByteList[order] = event.getFile().getContents();
			imgFiles[order] = event.getFile();
			updateImg[order] = true;
			imgErr[order / 2] = null;
		} catch (Exception e) {
			imgErr[order / 2] = "Error in getting image";
			return;
		}
	}

	public String getImageType(String input) {
		if (Constants.FILE_GIF.equalsIgnoreCase(input)) {
			return "gif";
		} else if (Constants.FILE_JPG.equalsIgnoreCase(input)) {
			return "jpg";
		} else if (Constants.FILE_PNG.equalsIgnoreCase(input)) {
			return "png";
		}
		return "invalid";
	}

	public String prepareCreate() {
		clearFile();
		return "create";
	}

	public boolean checkXml(FileUploadEvent event, int order) {
		if (!event.getFile().getFileName().toLowerCase().endsWith(".xml")) {
			xmlErr[order] = event.getFile().getFileName()
					+ " is a invalid file. ";
			return false;
		}
		for (int i = 0; i < 3; i++) {
			if (i != order && xml[i] != null
					&& xml[i].equalsIgnoreCase(event.getFile().getFileName())) {
				xmlErr[order] = "File " + xml[i] + " existed!";
				return false;
			}
		}
		return true;
	}

	public void uploadXMLHandle(FileUploadEvent event, int order) {
		if (!checkXml(event, order)) {
			return;
		}
		xmlFiles[order] = event.getFile();
		xml[order] = xmlFiles[order].getFileName();
		updateXml[order] = true;
		xmlErr[order] = null;
	}

	public boolean isValidData() {
		int countXml = gameService.countScreenByDay(selectedGame, date);
		for (int i = 0; i < xml.length; i++) {
			if (xml[i] == null) {
				error = "Error! You must upload enough 3 file xml";
				return false;
			}
		}
		if (isFindDiff(selectedGame) ){
			for (int i = 0; i < imageByteList.length; i++) {
				if(imageByteList[i] == null){
					error = "Error! You must upload more image!";
					return false;
				}
			}
		} else if (countXml >= 3) {
			error = "Error! You uploaded enough 3 level this date!";
			return false;
		}
		SimpleDateFormat format = new SimpleDateFormat("dd-MM-yyyy");
		try {
			Date today = format.parse(format.format(new Date()));
			if (date.before(today)) {
				error = "Error date! You must choose today or later!";
				return false;
			}
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return true;
	}

	 
	public void saveImage(InputStream uploadedInputStream,
			String uploadedFileLocation, int order) {
		try {
			File dirJpg = new File(uploadedFileLocation + ".jpg");
			File dirPng = new File(uploadedFileLocation + ".png");
			File dirGif = new File(uploadedFileLocation + ".gif");
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
			ImageIO.write(originalImage, imageTypes[order], new File(
					uploadedFileLocation + "." + imageTypes[order]));
		} catch (IOException e) {

			e.printStackTrace();
		}
	}

	public String uploadScreen() {
		if (!isValidData()) {
			return "";
		} else {
			try {
				String folder = selectedGame + "/"
						+ new SimpleDateFormat("ddMMyyyy").format(date);
				String imgLocation1, imgLocation2;
				InputStream in;
				GameModel gm;
				for (int i = 0; i < 3; i++) {
					saveFile(xmlFiles[i].getFileName(), folder,
							xmlFiles[i].getInputstream());
					gm = createModel("", xmlFiles[i].getFileName());
					if (isFindDiff(selectedGame)) {
						String str1[] = imgFiles[i * 2].getFileName().split(
								"\\.");
						String str2[] = imgFiles[i * 2 + 1].getFileName()
								.split("\\.");
						imgLocation1 = folder
								+ "/"
								+ imgFiles[i * 2].getFileName().replaceAll(
										"." + str1[str1.length - 1], "");
						imgLocation2 = folder
								+ "/"
								+ imgFiles[i * 2 + 1].getFileName().replaceAll(
										"." + str2[str1.length - 1], "");
						in = new ByteArrayInputStream(imageByteList[i * 2]);
						saveImage(in, destination + imgLocation1, i * 2);
						in = new ByteArrayInputStream(imageByteList[i * 2 + 1]);
						saveImage(in, destination + imgLocation2, i * 2 + 1);
						gm.setImage(imgFiles[i * 2].getFileName() + ";"
								+ imgFiles[i * 2 + 1].getFileName());
					}
					gameService.saveGameDetail(gm, selectedGame);
				}
				return "screenplays";
			} catch (Exception e) {
				e.printStackTrace();
				JsfUtil.addErrorMessage("globalMessage", "Cannot save file");
				clearFile();
			} finally {
				clearFile();
			}
		}
		return "screenplays";
	}

	public void moveFile(String oldUrl,String newUrl,String fileName){
		File file = new File(destination+oldUrl+"/"+fileName);
		File destFolder = new File(destination+newUrl);
		if(!destFolder.exists())
			destFolder.mkdirs();
		file.renameTo(new File(destination+newUrl+"/"+fileName));
	}
	public boolean isUpdatable(){
		SimpleDateFormat format = new SimpleDateFormat("dd-MM-yyyy");
		try {
			Date today = format.parse(format.format(new Date()));
			if (gameModels[0].getDate().before(today)) {
				return false;
			}
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return true;
	}
	
	public String updateScreen() {
		if(!isValidData()){
			return "";
		}
		String oldUrl = gameModels[0].getGame().getId()+"/"+gameModels[0].getDateStr();
		int oldType = gameModels[0].getGame().getId();
		String newUrl = selectedGame+"/"+ new SimpleDateFormat("ddMMyyyy").format(date);
		InputStream in;
		for (int i = 0; i < 3; i++) {
			String[] imgStr = gameModels[i].getImage().split(";");
			String oldImgStr = gameModels[i].getImage();
			if(updateXml[i]){
				File oldFile = new File(destination+oldUrl+"/"+gameModels[i].getResource());
				if(oldFile.exists())
					oldFile.delete();
				gameModels[i].setResource(xml[i]);
				try {
					saveFile(xml[i], newUrl, xmlFiles[i].getInputstream());
				} catch (Exception e) {
					e.printStackTrace();
					return "";
				}
			}else{
				if(!oldUrl.equalsIgnoreCase(newUrl)){
					moveFile(oldUrl, newUrl, gameModels[i].getResource());
				}		
			}
			if(isFindDiff(selectedGame)){
				for(int j= 0; j<=1; j++){
					String img = imgStr[j];
					if(updateImg[i*2+j]){
						//delete old file
						File oldFile = new File(destination+oldUrl+"/"+img);
						if(oldFile.exists())
							oldFile.delete();
						
						//initiate new file
						String str[] = imgFiles[i * 2+j].getFileName().split("\\.");
						String imgLocation = newUrl+ "/"+ imgFiles[i*2+j].getFileName().replaceAll(
										"." + str[str.length - 1], "");
						in = new ByteArrayInputStream(imageByteList[i * 2 + j]);
						saveImage(in, destination + imgLocation, i * 2 + j);	
						String newImgStr =oldImgStr.replaceAll(img, imgFiles[i*2+j].getFileName());
						gameModels[i].setImage(newImgStr);
					}else{
						if(!oldUrl.equalsIgnoreCase(newUrl)){
							moveFile(oldUrl, newUrl,img);
						}
					}
				}
			}else if(isFindDiff(oldType)){
				for(int j= 0; j<=1; j++){
					String img = imgStr[j];
					File oldFile = new File(destination+oldUrl+"/"+img);
					if(oldFile.exists())
						oldFile.delete();
					gameModels[i].setImage("");
				}
			}	
			gameModels[i].setDate(date);
			gameService.saveGameDetail(gameModels[i], selectedGame);
		}
		clearFile();
		return "screenplays";
	}

	public void editXml() {
		if (!"".equals(file.getFileName())
				|| file.getFileName().endsWith(".xml")) {
			String folder = gameModel.getGame().getId()
					+ "/"
					+ new SimpleDateFormat("ddMMyyyy").format(gameModel
							.getDate());
			File oldFile = new File(destination + folder + "/"
					+ gameModel.getResource());
			oldFile.delete();
			gameModel.setResource(file.getFileName());
			try {
				saveFile(file.getFileName(), folder, file.getInputstream());
				gameService.saveGameDetail(gameModel);
				oldFile.delete();
			} catch (IOException e) {
				e.printStackTrace();
			}
			gameService.saveScreenXml(file.getFileName(), selectedGame, date);
		} else {
			error = "no file was choosen or invalid file type";
		}
	}

	public GameModel createModel(String image, String xml) {
		GameModel gm = new GameModel();
		gm.setDate(date);
		gm.setImage(image);
		gm.setResource(xml);
		return gm;

	}

	public void clearFile() {
		date = new Date();
		error = null;
		for (int i = 0; i < 3; i++) {
			xmlFiles[i] = null;
			imgMsg[i] = "";
			imgErr[i] = null;
			xml[i] = null;
			xmlErr[i] = null;
			updateXml[i] = false;
		}
		for (int i = 0; i < 6; i++) {
			imageByteList[i] = null;
			imgFiles[i] = null;
			imageDatas[i] = null;
			updateImg[i] = false;
		}
	}

	public String prepare() {
		String folder = getParameter("dateSelected");
		List<GameModel> modelList = gameService.getGameDetail(folder,
				selectedGame);
		date = modelList.get(0).getDate();
		for (int i = 0; i < 3; i++) {
			gameModels[i] = modelList.get(i);
			xml[i] = modelList.get(i).getResource();
		}
		if (isFindDiff(selectedGame)) {
			for (int i = 0; i < 3; i++) {
				if(modelList.get(i)!= null){
					getImage(modelList.get(i), i);
				}
			}
		}
		return "update";
	}

	public void getImage(GameModel g, int order) {
		String[] imgStr = g.getImage().split(";");
		String url = destination + selectedGame + "/" + g.getDateStr() + "/";
		String img1 = imgStr[0];
		String img2 = imgStr[1];
		File dir1 = new File(url + img1);
		File dir2 = new File(url + img2);
		if (dir1.exists()) {
			imageTypes[order * 2] = getTypeOfImageByUrl(img1);
			imageByteList[order * 2] = FileUtils.download(url + img1);
			if (imageByteList[order * 2] != null) {
				imageDatas[order * 2] = new DefaultStreamedContent(
						new ByteArrayInputStream(imageByteList[order * 2]),
						"image/" + imageTypes[order * 2]);
			}
		}
		if (dir2.exists()) {
			imageTypes[order * 2 + 1] = getTypeOfImageByUrl(img2);
			imageByteList[order * 2 + 1] = FileUtils.download(url + img2);
			if (imageByteList[order * 2 + 1] != null) {
				imageDatas[order * 2 + 1] = new DefaultStreamedContent(
						new ByteArrayInputStream(imageByteList[order * 2 + 1]),
						"image/" + imageTypes[order * 2 + 1]);
			}
		}
	}

	public boolean isFindDiff(int i) {
		return (i == 3);
	}

	public void saveFile(String fileName, String folder, InputStream in) {
		File file = new File(destination + folder);
		if (!file.exists()) {
			if (file.mkdirs()) {
				JsfUtil.addSuccessMessage("Directory is created!");
			} else {
				JsfUtil.addErrorMessage("globalMessage", "Cannot save file");
			}
		}
		try {
			OutputStream out = new FileOutputStream(new File(destination
					+ folder + "/" + fileName));
			int read = 0;
			byte[] bytes = new byte[1024];

			while ((read = in.read(bytes)) != -1) {
				out.write(bytes, 0, read);
			}
			in.close();
			out.flush();
			out.close();
		} catch (IOException e) {
			System.out.println(e.getMessage());
		}
	}

	public String doSearch() {
		gameList = gameService.listGame();
		totalRecords = gameService.countAll(selectedGame);
		if (totalRecords == 0) {
			detailList = null;
			JsfUtil.addWarningString("messagePanel", "No record found.");
		} else {
			offset = PagingUtil
					.getOffset(getCurrentPage(), limit, totalRecords);
			detailList = gameService.getAll(selectedGame, limit, offset);
		}
		return "screenplays";
	}

	public void changeGame(ValueChangeEvent event) {
		selectedGame = (Integer) event.getNewValue();
		doSearch();
	}

	public void changeCreateGame(ValueChangeEvent event) {
		selectedGame = (Integer) event.getNewValue();
	}

	public IGameService getGameService() {
		return gameService;
	}

	public void setGameService(IGameService gameService) {
		this.gameService = gameService;
	}

	public List<Game> getGameList() {
		return gameList;
	}

	public void setGameList(List<Game> gameList) {
		this.gameList = gameList;
	}

	public List<GameModel> getDetailList() {
		return detailList;
	}

	public void setDetailList(List<GameModel> detailList) {
		this.detailList = detailList;
	}

	public Integer getSelectedGame() {
		return selectedGame;
	}

	public void setSelectedGame(Integer selectedGame) {
		this.selectedGame = selectedGame;
	}

	public UploadedFile getFile() {
		return file;
	}

	public void setFile(UploadedFile file) {
		this.file = file;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public GameModel getGameModel() {
		return gameModel;
	}

	public void setGameModel(GameModel gameModel) {
		this.gameModel = gameModel;
	}

	public UploadedFile[] getXmlFiles() {
		return xmlFiles;
	}

	public void setXmlFiles(UploadedFile[] xmlFiles) {
		this.xmlFiles = xmlFiles;
	}

	public String[] getXml() {
		return xml;
	}

	public void setXml(String xml[]) {
		this.xml = xml;
	}

	public String[] getImgMsg() {
		return imgMsg;
	}

	public void setImgMsg(String imgMsg[]) {
		this.imgMsg = imgMsg;
	}

	public String[] getImgErr() {
		return imgErr;
	}

	public void setImgErr(String imgErr[]) {
		this.imgErr = imgErr;
	}

	public UploadedFile[] getImgFiles() {
		return imgFiles;
	}

	public void setImgFiles(UploadedFile[] imgFiles) {
		this.imgFiles = imgFiles;
	}

	public String[] getXmlErr() {
		return xmlErr;
	}

	public void setXmlErr(String xmlErr[]) {
		this.xmlErr = xmlErr;
	}

	public StreamedContent[] getImageDatas() {
		for (int i = 0; i < 6; i++) {
			if (imageByteList[i] != null) {
				imageDatas[i] = new DefaultStreamedContent(
						new ByteArrayInputStream(imageByteList[i]), "image/"
								+ imageTypes[i]);
			} else {
				imageDatas[i] = null;
			}
		}
		return imageDatas;
	}

	public void setImageDatas(StreamedContent[] imageDatas) {
		this.imageDatas = imageDatas;
	}

	public String getError() {
		return error;
	}

	public void setError(String error) {
		this.error = error;
	}

	public boolean[] getUpdateXml() {
		return updateXml;
	}

	public void setUpdateXml(boolean updateXml[]) {
		this.updateXml = updateXml;
	}

	public boolean[] getUpdateImg() {
		return updateImg;
	}

	public void setUpdateImg(boolean updateImg[]) {
		this.updateImg = updateImg;
	}

	public GameModel[] getGameModels() {
		return gameModels;
	}

	public void setGameModels(GameModel gameModels[]) {
		this.gameModels = gameModels;
	}

}
