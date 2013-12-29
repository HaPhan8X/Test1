package com.tvo.madtv.webservice.mock;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;

import com.tvo.madtv.service.IScreenPlayService;
import com.tvo.madtv.service.IShowCategoryService;
import com.tvo.madtv.webservice.IScreenplayWebService;
import com.tvo.madtv.webservice.impl.BaseWebService;
import com.tvo.madtv.webservice.response.ListShowCategoryResponse;
import com.tvo.madtv.webservice.response.ScreenplayResponsse;

@Path("/screenplay")
public class ScreenplayWebServiceMock extends BaseWebService implements IScreenplayWebService {

	@GET
	@Path("/getcategories")
	@Produces({ "application/xml", "application/json" })
	public ListShowCategoryResponse getCategories() {
		IShowCategoryService showCategoryService = (IShowCategoryService) getService("showCategoryServiceMock");
		ListShowCategoryResponse response = new ListShowCategoryResponse();
		response.setShowCategoryList(showCategoryService.getall());
		return response;
	}

	@GET
	@Path("/getscreenplaybycategoryid/{categoryID}")
	@Produces({ "application/xml", "application/json" })
	public ScreenplayResponsse getScreenplayByCategoryID(@PathParam("categoryID") int categoryID) {
		IScreenPlayService screenPlayService = (IScreenPlayService) getService("screenPlayServiceMock");
		ScreenplayResponsse response = new ScreenplayResponsse();
		response.setScreenplayList(screenPlayService.findRange(categoryID, "", 200, 0));
		return response;
	}

	public String buyScreenplay(String sessionID, int playerID,
			String arrScreenplayID) {
		// TODO Auto-generated method stub
		return null;
	}

	

}
