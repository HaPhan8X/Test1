package com.tvo.madtv.webservice.mock;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;

import com.tvo.madtv.dto.ShowDTO;
import com.tvo.madtv.service.IShowScheduleService;
import com.tvo.madtv.webservice.ITelevisionProgramWebService;
import com.tvo.madtv.webservice.impl.BaseWebService;
import com.tvo.madtv.webservice.response.AdvertisementResponse;
import com.tvo.madtv.webservice.response.MovieResponse;
import com.tvo.madtv.webservice.response.TimetableResponse;

@Path("/programoffice")
public class TelevisionProgramWebServiceMock extends BaseWebService implements ITelevisionProgramWebService {

	@GET
	@Path("/gettimetable/{sessionID}/{playerID}")
	@Produces({ "application/xml", "application/json" })
	public TimetableResponse getTimetable(@PathParam("sessionID") String sessionID, @PathParam("playerID") int playerID) {
		IShowScheduleService showScheduleService = (IShowScheduleService) getService("showScheduleServiceMock");
		TimetableResponse response = new TimetableResponse();
		response.setTimetable(showScheduleService.getTimetableByPlayerID(playerID));
		return response;
	}

	@GET
	@Path("/getplayermoviesfromstore/{sessionID}/{playerID}/{limit}/{offset}")
	@Produces({ "application/xml", "application/json" })
	public MovieResponse getPlayerMoviesFromStore(@PathParam("sessionID") String sessionID,
												  @PathParam("playerID") int playerID,
												  @PathParam("limit") int limit,
												  @PathParam("offset") int offset) {
		IShowScheduleService showScheduleService = (IShowScheduleService) getService("showScheduleServiceMock");
		MovieResponse response = new MovieResponse();
		response.setMovieList(showScheduleService.getMoviesByPlayerID(playerID));
		return response;
	}

	@GET
	@Path("/getplayeradvertisementsfromstore/{playerID}")
	@Produces({ "application/xml", "application/json" })
	public AdvertisementResponse getPlayerAdvertisementsFromStore(@PathParam("playerID") int playerID) {
		IShowScheduleService showScheduleService = (IShowScheduleService) getService("showScheduleServiceMock");
		AdvertisementResponse response = new AdvertisementResponse();
		response.setAdvertisementList(showScheduleService.getAdvertisementsByPlayerID(playerID));
		return response;
	}

	@Override
	public String updateTimetable(String sessionID, int playerID,
			String timetable) {
		// TODO Auto-generated method stub
		return null;
	}

	@GET
	@Path("/getcurrentmovieinfo/{sessionID}/{playerID}")
	@Produces({ "application/xml", "application/json" })
	public ShowDTO getCurrentMovieInfo(@PathParam("sessionID") String sessionID, @PathParam("playerID") int playerID) {
		IShowScheduleService showScheduleService = (IShowScheduleService) getService("showScheduleServiceMock");
		return showScheduleService.getCurrentMovieInfo(playerID);
	}

}
