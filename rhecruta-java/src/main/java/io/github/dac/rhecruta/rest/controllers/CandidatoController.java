package io.github.dac.rhecruta.rest.controllers;

import io.github.dac.rhecruta.models.Candidato;
import io.github.dac.rhecruta.service.CandidatoService;

import javax.ejb.EJB;
import javax.ws.rs.*;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;
import java.net.URI;

@Path("candidato")
public class CandidatoController {

    @EJB
    private CandidatoService candidatoService;

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response retornarCandidatos() {

        return Response.ok(
                this.candidatoService.listarTodos()
        ).build();
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    public Response cadastrarCandidado(Candidato candidato, @Context UriInfo uriInfo) {

        String cpf = candidatoService.salvar(candidato);
        URI uri = uriInfo.getAbsolutePathBuilder().path(cpf).build();

        return Response.created(uri).build();
    }

    //TODO Método de Atualização

    @DELETE
    @Path("{cpf}")
    public Response deletarCandidato(@PathParam("cpf") String cpf) {

        Candidato candidato = new Candidato();
        candidato.setCpf(cpf);

        this.candidatoService.remover(candidato);

        return Response.ok().build();
    }

    @GET
    @Path("{cpf}")
    public Response candidatoComCpf(@PathParam("cpf") String cpf) {

        return Response.ok(
                this.candidatoService.candidatoComCPF(cpf)
        ).build();
    }

    @GET
    @Path("buscar/{email}")
    public Response candidatoComEmail(@PathParam("email") String email) {

        return Response.ok(
                this.candidatoService.candidatoComEmail(email)
        ).build();
    }
    
}
