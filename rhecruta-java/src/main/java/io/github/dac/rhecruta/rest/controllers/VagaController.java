package io.github.dac.rhecruta.rest.controllers;

import io.github.dac.rhecruta.service.VagaService;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.json.JsonArray;
import javax.json.JsonObject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Stateless
@Path("vaga")
public class VagaController {

    @EJB
    private VagaService vagaService;

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response listarVagas() {

        JsonArray jsonArray = this.vagaService.recuperarTodasVagas();

        return Response.ok(jsonArray).build();
    }

    @GET
    @Path("{idVaga}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response recuperarVagaComId(@PathParam("idVaga") Integer idVaga) {

        if (idVaga <= 0)
            return Response.status(Response.Status.BAD_REQUEST).build();

        JsonObject jsonObject = this.vagaService.recuperarVagaComId(idVaga);

        return Response.ok(jsonObject).build();
    }

    @POST
    @Path("cidade")
    @Consumes(MediaType.APPLICATION_FORM_URLENCODED)
    @Produces(MediaType.APPLICATION_JSON)
    public Response vagasPorCidade(@FormParam("cidade") String cidade) {

        if (cidade == null || cidade.isEmpty())
            return Response.status(Response.Status.BAD_REQUEST).build();

        return Response.ok(
                vagaService.vagasPorCidade(cidade)
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
                vagaService.vagasComDescricao(descricao)
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
                vagaService.vagasPorEmpresa(empresa)
        ).build();
    }

}

