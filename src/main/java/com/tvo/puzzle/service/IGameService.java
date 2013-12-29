package com.tvo.puzzle.service;

import java.util.Date;
import java.util.List;

import com.tvo.puzzle.dto.GameDTO;
import com.tvo.puzzle.entity.Game;
import com.tvo.puzzle.model.GameModel;

public interface IGameService {

	List<GameDTO> getGameList();

	List<Game> listGame();

	List<GameModel> getAll(Integer selectedGame,int limit,int offset);

	Integer countAll(Integer selectedGame);

	void saveScreenXml(String fileName, Integer selectedGame, Date date);

	int countScreenByDay(Integer selectedGame, Date date);

	GameModel getGameDetail(int id);

	void saveGameDetail(GameModel gameModel);

	void saveGameDetail(GameModel gm, Integer selectedGame);

	List<GameModel> getGameDetail(String folder, Integer selectedGame);

}
