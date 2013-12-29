package com.tvo.puzzle.service.impl;

import com.tvo.puzzle.dto.GameDetailDTO;
import com.tvo.puzzle.entity.Game;
import com.tvo.puzzle.entity.GameDetail;
import com.tvo.puzzle.service.IGameDetailService;

public class GameDetailServiceImpl implements IGameDetailService {

	private void dtoToEntity(GameDetailDTO dto, GameDetail entity) {
		entity.setId(dto.getId());
		
		Game g = new Game();
		g.setId(dto.getTblGame().getId());
		entity.setTblGame(g);
		
		entity.setResource(dto.getResource());
		entity.setFolderName(dto.getFolderName());
	}
}
