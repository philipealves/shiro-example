package br.com.pws.shiro.controller;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.LockedAccountException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.session.Session;
import org.apache.shiro.subject.Subject;

import br.com.pws.shiro.dto.ErrorResponse;
import br.com.pws.shiro.dto.UserDto;

@Path("/authentication")
public class AuthenticationController {

    @POST
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Response login(UserDto user) {
        Subject currentUser = SecurityUtils.getSubject();

        Session session = currentUser.getSession();
        session.setAttribute("username", user.getUsername());

        if (!currentUser.isAuthenticated()) {
            UsernamePasswordToken token = new UsernamePasswordToken(user.getPassword(), user.getPassword());

            token.setRememberMe(user.isRememberMe());

            // TODO: Tratar retorno de mensagens
            try {
                currentUser.login(token);
            } catch (UnknownAccountException uae) {
                return Response.status(401).entity(new ErrorResponse("ERROR", "Usuário ou senha inválido.")).build();
            } catch (IncorrectCredentialsException ice) {
                return Response.status(401).entity(new ErrorResponse("ERROR", "Usuário ou senha inválido.")).build();
            } catch (LockedAccountException lae) {
                return Response.status(401).entity(new ErrorResponse("ERROR", "Usuuário bloqueado.")).build();
            } catch (AuthenticationException ae) {
                return Response.status(401).entity(new ErrorResponse("ERROR", "Erro ao efetuar login.")).build();
            }
        }

        return Response.status(200).entity(user).build();
    }

    @POST
    @Path("/isLogged")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public boolean isLogged() {
        Subject currentUser = SecurityUtils.getSubject();

        if (currentUser.isAuthenticated())
            return true;

        return false;
    }

    @POST
    @Path("/currentUser")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Response getCurrentUser() {
        Subject currentUser = SecurityUtils.getSubject();

        if (currentUser.isAuthenticated()) {
            UserDto dto = new UserDto();
            dto.setUsername(currentUser.getPrincipal().toString());
            return Response.status(200).entity(dto).build();
        }

        return Response.status(200).build();
    }

    @POST
    @Path("/logout")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Response logout() {
        Subject currentUser = SecurityUtils.getSubject();

        if (currentUser.isAuthenticated()) {
            currentUser.logout();
            return Response.status(200).build();
        }
        return Response.status(200).build();
    }

}
