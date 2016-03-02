package com.bandi.jacksonconfig;

import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.ext.Provider;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.PropertyAccessor;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * JSON content type provider
 * 
 * This Class provides configuration about how to serialize/de-serialize data.
 * 
 * @author Kishore.Bandi
 */
@Provider
@Produces(MediaType.APPLICATION_JSON)
public class JacksonJaxbJsonProvider extends com.fasterxml.jackson.jaxrs.json.JacksonJaxbJsonProvider {
    private static ObjectMapper objectMapper = new ObjectMapper();

    static {
        objectMapper.setVisibility(PropertyAccessor.ALL, JsonAutoDetect.Visibility.ANY);
        objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        // objectMapperAtRest.configure(SerializationFeature.INDENT_OUTPUT, true);
        objectMapper.setSerializationInclusion(JsonInclude.Include.NON_NULL);
    }

    public JacksonJaxbJsonProvider() {
        super();
        setMapper(objectMapper);
    }
}
