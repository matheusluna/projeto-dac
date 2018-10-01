package io.github.dac.rhecruta.rest;

import io.github.dac.rhecruta.rest.infraSecurity.filters.CORSFilter;
import io.github.dac.rhecruta.rest.infraSecurity.filters.FilterSecurityAuthentication;
import io.github.dac.rhecruta.rest.infraSecurity.filters.FilterSecurityAuthorization;
import org.glassfish.jersey.server.ResourceConfig;

import javax.ws.rs.ApplicationPath;

@ApplicationPath("rest")
public class Loader extends ResourceConfig {

    public Loader() {
        packages("io.github.dac.rhecruta.rest.controllers");

        register(CORSFilter.class);
        register(FilterSecurityAuthorization.class);
        register(FilterSecurityAuthentication.class);
    }
}
