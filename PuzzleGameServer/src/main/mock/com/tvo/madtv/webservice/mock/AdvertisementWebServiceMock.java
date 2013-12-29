package com.tvo.madtv.webservice.mock;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;

import com.tvo.madtv.service.IAdvertisementService;
import com.tvo.madtv.webservice.IAdvertisementWebService;
import com.tvo.madtv.webservice.impl.BaseWebService;
import com.tvo.madtv.webservice.response.AdvertisementResponse;

@Path("/advertisement")
public class AdvertisementWebServiceMock extends BaseWebService implements IAdvertisementWebService {

	@GET
	@Path("/getplayeradvertisementsfromlibrary/{sessionID}/{playerID}")
	@Produces({ "application/xml", "application/json" })
	public AdvertisementResponse getPlayerAdvertisementsFromLibrary(@PathParam("sessionID") String sessionID, @PathParam("playerID") int playerID) {
		IAdvertisementService advertisementService = (IAdvertisementService) getService("advertisementServiceMock");
		AdvertisementResponse response = new AdvertisementResponse();
		response.setAdvertisementList(advertisementService.getPlayerAdvertisementByPlayerID(playerID));
		return response;
	}

	@GET
	@Path("/getplayeradvertisementsfromstore")
	@Produces({ "application/xml", "application/json" })
	public AdvertisementResponse getAdvertisementsFromStore() {
		IAdvertisementService advertisementService = (IAdvertisementService) getService("advertisementServiceMock");
		AdvertisementResponse response = new AdvertisementResponse();
		response.setAdvertisementList(advertisementService.findRangeAdvertisement(1, 1));
		return response;
	}

	public String updatePlayerAdvertisementsToLibrary(String sessionID,
			int playerID, String arrayAdsvertisementID) {
		// TODO Auto-generated method stub
		return null;
	}

}
