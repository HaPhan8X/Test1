package com.tvo.puzzle.bean;

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
	private UploadedFile[] xmlFiles = new UploadedFile[3];
	private UploadedFile[] imgFiles = new UploadedFile[6];
	private String xml[] = new String[3];
	private String xmlErr[] = new String[3];
	private String imgMsg[] = new String[3];
	private String imgErr[] = new String[3];
	private byte[][] imageByteList = new byte[6][];
	private String imageTypes[] = new String[6];
	private StreamedContent[] imageDatas = new StreamedContent[6];
	private String destination = PropertiesUtil
			.get(Constants.UPLOAD_SCREENPLAY);
	private Date date = new Date();
	private GameModel gameModel;
	private String gameDetailId;
	private String error;

	public void handleDateSelect(DateSelectEvent event) {
		SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");  
		date = event.getDate();
		System.out.println("|!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!"+format.format(date));
		
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
			if (i != order
					&& xml[i] != null
					&& xml[i].equalsIgnoreCase(
							event.getFile().getFileName())) {
				xmlErr[order] = "File "+xml[i]+" existed!";	
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
		xmlErr[order] = null;
	}

	public boolean isValidData() {
		int countXml = gameService.countScreenByDay(selectedGame, date);
		if (xmlFiles[2] == null) {
			error = "Error! You must upload enough 3 file xml";
			return false;
		} else if (isFindDiff(selectedGame) && imgFiles[5] == null) {
			error = "Error! You must upload more image!";
			return false;
		} else if (countXml >= 3) {
			error = "Error! You uploaded enough 3 screen this date!";
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

	public void handleFileUpload(FileUploadEvent event) {
		gameModel = gameService.getGameDetail(Integer.parseInt(gameDetailId));
		if (!FileUtils.checkFileType(event.getFile().getFileName(),
				Constants.PICTURE_FORMAT)) {
			JsfUtil.addErrorMessage("globalMessage", "Not supported file type.");
			return;
		}
		getTypeOfImage(event.getFile().getContentType());
		imageByte = event.getFile().getContents();
		String folder = selectedGame + "/"
				+ new SimpleDateFormat("ddMMyyyy").format(date) + "/";
		String img = folder
				+ event.getFile().getFileName().replaceAll("." + imageType, "");
		String imgStr = gameModel.getImage();
		if (imgStr != null) {
			gameModel.setImage(gameModel.getImage() + ";"
					+ event.getFile().getFileName());
		} else {
			gameModel.setImage(event.getFile().getFileName());
		}
		InputStream in = new ByteArrayInputStream(imageByte);
		saveImage(in, destination + img);
		gameService.saveGameDetail(gameModel);
		doSearch();
	}

	public String uploadScreen() {
		if (!isValidData()) {
			return "create";
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
						saveImage(in, destination + imgLocation1);
						in = new ByteArrayInputStream(imageByteList[i * 2 + 1]);
						saveImage(in, destination + imgLocation2);
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

	public GameModel createModel(String image, String xml) {
		GameModel gm = new GameModel();
		gm.setDate(date);
		gm.setImage(image);
		gm.setResource(xml);
		return gm;

	}

	public void clearFile() {

		error = null;
		for (int i = 0; i < 3; i++) {
			xmlFiles[i] = null;
			imgMsg[i] = "";
			imgErr[i] = null;
			xml[i] = null;
			xmlErr[i] = null;
		}
		for (int i = 0; i < 6; i++) {
			imageByteList[i] = null;
			imgFiles[i] = null;
			imageDatas[i] = null;
		}
	}

	public void prepare() {
		gameDetailId = getParameter("gameDetailId");
		gameModel = gameService.getGameDetail(Integer.parseInt(gameDetailId));
	}

	/*
	 * public void editXml() { if (!"".equals(file.getFileName()) ||
	 * file.getFileName().endsWith(".xml")) { String folder =
	 * gameModel.getGame().getId() + "/" + new
	 * SimpleDateFormat("ddMMyyyy").format(gameModel .getDate()); File oldFile =
	 * new File(destination + folder + "/" + gameModel.getResource());
	 * oldFile.delete(); gameModel.setResource(file.getFileName()); try {
	 * saveFile(file.getFileName(), folder, file.getInputstream());
	 * gameService.saveGameDetail(gameModel); oldFile.delete(); } catch
	 * (IOException e) { e.printStackTrace(); }
	 * gameService.saveScreenXml(file.getFileName(), selectedGame, date); } else
	 * { error = "no file was choosen or invalid file type"; } }
	 */

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

}
