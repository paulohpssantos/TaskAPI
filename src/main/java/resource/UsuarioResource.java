package resource;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import javax.inject.Inject;
import javax.inject.Named;
import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import model.Usuario;
import service.UsuarioService;

@Named
@Path("usuario")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class UsuarioResource {
	
	@Inject
	private UsuarioService service;
	
	@GET
    @Path("listar")
    public List<Usuario> listaUsuarios(){
            return service.findAll();

    }
	
	@POST
    @Path("cadastrar")
    public Response salvar(Usuario usuario) {
        try {

        	service.create(usuario);
        return Response.ok().build();

        }catch (ConstraintViolationException cvex) {
            final Set<String> erros = 
                    cvex.getConstraintViolations().stream()
                    .map(ConstraintViolation::getMessage)
                    .collect(Collectors.toSet());

            return Response.status(Response.Status.BAD_REQUEST)
                    .entity(erros).build();

        }catch (Exception e) {
            return Response.status(Response.Status.BAD_REQUEST)
                    .entity("Instabilidade no serviço").build();

        }

    }
	
	@POST
    @Path("atualizar")
    public Response atualizar(Usuario usuario) {
        try {

        	service.update(usuario);
        return Response.ok().build();

        }catch (ConstraintViolationException cvex) {
            final Set<String> erros = 
                    cvex.getConstraintViolations().stream()
                    .map(ConstraintViolation::getMessage)
                    .collect(Collectors.toSet());

            return Response.status(Response.Status.BAD_REQUEST)
                    .entity(erros).build();

        }catch (Exception e) {
            return Response.status(Response.Status.BAD_REQUEST)
                    .entity("Instabilidade no serviço").build();

        }

    }
	
	@POST
    @Path("remover")
    public Response remover(Usuario usuario) {
        try {

        	service.remove(usuario);
        return Response.ok().build();

        }catch (ConstraintViolationException cvex) {
            final Set<String> erros = 
                    cvex.getConstraintViolations().stream()
                    .map(ConstraintViolation::getMessage)
                    .collect(Collectors.toSet());

            return Response.status(Response.Status.BAD_REQUEST)
                    .entity(erros).build();

        }catch (Exception e) {
            return Response.status(Response.Status.BAD_REQUEST)
                    .entity("Instabilidade no serviço").build();

        }

    }

}