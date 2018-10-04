package io.github.dac.rhecruta.rest.controllers;

import io.github.dac.rhecruta.models.Candidato;
import io.github.dac.rhecruta.models.Candidatura;
import io.github.dac.rhecruta.service.CandidaturaService;

import javax.ejb.EJB;
import javax.ws.rs.*;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;
import java.net.URI;

@Path("candidatura")
public class CandidaturaController {

    @EJB
    private CandidaturaService candidaturaService;

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    public Response cadastrarCandidatura(Candidatura candidatura, @Context UriInfo uriInfo) {

        Integer id = this.candidaturaService.salvar(candidatura);
        URI uri = uriInfo.getAbsolutePathBuilder().path(id.toString()).build();

        return Response.created(uri).build();
    }

    @GET
    @Path("{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response recuperarCandidaturas(@PathParam("id") Integer id) {

        return Response.ok(
                this.candidaturaService.candidaturaComId(id)
        ).build();
    }

    //TODO Método de Atualização

    @DELETE
    @Path("{id}")
    public Response removerCandidatura(@PathParam("id") Integer id) {

        Candidatura candidatura = new Candidatura();
        candidatura.setId(id);

        this.candidaturaService.remover(candidatura);

        return Response.ok().build();
    }

    @GET
    @Path("candidato")
    @Produces(MediaType.APPLICATION_JSON)
    public Response candidaturaDoCandidato(Candidato candidato) {

        return Response.ok(
                this.candidaturaService.candidaturasPorCandidato(candidato)
        ).build();
    }

    @GET
    @Path("candidato/{cpf}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response candidaturaDoCandidato(@PathParam("cpf") String cpf) {

        Candidato candidato = new Candidato();
        candidato.setCpf(cpf);

        return Response.ok(
                this.candidaturaService.candidaturasPorCandidato(candidato)
        ).build();
    }

}
