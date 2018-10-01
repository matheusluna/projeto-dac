package io.github.dac.rhecruta.rest.controllers;

import io.github.dac.rhecruta.service.VagaService;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.json.Json;
import javax.json.JsonArray;
import javax.json.JsonObject;
import javax.json.JsonReader;
import javax.ws.rs.*;
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

    @EJB
    private VagaService vagaService;

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

    @POST
    @Path("cidade")
    @Consumes(MediaType.APPLICATION_FORM_URLENCODED)
    @Produces(MediaType.APPLICATION_JSON)
    public Response vagasPorCidade(@FormParam("cidade") String cidade) {

        if (cidade == null || cidade.isEmpty())
            return Response.status(Response.Status.BAD_REQUEST).build();

        return Response.ok(
                vagaService.vagasPorCidade(processarRequisisao(), cidade)
        ).build();
    }

    @POST
    @Path("descricao")
    @Consumes(MediaType.APPLICATION_FORM_URLENCODED)
    @Produces(MediaType.APPLICATION_JSON)
    public Response vagasPorDescricao(@FormParam("descricao") String descricao) {

        if (descricao == null || descricao.isEmpty())
            return Response.status(Response.Status.BAD_REQUEST).build();

        return Response.ok(
                vagaService.vagasComDescricao(processarRequisisao(), descricao)
        ).build();
    }

    @POST
    @Path("empresa")
    @Consumes(MediaType.APPLICATION_FORM_URLENCODED)
    @Produces(MediaType.APPLICATION_JSON)
    public Response vagasPorEmpresa(@FormParam("empresa") String empresa) {

        if (empresa == null || empresa.isEmpty())
            return Response.status(Response.Status.BAD_REQUEST).build();

        return Response.ok(
                vagaService.vagasPorEmpresa(processarRequisisao(), empresa)
        ).build();
    }

    private JsonArray recuperarVagas(Response response) {

        String jsonString = response.readEntity(String.class);
        JsonReader jsonReader = Json.createReader(new StringReader(jsonString));
        JsonObject jsonObject = jsonReader.readObject();

        return jsonObject.getJsonArray("objects");
    }

    private JsonArray processarRequisisao() {
        String jsonString = listarVagas().readEntity(String.class);
        JsonReader jsonReader = Json.createReader(new StringReader(jsonString));
        return jsonReader.readArray();
    }
}

