package com.tvo.puzzle.dao;

import java.util.List;

import com.tvo.puzzle.entity.Game;
import com.tvo.puzzle.entity.GameDetail;

public interface GameDao extends GenericDao<Game, Integer>{

	public List<Game> getGameList();

	public List<GameDetail> getGameDetails(Integer selectedGame,int limit,int offset);

	public Integer countAll(Integer selectedGame);

	public void saveGameDetail(GameDetail gd);

	public int countScreenByDate(Integer selectedGame, String date);

	public GameDetail getById(int id);

	public List<GameDetail> getListLevelByDate(String folder,
			Integer selectedGame);
	public void updateGameDetail(final GameDetail gd);

}
