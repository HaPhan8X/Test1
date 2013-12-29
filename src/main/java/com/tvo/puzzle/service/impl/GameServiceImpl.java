package com.tvo.puzzle.service.impl;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.tvo.puzzle.dao.GameDao;
import com.tvo.puzzle.dto.GameDTO;
import com.tvo.puzzle.entity.Game;
import com.tvo.puzzle.entity.GameDetail;
import com.tvo.puzzle.model.GameModel;
import com.tvo.puzzle.service.IGameService;

@Service("gameService")
public class GameServiceImpl implements IGameService{

	@Autowired
	GameDao gameDao;
	
	@Transactional
	public List<GameDTO> getGameList() {
		List<Game> games = gameDao.getGameList();
		List<GameDTO> gameDTOs = new ArrayList<GameDTO>();
		
		GameDTO gd =null;
		for(Game g : games){
			gd = entityToDTO(g);
			if(gd != null){
				gameDTOs.add(gd);
			}
		}
		return gameDTOs;
		
	}
	
	public GameDTO entityToDTO(Game game){
		GameDTO g = new GameDTO();
		List<String> resource = new ArrayList<String>();
		g.setId(game.getId());
		g.setDescription(game.getDescription());
		g.setName(game.getName());
		String date = new SimpleDateFormat("ddMMyyyy").format(new Date());
		for(GameDetail gd : game.getTblGameDetails()){
			String folder = gd.getFolderName();
			if(folder.equals(date)){
				resource.add(folder);
			}
		}
		g.setResources(resource);
		return g;
	}

	@Transactional
	public List<Game> listGame() {
		return gameDao.getGameList();
	}

	@Transactional
	public Integer countAll(Integer selectedGame) {
		return gameDao.countAll(selectedGame);
	}

	@Override
	public List<GameModel> getAll(Integer selectedGame,int limit,int offset) {
		List<GameDetail> games = gameDao.getGameDetails(selectedGame,limit,offset);
		List<GameModel> gameModels = new ArrayList<GameModel>();
		for (GameDetail game : games) {
			gameModels.add(convertToModel(game));
		}
		return gameModels;
	}

	private GameModel convertToModel(GameDetail g) {
		GameModel gm = new GameModel();
		gm.setGame(g.getTblGame());
		gm.setId(g.getId());	
		SimpleDateFormat format = new SimpleDateFormat("ddMMyyyy");
		String [] a = g.getResource().split(";");
		Date date;
		try {
			date = format.parse(g.getFolderName());
			gm.setDate(date);
		} catch (ParseException e) {
			System.err.println("Fail parsing date from forder name");
			e.printStackTrace();
		}
		gm.setDateStr(g.getFolderName());
		if(a.length >0)
		gm.setResource(a[0]);
		if(a.length >= 2 && a[1] != null)
			gm.setImage(a[1]);
		if(a.length >= 3 && a[2] != null)
			gm.setImage(a[1]+";"+a[2]);
		return gm;
	}

	@Override
	public List<GameModel> getGameDetail(String folder, Integer selectedGame) {
		List<GameModel> gm = new ArrayList<GameModel>();
		List<GameDetail> gd = gameDao.getListLevelByDate(folder,selectedGame);
		for (GameDetail g : gd){
			gm.add(convertToModel(g));
		}
		return gm;
	}
	
	public GameDetail convertToEntity(GameModel gm){
		GameDetail g = new GameDetail();
		g.setId(gm.getId());
		g.setFolderName(new SimpleDateFormat("ddMMyyyy").format(gm.getDate()));
		g.setResource(gm.getResource()+";"+gm.getImage());
		g.setTblGame(gm.getGame());
		return g;
	}
	
	@Transactional
	public void saveScreenXml(String fileName, Integer selectedGame ,Date date) {
		String folder = new SimpleDateFormat("ddMMyyyy").format(date);
		GameDetail gd = new GameDetail();
		Game g = gameDao.findById(selectedGame);
		gd.setFolderName(folder);
		gd.setResource(fileName+";");
		gd.setTblGame(g);
		gameDao.saveGameDetail(gd);
	}

	@Override
	public int countScreenByDay(Integer selectedGame,Date date) {
		String dateStr = new SimpleDateFormat("ddMMyyyy").format(date);
		return gameDao.countScreenByDate(selectedGame,dateStr);
		
	}

	@Override
	public GameModel getGameDetail(int id) {
		GameModel g = new GameModel();
		GameDetail gd = gameDao.getById(id);
		g= convertToModel(gd);	
		return g;
	}

	@Transactional
	public void saveGameDetail(GameModel gameModel) {
		GameDetail g = convertToEntity(gameModel);
		gameDao.saveGameDetail(g);
	}

	@Transactional
	public void saveGameDetail(GameModel gm, Integer selectedGame) {
		Game g = gameDao.findById(selectedGame);
		gm.setGame(g);
		GameDetail gd = convertToEntity(gm);
		if(gd.getId()== null||gd.getId() ==0)
			gameDao.saveGameDetail(gd);
		else
			gameDao.updateGameDetail(gd);
	}

	

}
