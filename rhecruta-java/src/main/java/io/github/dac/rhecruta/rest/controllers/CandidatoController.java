package io.github.dac.rhecruta.rest.controllers;

import io.github.dac.rhecruta.models.Candidato;
import io.github.dac.rhecruta.rest.infraSecurity.Security;
import io.github.dac.rhecruta.rest.infraSecurity.TokenManagement;
import io.github.dac.rhecruta.service.CandidatoService;

import javax.ejb.EJB;
import javax.ws.rs.*;
import javax.ws.rs.core.*;
import java.net.URI;
import java.util.List;

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

        String cpf = this.candidatoService.salvar(candidato);
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

        Candidato candidato = this.candidatoService.candidatoComCPF(cpf);

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

        Candidato candidato = this.candidatoService.candidatoComEmail(email);

        if (candidato == null)
            return Response.status(Response.Status.NO_CONTENT).build();
        else
            return Response.ok(candidato).build();
    }

    @GET
    @Path("interesse")
    @Security
    @Produces(MediaType.APPLICATION_JSON)
    public Response recuperarInteresses(@Context SecurityContext securityContext) {

        String token = TokenManagement.getToken(securityContext);

        Candidato candidato = this.candidatoService.candidatoComEmail(token);

        List<Integer> interesses = candidato.getInteresses();

        if (interesses != null && interesses.size() > 0)
            return Response.ok(interesses).build();
        else
            return Response.noContent().build();

    }

    @POST
    @Path("interesse")
    @Security
    @Consumes(MediaType.APPLICATION_FORM_URLENCODED)
    public Response adicionarInteresse(@FormParam("idVaga") Integer idVaga,
                                       @Context SecurityContext securityContext) {

        if (idVaga == null || idVaga <= 0)
            return Response.status(Response.Status.BAD_REQUEST).build();

        String token = TokenManagement.getToken(securityContext);

        Candidato candidato = this.candidatoService.candidatoComEmail(token);

        if (candidato.getInteresses().contains(idVaga))
            return Response.status(Response.Status.NOT_MODIFIED).build();


        candidato.adicionarInteresse(idVaga);

        candidatoService.atualizar(candidato);

        return Response.ok().build();

    }

    @DELETE
    @Path("interesse/{idVaga}")
    @Security
    public Response removerInteresse(@PathParam("idVaga") Integer idVaga,
                                     @Context SecurityContext securityContext) {

        if (idVaga == null || idVaga <= 0)
            return Response.status(Response.Status.BAD_REQUEST).build();

        String token = TokenManagement.getToken(securityContext);

        Candidato candidato = this.candidatoService.candidatoComEmail(token);

        if (!candidato.getInteresses().contains(idVaga))
            return Response.status(Response.Status.NOT_MODIFIED).build();

        candidato.removerInteresse(idVaga);

        this.candidatoService.atualizar(candidato);

        return Response.ok().build();

    }

}
