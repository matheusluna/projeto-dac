package io.github.dac.rhecruta.rest;

import org.glassfish.jersey.server.ResourceConfig;

import javax.ws.rs.ApplicationPath;

@ApplicationPath("rest")
public class Loader extends ResourceConfig {

    public Loader() {
        packages("io.github.dac.rhecruta.rest.controllers");
    }
}
