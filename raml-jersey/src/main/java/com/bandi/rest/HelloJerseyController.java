package com.bandi.rest;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

import javax.servlet.ServletRequest;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.glassfish.jersey.server.mvc.Viewable;
//import com.sun.jersey.api.view.Viewable;
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
	  * http://localhost:8080/Jersey/rest/HelloJaxRSController/player/kishore?text=bandi&anotherText=contextText
	  * 
	  */
	@GET
	@Path("/player/{player}")
	@Produces(MediaType.APPLICATION_JSON)
	public Message getMessage(@PathParam("player") String player, @QueryParam("text") String text, @Context HttpServletRequest request)
	{
		Gson gson = new GsonBuilder().create();
		log.debug("log for hello controller using debug logging");
		// String output = "{\"data\":\""+name+"\"}";
		Message message = new Message(player, " Hope you got your text " + text);
		message.setAnotherText(request.getParameter("anotherText"));
		// return   gson.toJson(message, Message.class);
		return message;
	}
	
	 /*
	  * URL to test
	  * 
	  * http://localhost:8080/Jersey/rest/HelloJaxRSController/getAutoList
	  * 
	  */
	@GET
	@Path("/getAutoList")
	@Produces(MediaType.APPLICATION_JSON)
	public List getAutoList()
	{
		Gson gson = new GsonBuilder().create();
		List list = new ArrayList();
		list.add(1);
		list.add(new Message("kishore", " Bandi "));
		list.add(" abc 123 ");
		// return   gson.toJson(message, Message.class);
		return list;
	}
	
	 /*
	  * URL to test
	  * 
	  * http://localhost:8080/Jersey/rest/HelloJaxRSController/getList
	  * 
	  */
	@GET
	@Path("/getList")
	@Produces(MediaType.APPLICATION_JSON)
	public String getList()
	{
		Gson gson = new GsonBuilder().create();
		List<String> list = new ArrayList<String>();
		//list.add(1);
		list.add("Kishore");
		list.add("Bandi");
		//list.add(" abc 123 ");
		// return   gson.toJson(message, Message.class);
		return gson.toJson(list);
	}
	
	 /*
	  * URL to test
	  * 
	  * http://localhost:8080/Jersey/rest/HelloJaxRSController/getDoubleValue?doublevalue=234.45
	  * 
	  */
	@GET
	@Path("/getDoubleValue")
	@Produces(MediaType.APPLICATION_JSON)
	public double getDoubleValue(@QueryParam("doublevalue") double doublevalue)
	{
		System.out.println("doublevalue : " + doublevalue);
		return doublevalue;
	}
	
	 /*
	  * URL to test
	  * 
	  * http://localhost:8080/Jersey/rest/HelloJaxRSController/getArray?array=abc&array=def
	  * 
	  */
	@GET
	@Path("/getArray")
	@Produces(MediaType.APPLICATION_JSON)
	public String getArray(@QueryParam("array") List<String> arrays)
	{
		System.out.println("arrays : " + arrays);
		return arrays.toArray()[0].toString();
	}
	
	 /*
	  * URL to test
	  * 
	  * http://localhost:8080/Jersey/rest/HelloJaxRSController/homepage
	  * 
	  */
	@Path("/homepage")
	@GET
	public Viewable homepage(@Context HttpServletRequest request) {
	    System.out.println("/INDEX called");
	  HashMap<String, String> model = new HashMap<String, String>();
	  model.put("name","kishore-bandi");
	  return new Viewable("/index.jsp", model);
	}
	
	 /*
	  * URL to test
	  * 
	  * http://localhost:8080/Jersey/rest/HelloJaxRSController/genericRequest?name=kishore.
	  * 
	  */
	@Path("/genericRequest")
	@GET
	public String genericRequest(@Context ServletRequest request, @Context HttpServletResponse response) {
	    
		return request.getParameter("name");
	  
	}
}