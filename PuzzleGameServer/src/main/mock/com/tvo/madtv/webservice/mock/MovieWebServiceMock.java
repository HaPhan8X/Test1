package com.tvo.madtv.webservice.mock;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;

import com.tvo.madtv.service.IMovieService;
import com.tvo.madtv.service.IShowCategoryService;
import com.tvo.madtv.webservice.IMovieWebService;
import com.tvo.madtv.webservice.impl.BaseWebService;
import com.tvo.madtv.webservice.response.ListShowCategoryResponse;
import com.tvo.madtv.webservice.response.MovieResponse;

@Path("/movie")
public class MovieWebServiceMock extends BaseWebService implements IMovieWebService {

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
	@Path("/getmoviebycategoryid/{categoryID}")
	@Produces({ "application/xml", "application/json" })
	public MovieResponse getMovieByCategoryID(@PathParam("categoryID") int categoryID) {
		IMovieService movieService = (IMovieService) getService("movieServiceMock");
		MovieResponse response = new MovieResponse();
		response.setMovieList(movieService.findRangeShowByCatgory(100, 0, categoryID));
		return response;
	}

	public String buyMovies(String sessionID, int playerID, String arrMovieID) {
		// TODO Auto-generated method stub
		return null;
	}

}
