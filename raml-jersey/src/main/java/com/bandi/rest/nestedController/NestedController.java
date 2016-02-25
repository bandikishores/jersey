package com.bandi.rest.nestedController;

import java.util.HashMap;

import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;

// If even one Controller defines an @Path Class level, then other controllers which define at method level will be ignored.

@Path("")
public class NestedController {
	
	/*
	  * URL to test
	  * 
	  * http://localhost:8080/Jersey/rest/nestedController
	  * 
	  */
	@Path("/nestedController")
	@GET
	@Produces(MediaType.TEXT_HTML)
	public String nestedController(@Context HttpServletRequest request) {	  
	  return "Nested Controller";
	}

}
