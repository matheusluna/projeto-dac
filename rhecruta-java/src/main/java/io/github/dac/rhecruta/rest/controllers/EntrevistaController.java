package io.github.dac.rhecruta.rest.controllers;

import io.github.dac.rhecruta.models.Candidatura;
import io.github.dac.rhecruta.models.Entrevista;
import io.github.dac.rhecruta.rest.infraSecurity.Security;
import io.github.dac.rhecruta.rest.infraSecurity.TokenManagement;
import io.github.dac.rhecruta.service.CandidaturaService;
import io.github.dac.rhecruta.service.EntrevistaService;

import javax.ejb.EJB;
import javax.ws.rs.*;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.SecurityContext;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

@Path("entrevista")
public class EntrevistaController {

    @EJB
    private EntrevistaService entrevistaService;

    @EJB
    private CandidaturaService candidaturaService;

    @GET
    @Security
    @Consumes(MediaType.APPLICATION_JSON)
    public Response listarTodasEntrevistas(@Context SecurityContext securityContext) {

        String token = TokenManagement.getToken(securityContext);

        List<Entrevista> entrevistas = this.entrevistaService.entrevistasPorCandidato(token);

        if (entrevistas == null || entrevistas.size() == 0)
            return Response.status(Response.Status.NO_CONTENT).build();
        else
            return Response.ok(entrevistas).build();

    }

    @GET
    @Path("{idCandidatura}")
    @Security
    @Consumes(MediaType.APPLICATION_JSON)
    public Response recuperarEntrevista(@PathParam("idCandidatura") Integer id,
                                      @Context SecurityContext securityContext) {

        if (id == null || id <= 0)
            return Response.status(Response.Status.BAD_REQUEST).build();

        String token = TokenManagement.getToken(securityContext);

        Entrevista entrevista = this.entrevistaService.entrevistaComId(id);

        if (entrevista == null)
            return Response.status(Response.Status.NO_CONTENT).build();

        if (entrevista.getCandidatura().getCandidato().getEmail().equals(token))
            return Response.status(Response.Status.UNAUTHORIZED).build();

        return Response.ok(entrevista).build();
    }

    @POST
    @Path("{idCandidatura}")
    @Security
    @Consumes(MediaType.APPLICATION_JSON)
    public Response agendarEntrevista(@PathParam("idCandidatura") Integer id,
                                      @FormParam("diaDaEntrevista") String diaDaEntrevista,
                                      @FormParam("horarioDaEntrevista") String horarioDaEntrevista,
                                      @Context SecurityContext securityContext) {

        if (id == null || id <= 0)
            return Response.status(Response.Status.BAD_REQUEST).build();

        String token = TokenManagement.getToken(securityContext);

        Candidatura candidatura = this.candidaturaService.candidaturaComId(id);

        if (candidatura == null)
            return Response.status(Response.Status.NO_CONTENT).build();

        if (candidatura.getCandidato().getEmail().equals(token))
            return Response.status(Response.Status.UNAUTHORIZED).build();

        DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        DateTimeFormatter timeFormatter = DateTimeFormatter.ofPattern("hh:mm");

        Entrevista entrevista = new Entrevista();

        entrevista.setDiaDaEntrevista(LocalDateTime.parse(diaDaEntrevista, dateFormatter));
        entrevista.setHorarioDaEntrevista(LocalDateTime.parse(horarioDaEntrevista, timeFormatter));
        entrevista.setCandidatura(candidatura);

        entrevistaService.salvar(entrevista);

        //  TODO por código create?
        return Response.ok().build();
    }

    @DELETE
    @Path("{idEntrevista}")
    @Security
    public Response cancelarEntrevista(@PathParam("idEntrevista") Integer id,
                                       @Context SecurityContext securityContext) {

        if (id == null || id <= 0)
            return Response.status(Response.Status.BAD_REQUEST).build();

        Entrevista entrevista = entrevistaService.entrevistaComId(id);

        if (entrevista == null)
            return Response.status(Response.Status.NO_CONTENT).build();

        String token = TokenManagement.getToken(securityContext);

        if (!entrevista.getCandidatura().getCandidato().getEmail().equals(token))
            return Response.status(Response.Status.UNAUTHORIZED).build();

        entrevistaService.remover(entrevista);

        return Response.ok().build();
    }


}
