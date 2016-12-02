package com.bandi.rest.test;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import com.google.gson.JsonObject;

public class TestClass {

    public static void main(String[] args) {
        JsonObject parameters = new JsonObject();
        parameters.addProperty("result", "firstSend");
        parameters.addProperty("adId", "abcAd");
        
        System.out.println(parameters);

        Client client = ClientBuilder.newClient();
        
         Response response = client.target("http://localhost:8080/Jersey").path("rest/HelloJaxRSController/postJson")
        
            .request(MediaType.APPLICATION_JSON).post(Entity.entity(parameters.toString(), MediaType.APPLICATION_JSON));
            
            
        System.out.println(response.readEntity(String.class));
        
        
        /*
        String response = client.target("http://localhost:8080/Jersey").path("rest/HelloJaxRSController/postJson")
                .request(MediaType.APPLICATION_JSON).post(Entity.json(parameters.toString()), String.class);
            
        
        System.out.println(response);*/
    }
    


    

}
