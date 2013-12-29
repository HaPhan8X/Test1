package com.tvo.puzzle.webservice;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.ResponseBuilder;

import com.tvo.puzzle.util.Constants;
import com.tvo.puzzle.util.PropertiesUtil;
import com.tvo.puzzle.dto.GameDTO;
import com.tvo.puzzle.service.IGameService;
import com.tvo.puzzle.webservice.response.GameScreenResponse;

@Path("/game")
public class GameScreenWebService extends BaseWebService{
	
	IGameService gameService;
	@GET
	@Path("/download-xml/{gameId}/{resource}")
	@Produces("application/xml")
	public Response downloadgame(@PathParam("gameId") String id, @PathParam("resource") String resource){
		//StringBuilder file_path=new StringBuilder("C:\\game\\");
		StringBuilder file_path=new StringBuilder(PropertiesUtil.get(Constants.UPLOAD_SCREENPLAY));
		Integer gameId= 0;
		try {
			
			gameId = Integer.parseInt(id);
			
		} catch (Exception e) {
			
		}
		String folder = new SimpleDateFormat("ddMMyyyy").format(new Date());
		file_path.append(gameId+"/"+folder+"/"+resource+".xml");
		File file = new File(file_path.toString());
		ResponseBuilder response =Response.ok((Object)file);
		response.header("Content-Disposition",
				"attachment; filename=\""+resource+".xml\"");
			return response.build();
		
		
	}
	
	@GET
	@Path("/show-games")
	@Produces("application/xml")
	public GameScreenResponse showGames(){
		GameScreenResponse gsr = new GameScreenResponse();
		gameService = (IGameService) getService("gameService");
		List <GameDTO> gameDTOs = gameService.getGameList();
		gsr.setGameDTOs(gameDTOs);
		gsr.setMessage("load success");
		gsr.setResult("success");
		return gsr;
	}
}
