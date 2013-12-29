package com.tvo.puzzle.webservice;

import com.tvo.puzzle.context.SpringApplicationContext;

public class BaseWebService {
	
	// get service by service name
	public Object getService(String serviceName) {
		return SpringApplicationContext.getBean(serviceName);
	}
}
