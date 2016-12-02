package com.bandi.rest.filter;

import java.io.IOException;

import javax.ws.rs.container.ContainerRequestContext;
import javax.ws.rs.container.ContainerRequestFilter;
import javax.ws.rs.ext.Provider;

import com.bandi.rest.data.ThreadLocalWrapper;

@Provider
public class RequestFilter implements ContainerRequestFilter {

	@Override
	public void filter(ContainerRequestContext requestContext) throws IOException {
		ThreadLocalWrapper.getInstance().get().add("adbc");
	}

}
