package io.github.dac.rhecruta.rest.controllers;

import javax.ejb.Stateless;
import javax.json.Json;
import javax.json.JsonArray;
import javax.json.JsonObject;
import javax.json.JsonReader;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.io.StringReader;

@Stateless
@Path("vaga")
public class VagaController {

    private final String URL_BASE = "www.pyjobs.com.br/api/jobs/";
    private final WebTarget REQUEST_BASE = ClientBuilder.newClient().target(URL_BASE);

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response listarVagas() {

        Response response = REQUEST_BASE
                .request()
                .get();

        JsonArray jsonArray = recuperarVagas(response);

        return Response.ok(jsonArray).build();
    }

    @GET
    @Path("{idVaga}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response recuperarVagaComId(@PathParam("idVaga") Integer idVaga) {

        if (idVaga <= 0)
            return Response.status(Response.Status.BAD_REQUEST).build();

        Response response = REQUEST_BASE
                .path("idVaga")
                .request()
                .get();

        JsonArray jsonArray = recuperarVagas(response);

        return Response.ok(jsonArray).build();
    }

    private JsonArray recuperarVagas(Response response) {

        String jsonString = response.readEntity(String.class);
        JsonReader jsonReader = Json.createReader(new StringReader(jsonString));
        JsonObject jsonObject = jsonReader.readObject();

        return jsonObject.getJsonArray("objects");
    }
}

