package io.github.dac.rhecruta.rest.infraSecurity.filters;

import javax.ws.rs.container.ContainerRequestContext;
import javax.ws.rs.container.ContainerResponseContext;
import javax.ws.rs.container.ContainerResponseFilter;
import javax.ws.rs.core.MultivaluedMap;
import java.io.IOException;

public class CORSFilter implements ContainerResponseFilter {

	public void filter(ContainerRequestContext requestContext, ContainerResponseContext responseContext)
			throws IOException {

		MultivaluedMap<String, Object> headers = responseContext.getHeaders();

		headers.add("Access-Control-Allow-Origin", "*"); //Permite o Origin com qualquer URL
		headers.add("Access-Control-Allow-Methods", "PUT, GET, POST, DELETE, OPTIONS");
        headers.add("Access-Control-Allow-Credentials", "true");
		headers.add("Access-Control-Allow-Headers", "accept, origin, X-Requested-With, Content-Type, X-Codingpedia, Authorization");
		headers.add("Access-Control-Max-Age", "172800");
	}

}