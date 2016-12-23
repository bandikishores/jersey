package com.bandi.rest;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.ws.rs.core.MediaType;

import org.apache.commons.io.IOUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.slf4j.MDC;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.bandi.rest.data.DistributionOrderActionParametersList;
import com.bandi.rest.data.Message;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

/*
 * URL to test
 * 
 * 
 * 
 */

@RestController("springController")
public class HelloSpringRestController {
	
	private static Logger log = LoggerFactory.getLogger(HelloSpringRestController.class);
	
		/*
		 * URL to test
		 * 
		 * http://localhost:8080/Jersey/dispatcherPath/springBoot/1.json?text=abc
		 * 
		 */
	 	@RequestMapping(value = "/springBoot/{player}.json", method = RequestMethod.GET)
	    public void textUsingJson(final HttpServletRequest request, HttpServletResponse response, @PathVariable String player,
	    		@RequestParam(required = true, value = "text") String text) {
	 		
	 		MDC.put("logFileName", "JSONLogger");
	 		
	 		log.trace(" trace values passed in JSON {}   {}  ", player, text);
	 		log.debug(" debug passed in JSON {}   {}  ", player, text);
	 		log.info(" info values passed in JSON {}   {}  ", player, text);
	 		log.warn(" warn values passed in JSON {}   {}  ", player, text);
	 		log.error(" error values passed in JSON {}   {}  ", player, text);
	 		
	 		Gson gson = new GsonBuilder().create();
	 		String jsonString = gson.toJson(new Message(player, " Hope you got the output of player " + text), Message.class);
	 		
	 		PrintWriter writer = null;
	        try {
	            response.setContentType("application/json");
	            writer = response.getWriter();
	            IOUtils.write(jsonString, writer);
	        } catch (IOException e) {
	            log.error("Exception in writeResponse: ", e);
	        } finally {
	            IOUtils.closeQuietly(writer);
	            MDC.remove("logFileName");
	        }
	        
	    }

		/*
		 * URL to test
		 * 
		 * http://localhost:8080/Jersey/dispatcherPath/springBoot/1.xml?text=abc
		 * 
		 */
	 	@RequestMapping(value = "/springBoot/{player}.xml", method = RequestMethod.GET, produces = MediaType.APPLICATION_XML)
	    public @ResponseBody Message textUsingXML(@PathVariable String player,
	    		@RequestParam(required = true, value = "text") String text) {
	 		
	 		MDC.put("logFileName", "XMLLogger");
	 		
	 		log.info(" values passed in ZML {}   {}  ", player, text);
	 		
	 		MDC.remove("logFileName");
	 		
	 		return new Message(player, " Hope you got your text " + text);
	 		/* 
	 		 * 
	 		 * OR
	 		 * 
	 		 * remove return statement, @responsebody and produces clause
	 		 * 
	 		 * return gson.toJson(new Message(player, " Hope you got the output of player "), Message.class);
	 		 * 
	 		 */
	 		
	 		
	    }

		/*
		 * URL to test
		 * 
		 * http://localhost:8080/Jersey/dispatcherPath/springBoot/homepage
		 * 
		 */
	 	@RequestMapping(value = "/springBoot/homepage", method = RequestMethod.GET, produces = MediaType.APPLICATION_XML)
	    public String homepage(ModelMap map) {
	 		
	 		MDC.put("logFileName", "XMLLogger");
	 		
	 		map.addAttribute("name", "Kishore-Bandi");
	 		
	 		MDC.remove("logFileName");
	 		
	 		return "/index.jsp";
	    }

		/*
		 * URL to test
		 * 
		 * http://localhost:8080/Jersey/dispatcherPath/samplePostTest
		 * 
		 */
	 	@RequestMapping(value = "/samplePostTest", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON)
	    public Message samplePostTest(Message message) {
	 		
	 		log.info("Value of message {}", message);
	 		
	 		return message;
	    }

		/*
		 * URL to test
		 * 
		 * http://localhost:8080/Jersey/dispatcherPath/sampleTest
		 * 
		 */
	 	@RequestMapping(value = "/sampleTest", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON)
	    public DistributionOrderActionParametersList sampleTest(@RequestBody DistributionOrderActionParametersList DistributionOrderActionParametersList) {
	 		
	 		log.info("Value of Object {}", DistributionOrderActionParametersList);
	 		
	 		return DistributionOrderActionParametersList;
	    }

	

}
