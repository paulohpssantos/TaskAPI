package resource;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;
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
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import dto.TarefaFiltroDTO;
import model.Tarefa;
import service.TarefaService;
import util.EStatus;

@Named
@Path("tarefa")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class TarefaResource {
	
	@Inject
	private TarefaService service;
	
	@POST
	@Path("listar")
	public Response listar(TarefaFiltroDTO filtro) {
	    try {
	        // Filtro por tarefas atrasadas
	        if (filtro.isAtrasadas()) {
	            List<Tarefa> tarefas = service.findTarefasAtrasadas();
	            return Response.ok(tarefas).build();
	        }

	        // Filtro por usuário e status
	        if (filtro.getUserId() != null) {
	            EStatus eStatus = null;
	            if (filtro.getStatus() != null && !filtro.getStatus().isEmpty()) {
	                eStatus = EStatus.valueOf(filtro.getStatus().toUpperCase());
	            }
	            List<Tarefa> tarefas = service.findByUsuarioStatus(filtro.getUserId(), eStatus);
	            return Response.ok(tarefas).build();
	        }

	        // Filtro por data de início e fim
	        if ((filtro.getInicio() != null && !filtro.getInicio().isEmpty()) || (filtro.getFim() != null && !filtro.getFim().isEmpty())) {
	            LocalDate inicio = filtro.getInicio() != null && !filtro.getInicio().isEmpty() ? LocalDate.parse(filtro.getInicio()) : null;
	            LocalDate fim = filtro.getFim() != null && !filtro.getFim().isEmpty() ? LocalDate.parse(filtro.getFim()) : null;
	            List<Tarefa> tarefas = service.findByDataInicioFim(inicio, fim);
	            return Response.ok(tarefas).build();
	        }

	        // Se nenhum filtro for informado, retorna todas as tarefas
	        List<Tarefa> tarefas = service.findAll();
	        return Response.ok(tarefas).build();
	        
	    } catch (DateTimeParseException e) {
	        return Response.status(Response.Status.BAD_REQUEST)
	                       .entity("Formato de data inválido. Use: yyyy-MM-dd")
	                       .build();
	    } catch (Exception e) {
	        return Response.status(Response.Status.BAD_REQUEST)
	                .entity("Instabilidade no serviço").build();
	    }
	}
	
	
	
	@POST
    @Path("cadastrar")
    public Response salvar(Tarefa tarefa) {
        try {

        	service.create(tarefa);
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
    public Response atualizar(Tarefa tarefa) {
        try {

        	service.update(tarefa);
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
    public Response remover(Tarefa tarefa) {
        try {

        	service.remove(tarefa);
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