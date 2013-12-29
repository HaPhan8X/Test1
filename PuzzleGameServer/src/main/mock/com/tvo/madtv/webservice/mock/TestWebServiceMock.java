package com.tvo.madtv.webservice.mock;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import com.tvo.madtv.webservice.ITestWebService;

@Path("/sayhello")
public class TestWebServiceMock implements ITestWebService {

	@GET
	@Produces(MediaType.TEXT_PLAIN)
	public String sayHello() {
		return "Hello, this is Mock Web-Service of Mad-TV";
	}

}
