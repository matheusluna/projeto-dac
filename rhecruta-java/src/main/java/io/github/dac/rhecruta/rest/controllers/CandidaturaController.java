package io.github.dac.rhecruta.rest.controllers;

import io.github.dac.rhecruta.models.Candidato;
import io.github.dac.rhecruta.models.Candidatura;
import io.github.dac.rhecruta.rest.infraSecurity.Security;
import io.github.dac.rhecruta.rest.infraSecurity.TokenManagement;
import io.github.dac.rhecruta.service.CandidatoService;
import io.github.dac.rhecruta.service.CandidaturaService;
import jdk.nashorn.internal.parser.Token;

import javax.ejb.EJB;
import javax.ws.rs.*;
import javax.ws.rs.core.*;
import java.net.URI;

@Path("candidatura")
public class CandidaturaController {

    @EJB
    private CandidaturaService candidaturaService;

    @EJB
    private CandidatoService candidatoService;

    @POST
    @Security
    @Consumes(MediaType.APPLICATION_JSON)
    public Response cadastrarCandidatura(Candidatura candidatura,
                                         @Context UriInfo uriInfo,
                                         @Context SecurityContext securityContext) {

        if (candidatura == null || candidatura.getVagaId() == null || candidatura.getId() <= 1)
            return Response.status(Response.Status.BAD_REQUEST).build();

        String token = TokenManagement.getToken(securityContext);

        Candidato candidato = this.candidatoService.candidatoComEmail(token);

        candidatura.setCandidato(candidato);

        Integer id = this.candidaturaService.salvar(candidatura);
        URI uri = uriInfo.getAbsolutePathBuilder().path(id.toString()).build();

        return Response.created(uri).build();
    }

    @GET
    @Security
    @Produces(MediaType.APPLICATION_JSON)
    public Response candidaturasDoCandidato(@Context SecurityContext securityContext) {

        String token = TokenManagement.getToken(securityContext);

        Candidato candidato = this.candidatoService.candidatoComEmail(token);

        return Response.ok(
                this.candidaturaService.candidaturasPorCandidato(candidato)
        ).build();
    }

    @GET
    @Path("{id}")
    @Security
    @Produces(MediaType.APPLICATION_JSON)
    public Response recuperarCandidaturaComId(@PathParam("id") Integer id,
                                              @Context SecurityContext securityContext) {

        if (id == null || id <= 0)
            return Response.status(Response.Status.BAD_REQUEST).build();

        String token = TokenManagement.getToken(securityContext);

        Candidatura candidatura = this.candidaturaService.candidaturaComId(id);

        if (!candidatura.getCandidato().getEmail().equals(token))
            return Response.status(Response.Status.UNAUTHORIZED).build();
        else
            return Response.ok(candidatura).build();

    }

    //TODO Método de Atualização

    @DELETE
    @Path("{id}")
    @Security
    public Response removerCandidatura(@PathParam("id") Integer id,
                                       @Context SecurityContext securityContext) {

        if (id == null || id <= 0)
            return Response.status(Response.Status.BAD_REQUEST).build();

        Candidatura candidatura = this.candidaturaService.candidaturaComId(id);

        if (candidatura == null)
            return Response.status(Response.Status.BAD_REQUEST).build();

        String token = TokenManagement.getToken(securityContext);

        if (!candidatura.getCandidato().getEmail().equals(token))
            return Response.status(Response.Status.UNAUTHORIZED).build();

        this.candidaturaService.remover(candidatura);

        return Response.ok().build();

    }

}
