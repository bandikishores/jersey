package com.bandi.rest;

import java.util.Date;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Response;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

 /*
  * URL to test
  * 
  * http://localhost:8080/raml-jersey/rest
  * 
  */
@Path("/")
public class HelloController 
{
	private static Logger log = LoggerFactory.getLogger(HelloController.class);
	
	@GET
	@Produces("text/html")
	public Response getStartingPage()
	{
		log.debug("log for hello controller using debug logging");
		String output = "<h1>Hello World!<h1>" +
				"<p>RESTful Service is running ... <br>Ping @ " + new Date().toString() + "</p<br>";
		return Response.status(200).entity(output).build();
	}
	
	@Consumes
	@Produces("text/html")
	public Response getNextRestContent()
	{
		String output = "<h1>Hello World!<h1>" +
				"<p>Next Rest Content</p<br>";
		return Response.status(200).entity(output).build();
	}
}