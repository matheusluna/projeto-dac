package io.github.dac.rhecruta.rest.infraSecurity.filters;

import io.github.dac.rhecruta.rest.infraSecurity.Security;
import io.github.dac.rhecruta.rest.infraSecurity.TokenManagement;
import io.jsonwebtoken.Claims;

import javax.annotation.Priority;
import javax.ws.rs.NotAuthorizedException;
import javax.ws.rs.Priorities;
import javax.ws.rs.container.ContainerRequestContext;
import javax.ws.rs.container.ContainerRequestFilter;
import javax.ws.rs.core.HttpHeaders;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.SecurityContext;
import javax.ws.rs.ext.Provider;
import java.io.IOException;
import java.security.Principal;

@Security
@Provider
@Priority(Priorities.AUTHENTICATION)
public class FilterSecurityAuthentication implements ContainerRequestFilter {

    @Override
    public void filter(ContainerRequestContext requestContext) throws IOException {

        String authorizationHeader = requestContext.getHeaderString(HttpHeaders.AUTHORIZATION);
        try {
            if (authorizationHeader == null || !authorizationHeader.startsWith("Bearer "))
                throw new NotAuthorizedException("Necessário Informar Authorization Header Para Acessar Este Recurso.");

            String token = authorizationHeader.substring("Bearer".length()).trim();

            Claims claims = new TokenManagement().validaToken(token);

            if (claims == null)
                throw new NotAuthorizedException("Token inválido");

            modificarRequestContext(requestContext, claims.getIssuer());
        } catch (NotAuthorizedException ex) {
            ex.printStackTrace();
            requestContext.abortWith(
                    Response.status(Response.Status.UNAUTHORIZED).build());
        }
    }

    private void modificarRequestContext(ContainerRequestContext requestContext, String indentificador) {

        final SecurityContext currentSecurityContext = requestContext.getSecurityContext();

        requestContext.setSecurityContext(new SecurityContext() {
            @Override
            public Principal getUserPrincipal() {
                return () -> indentificador;
            }

            @Override
            public boolean isUserInRole(String role) {
                return true;
            }

            @Override
            public boolean isSecure() {
                return currentSecurityContext.isSecure();
            }

            @Override
            public String getAuthenticationScheme() {
                return "Bearer";
            }

        });
    }

}
