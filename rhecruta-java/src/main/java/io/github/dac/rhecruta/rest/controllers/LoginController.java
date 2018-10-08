package io.github.dac.rhecruta.rest.controllers;

import io.github.dac.rhecruta.models.Candidato;
import io.github.dac.rhecruta.rest.infraSecurity.TokenManagement;
import io.github.dac.rhecruta.service.CandidatoService;

import javax.ejb.EJB;
import javax.ws.rs.Consumes;
import javax.ws.rs.FormParam;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("login")
public class LoginController {

    @EJB
    private CandidatoService candidatoService;

    @POST
    @Consumes(MediaType.APPLICATION_FORM_URLENCODED)
    public Response realizarLogin(@FormParam("email") String email,
                                  @FormParam("senha") String senha) {

        if (email == null || email.isEmpty() || !email.contains("@"))
            return Response.status(Response.Status.BAD_REQUEST).build();

        if (senha == null || senha.isEmpty())
            return Response.status(Response.Status.BAD_REQUEST).build();

        if (!candidatoService.login(email, senha))
            return Response.status(Response.Status.UNAUTHORIZED).build();

        Candidato candidato = candidatoService.candidatoComEmail(email);

        String token = new TokenManagement().gerarToken(email, 1);

        candidato.setSenha(token);

        return Response.ok(candidato).build();
    }

}
