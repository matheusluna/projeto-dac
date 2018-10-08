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

        if (cpf == null || cpf.isEmpty() || cpf.length() != 11)
            return Response.status(Response.Status.BAD_REQUEST).build();

        Candidato candidato = new Candidato();
        candidato.setCpf(cpf);

        this.candidatoService.remover(candidato);

        return Response.ok().build();
    }

    @GET
    @Path("{cpf}")
    public Response candidatoComCpf(@PathParam("cpf") String cpf) {

        if (cpf == null || cpf.isEmpty() || cpf.length() != 11)
            return Response.status(Response.Status.BAD_REQUEST).build();

        Candidato candidato = candidatoService.candidatoComCPF(cpf);

        if (candidato == null)
            return Response.status(Response.Status.NO_CONTENT).build();
        else
            return Response.ok(candidato).build();

    }

    @GET
    @Path("buscar/{email}")
    public Response candidatoComEmail(@PathParam("email") String email) {

        if (email == null || email.length() < 3 || !email.contains("@"))
            return Response.status(Response.Status.BAD_REQUEST).build();

        Candidato candidato = candidatoService.candidatoComEmail(email);

        if (candidato == null)
            return Response.status(Response.Status.NO_CONTENT).build();
        else
            return Response.ok(candidato).build();
    }

}
