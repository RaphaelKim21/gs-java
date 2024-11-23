package org.example.controller;

import org.example.dto.UsuarioDto;
import org.example.exceptions.*;
import org.example.model.Usuario;
import org.example.service.UsuarioService;
import org.example.service.UsuarioServiceFactory;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.sql.SQLException;
import java.util.Map;

@Path("/usuario")
public class UsuarioController {

    private UsuarioService usuarioService = UsuarioServiceFactory.create();

    @GET
    @Path("/localizar-todos")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getAllUsuarios() {

        return Response
                .status(Response.Status.OK)
                .entity(this.usuarioService.listarTodos())
                .build();
    }

    @POST
    @Path("")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Response addUsuario(UsuarioDto dto){
        try {
            System.out.println("Ricardo");
            Usuario usuario = null;
            try {
                usuario = new Usuario(dto.getId(), dto.getNome(), dto.getEmail(), dto.getSenha(), dto.getTipo());
            } catch (UsuarioTipoInvalido e) {
                throw new RuntimeException(e);
            }
            usuario = this.usuarioService.create(usuario);
            return Response.status(Response.Status.CREATED)
                    .entity(usuario).build();
        }catch (SQLException | UsuarioNotSavedException s){
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
                    .entity(Map.of("messagem","não foi possível salvar o registro"))
                    .build();
        }
    }

    @PUT
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Response updatePessoa(@PathParam("id") Long id,UsuarioDto dto){
        try {
            Usuario usuario = this.usuarioService
                    .update(new Usuario(id, dto.getNome(), dto.getEmail(), dto.getSenha(), dto.getTipo()));
            return Response.status(Response.Status.OK)
                    .entity(usuario).build();
        } catch (UsuarioNotFound e) {
            return Response.status(Response.Status.NOT_FOUND)
                    .entity(Map.of("mensagem","id não existe")).build();
        } catch (UsuarioNotUpdatedException | UsuarioTipoInvalido u){
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
                    .entity(Map.of("messagem","não foi possível atualizar o registro"))
                    .build();
        }
    }

    @DELETE
    @Path("/{id}")
    public Response deletePessoa(@PathParam("id") Long id){
        try{
            this.usuarioService.delete(id);
            return Response.status(Response.Status.NO_CONTENT).build();
        } catch (UsuarioNotFound e){
            return Response.status(Response.Status.NOT_FOUND).build();
        } catch (SQLException s){
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
                    .entity(Map.of("messagem","não foi possível deletar o registro"))
                    .build();
        }

    }

}
