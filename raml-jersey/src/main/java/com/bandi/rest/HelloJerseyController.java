package com.bandi.rest;

import java.util.Date;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.bandi.rest.data.Message;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

 /*
  * URL to test
  * 
  * http://localhost:8080/raml-jersey/HelloJaxRSController
  * 
  */
@Path("/HelloJaxRSController")
public class HelloJerseyController 
{
	private static Logger log = LoggerFactory.getLogger(HelloJerseyController.class);
	
	 /*
	  * URL to test
	  * 
	  * http://localhost:8080/Jersey/rest/HelloJaxRSController
	  * 
	  */
	@GET
	@Produces(MediaType.TEXT_HTML)
	public Response getStartingPage()
	{
		log.debug("log for hello controller using debug logging");
		String output = "<h1>Hello World!<h1>" +
				"<p>RESTful Service is running ... <br>Ping @ " + new Date().toString() + "</p<br>";
		return Response.status(200).entity(output).build();
	}
	
	 /*
	  * URL to test
	  * 
	  * http://localhost:8080/Jersey/rest/HelloJaxRSController/player.xml/kishore?text=bandi
	  * 
	  */
	@GET
	@Path("/player.xml/{player}")
	@Produces(MediaType.TEXT_XML)
	public Message getMessageXML(@PathParam("player") String player, @QueryParam("text") String text)
	{
		Gson gson = new GsonBuilder().create();
		log.debug("log for hello controller using debug logging");
		// String output = "{\"data\":\""+name+"\"}";
		Message message = new Message(player, " Hope you got your text " + text);
		// return   gson.toJson(message, Message.class);
		return message;
	}
	
	@Consumes
	@Produces("text/html")
	public Response getNextRestContent()
	{
		String output = "<h1>Hello World!<h1>" +
				"<p>Next Rest Content</p<br>";
		return Response.status(200).entity(output).build();
	}
	
	 /*
	  * URL to test
	  * 
	  * http://localhost:8080/Jersey/rest/HelloJaxRSController/inside/kishore
	  * 
	  */
	@GET
	@Path("/inside/{name}")
	@Produces(MediaType.APPLICATION_JSON)
	public Response getInside(@PathParam("name") String name)
	{
		log.debug("log for hello controller using debug logging");
		String output = "{\"data\":\""+name+"\"}";
		return Response.status(200).entity(output).build();
	}
	
	 /*
	  * URL to test
	  * 
	  * http://localhost:8080/Jersey/rest/HelloJaxRSController/player/kishore?text=bandi
	  * 
	  */
	@GET
	@Path("/player/{player}")
	@Produces(MediaType.APPLICATION_JSON)
	public Message getMessage(@PathParam("player") String player, @QueryParam("text") String text)
	{
		Gson gson = new GsonBuilder().create();
		log.debug("log for hello controller using debug logging");
		// String output = "{\"data\":\""+name+"\"}";
		Message message = new Message(player, " Hope you got your text " + text);
		// return   gson.toJson(message, Message.class);
		return message;
	}
}