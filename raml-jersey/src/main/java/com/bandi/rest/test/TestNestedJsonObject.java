package com.bandi.rest.test;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

public class TestNestedJsonObject {
	
	public static void main(String[] args) {
        String courseDetails = "{\"UserId\": \"abc\",\"CourseId\": \"C1\",\"CourseDetails\":{\"Id\": \"_26_1\",\"Name\": \"Group task test (revised)\",\"Description\": \"Sample Description\"}}";
        
        System.out.println(courseDetails);

        Client client = ClientBuilder.newClient();
        
         Response response = client.target("http://localhost:8080/Jersey").path("rest/HelloJaxRSController/postNestedJson")
        
            .request(MediaType.TEXT_PLAIN).post(Entity.entity(courseDetails, MediaType.APPLICATION_JSON));
            
            
        System.out.println(response.readEntity(String.class));
        
        
        /*
        String response = client.target("http://localhost:8080/Jersey").path("rest/HelloJaxRSController/postJson")
                .request(MediaType.APPLICATION_JSON).post(Entity.json(parameters.toString()), String.class);
            
        
        System.out.println(response);*/
    }
    

}
