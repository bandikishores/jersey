package com.bandi.rest;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.inject.Inject;
import javax.servlet.ServletRequest;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.ws.rs.Consumes;
import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.apache.commons.io.IOUtils;
import org.glassfish.jersey.media.multipart.FormDataBodyPart;
import org.glassfish.jersey.media.multipart.FormDataMultiPart;
import org.glassfish.jersey.media.multipart.FormDataParam;
import org.glassfish.jersey.server.mvc.Viewable;
import org.json.simple.JSONObject;
//import com.sun.jersey.api.view.Viewable;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.bandi.rest.data.Message;
import com.bandi.rest.data.SimpleData;
import com.bandi.rest.service.TestService;
import com.bandi.rest.transaction.GuiceTransaction;
import com.bandi.rest.transaction.Transaction;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

/*
 * URL to test
 * 
 * http://localhost:8080/raml-jersey/HelloJaxRSController
 * 
 */
@Path("/HelloJaxRSController")
public class HelloJerseyController {
	@Inject
	TestService testService;
	
	@Inject
	Transaction transaction;

	@Inject
	GuiceTransaction guiceTransaction;

	private static Logger log = LoggerFactory.getLogger(HelloJerseyController.class);

	/*
	 * URL to test
	 * 
	 * http://localhost:8080/Jersey/rest/HelloJaxRSController
	 * 
	 */
	@GET
	@Produces(MediaType.TEXT_HTML)
	public Response getStartingPage() {
		log.debug("log for hello controller using debug logging");
		String output = "<h1>Hello World!<h1>" + "<p>RESTful Service is running ... <br>Ping @ " + new Date().toString()
				+ "</p<br>";
		return Response.status(200).entity(output).build();
	}

	/*
	 * URL to test
	 * 
	 * http://localhost:8080/Jersey/rest/HelloJaxRSController/player.xml/kishore
	 * ?text=bandi
	 * 
	 */
	@GET
	@Path("/player.xml/{player}")
	@Produces(MediaType.TEXT_XML)
	public Message getMessageXML(@PathParam("player") String player, @QueryParam("text") String text) {
		Gson gson = new GsonBuilder().create();
		log.debug("log for hello controller using debug logging");
		// String output = "{\"data\":\""+name+"\"}";
		Message message = new Message(player, " Hope you got your text " + text);
		// return gson.toJson(message, Message.class);
		return message;
	}

	@Consumes
	@Produces("text/html")
	public Response getNextRestContent() {
		String output = "<h1>Hello World!<h1>" + "<p>Next Rest Content</p<br>";
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
	@Produces({MediaType.APPLICATION_JSON})
	public Response getInside(@PathParam("name") String name) {
		log.debug("log for hello controller using debug logging");
		String output = "{\"data\":\"" + name + "\"}";
		return Response.status(200).entity(output).build();
	}

	/*
	 * URL to test
	 * 
	 * http://localhost:8080/Jersey/rest/HelloJaxRSController/player/kishore?
	 * text=bandi&anotherText=contextText
	 * 
	 */
	@GET
	@Path("/player/{player}")
	@Produces(MediaType.APPLICATION_JSON)
	public Message getMessage(@PathParam("player") String player, @QueryParam("text") String text,
			@Context HttpServletRequest request) {
		Gson gson = new GsonBuilder().create();
		log.debug("log for hello controller using debug logging");
		// String output = "{\"data\":\""+name+"\"}";
		Message message = new Message(player, " Hope you got your text " + text);
		message.setAnotherText(request.getParameter("anotherText"));
		// return gson.toJson(message, Message.class);
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
	public List getAutoList() {
		Gson gson = new GsonBuilder().create();
		List list = new ArrayList();
		list.add(1);
		list.add(new Message("kishore", " Bandi "));
		list.add(" abc 123 ");
		// return gson.toJson(message, Message.class);
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
	public String getList() {
		Gson gson = new GsonBuilder().create();
		List<String> list = new ArrayList<String>();
		// list.add(1);
		list.add("Kishore");
		list.add("Bandi");
		// list.add(" abc 123 ");
		// return gson.toJson(message, Message.class);
		return gson.toJson(list);
	}

	/*
	 * URL to test
	 * 
	 * http://localhost:8080/Jersey/rest/HelloJaxRSController/getDoubleValue?
	 * doublevalue=234.45
	 * 
	 */
	@GET
	@Path("/getDoubleValue")
	@Produces(MediaType.APPLICATION_JSON)
	public double getDoubleValue(@QueryParam("doublevalue") double doublevalue) {
		System.out.println("doublevalue : " + doublevalue);
		return doublevalue;
	}

	/*
	 * URL to test
	 * 
	 * http://localhost:8080/Jersey/rest/HelloJaxRSController/getArray?array=abc
	 * &array=def
	 * 
	 */
	@GET
	@Path("/getArray")
	@Produces(MediaType.APPLICATION_JSON)
	public String getArray(@QueryParam("array") List<String> arrays) {
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
		model.put("name", "kishore-bandi");
		return new Viewable("/index.jsp", model);
	}

	/*
	 * URL to test
	 * 
	 * http://localhost:8080/Jersey/rest/HelloJaxRSController/genericRequest?
	 * name=kishore.
	 * 
	 */
	@Path("/genericRequest")
	@GET
	public String genericRequest(@Context ServletRequest request, @Context HttpServletResponse response) {

		return request.getParameter("name");

	}

	/*
	 * URL to test
	 * 
	 * http://localhost:8080/Jersey/rest/HelloJaxRSController/springLoad
	 * 
	 */
	@Path("/springLoad")
	@GET
	public String springLoad(@Context HttpServletRequest request) {
		return transaction.returnSuccess();
	}

	/*
	 * URL to test
	 * 
	 * http://localhost:8080/Jersey/rest/HelloJaxRSController/springLoad.json
	 * 
	 */
	@Path("/springLoad.json")
	@GET
	// @Transactional(propagation = Propagation.REQUIRES_NEW)
	public String springLoad_json(@Context HttpServletRequest request) {
		return testService.returnString();
	}

	/*
	 * URL to test
	 * 
	 * http://localhost:8080/Jersey/rest/HelloJaxRSController/guiceLoad
	 * 
	 */
	@Path("/guiceLoad")
	@GET
	public String guiceLoad(@Context HttpServletRequest request) {
		return guiceTransaction.returnSuccess();
	}

	/*
	 * URL to test
	 * 
	 * http://localhost:8080/Jersey/rest/HelloJaxRSController/uploadData
	 * 
	 */
	@POST
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.MULTIPART_FORM_DATA)
	@Path("/uploadData")
	public void uploadImage(@Context HttpServletRequest request, @Context HttpServletResponse response,
			@FormParam(value = "param1") String assetType, @FormParam("param2") String param2) throws Exception {

		if (assetType == null || param2 == null) {
			log.info("In param2 file Missing");
			throw new Exception("param1");
		}

	}

	@POST
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.MULTIPART_FORM_DATA)
	@Path("/assets/image.json")
	public void uploadImage(@Context HttpServletRequest request, @Context HttpServletResponse response,
			@FormDataParam(value = "assettype") String assetType, @FormDataParam("fileData") InputStream file)
			throws Exception {

		if (file == null) {
			log.info("In uploadImage file Missing");
			throw new Exception("FileMissing");
		}
		IOUtils.toByteArray(file);

	}

	@POST
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.MULTIPART_FORM_DATA)
	@Path("/uploadcsvbsid.html")
	public String uploadCsv(@Context final HttpServletRequest request, FormDataMultiPart formParams)
			throws IOException {

		Map<String, List<FormDataBodyPart>> fileMapSet = formParams.getFields();
		for (String controlName : fileMapSet.keySet()) {
			List<FormDataBodyPart> formDatas = fileMapSet.get(controlName);
			for (FormDataBodyPart formData : formDatas) {
				BufferedReader reader = new BufferedReader(
						new InputStreamReader(formData.getValueAs(InputStream.class)));
			}
		}
		return null;
	}
	


	/*
	 * URL to test
	 * 
	 * http://localhost:8080/Jersey/rest/HelloJaxRSController/uploadPOST
	 * 
	 */
	@POST
	@Produces(MediaType.APPLICATION_JSON)
	@Path("/uploadPOST")
	public String uploadJSON(Integer i) throws Exception {

		return "{\"abc\":\"JSON\"}";
	}

	/*
	 * URL to test
	 * 
	 * http://localhost:8080/Jersey/rest/HelloJaxRSController/uploadPOST
	 * 
	 */
	@POST
	@Produces(MediaType.TEXT_HTML)
	@Path("/uploadPOST")
	public String uploadHTML(String abc) throws Exception {

		return "<html><body>HTML Text</body></html>";

	}

	/*
	 * URL to test
	 * 
	 * http://localhost:8080/Jersey/rest/HelloJaxRSController/addadvertiser
	 * 
	 * { "advertiserEmailId" : "testEmail"}
	 * 
	 */
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.TEXT_PLAIN)
	@Path("/addadvertiser")
	public String addadvertiser(final String advertiserEmailId) throws Exception {

		return "responseText " + advertiserEmailId;

	}

	/*
	 * URL to test
	 * 
	 * http://localhost:8080/Jersey/rest/HelloJaxRSController/postJson
	 * 
	 * {"result" : "firstSend", "adId":"abcAd" }
	 * 
	 * or
	 * 
	 * { "SimpleData":{"result" : "firstSend", "adId":"abcAd" } }
	 * 
	 */
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	@Path("/postJson")
	public SimpleData uploadHTML(SimpleData simple) throws Exception {
		simple.setResult("Received sending response");
		return simple;

	}

	/*
	 * URL to test
	 * 
	 * http://localhost:8080/Jersey/rest/HelloJaxRSController/postJson
	 * 
	 * {"result" : "firstSend", "adId":"abcAd" }
	 * 
	 * or
	 * 
	 * { "SimpleData":{"result" : "firstSend", "adId":"abcAd" } }
	 * 
	 */
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces({MediaType.TEXT_PLAIN})
	@Path("/postNestedJson")
	public String postNestedJson(JSONObject obj) throws Exception {
		 String result = "failure";
		    if(obj != null) {
		        String userId = (String) obj.get("UserId");
		        String courseId = (String) obj.get("CourseId");         
		        result = ((LinkedHashMap) obj.get("CourseDetails")).toString();
		        // Problem in fetching course Details 
		    }
		return result;
	}
}
