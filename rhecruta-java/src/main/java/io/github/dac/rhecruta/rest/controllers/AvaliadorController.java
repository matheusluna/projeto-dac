package io.github.dac.rhecruta.rest.controllers;

import io.github.dac.rhecruta.models.Avaliador;
import io.github.dac.rhecruta.service.AvaliadorService;

import javax.ejb.EJB;
import javax.ws.rs.*;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;
import java.net.URI;

@Path("avaliador")
public class AvaliadorController {

    @EJB
    private AvaliadorService avaliadorService;

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response retornarAvaliadores() {
        return Response
                .ok(avaliadorService.listarTodos())
                .build();
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    public Response cadastrarAvaliador(Avaliador avaliador, @Context UriInfo uriInfo) {

        String cpf = this.avaliadorService.salvar(avaliador);
        URI location = uriInfo.getAbsolutePathBuilder().path(cpf).build();

        return Response.created(location).build();
    }

    //TODO Método de Atualização

    @DELETE
    @Path("{cpf}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response removerAvaliadorComCPF(@PathParam("cpf") String cpf) {

        Avaliador avaliador = new Avaliador();
        avaliador.setCpf(cpf);

        this.avaliadorService.remover(avaliador);

        return Response.ok().build();
    }

    @GET
    @Path("{cpf}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response avaliadorComCPF(@PathParam("cpf") String cpf) {

        if (cpf == null || cpf.isEmpty() || cpf.length() < 11 || cpf.length() > 14)
            return Response.status(Response.Status.BAD_REQUEST).build();

        return Response.ok(
                this.avaliadorService.avaliadorComCPF(cpf)
        ).build();
    }

    @GET
    @Path("buscar/{email}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response avaladorComEmail(@PathParam("email") String email) {

        if (email == null || email.isEmpty() || email.length() <= 0 || !email.contains("@"))
            return Response.status(Response.Status.BAD_REQUEST).build();

        return Response.ok(
                this.avaliadorService.avaliadorComEmail(email)
        ).build();
    }
}
