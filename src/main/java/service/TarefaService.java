package service;

import java.time.LocalDate;
import java.util.List;

import javax.inject.Named;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import model.Tarefa;
import util.EStatus;


@Named
public class TarefaService extends AbstractService<Tarefa, Long>{
	
	
	
	
   /**
    * Buscar por data inicial e final
    * @param inicio
    * @param fim
    * @return
    */
	public List<Tarefa> findByDataInicioFim(LocalDate inicio, LocalDate fim) {
	    CriteriaBuilder cb = entityManager.getCriteriaBuilder();
	    CriteriaQuery<Tarefa> cq = cb.createQuery(Tarefa.class);
	    Root<Tarefa> root = cq.from(Tarefa.class);

	    Predicate pred = cb.conjunction();

	    if (inicio != null) {
	        pred = cb.and(pred, cb.greaterThanOrEqualTo(root.get("dataInicio"), inicio));
	    }

	    if (fim != null) {
	        pred = cb.and(pred, cb.lessThanOrEqualTo(root.get("dataFim"), fim));
	    }

	    cq.where(pred);

	    return entityManager.createQuery(cq).getResultList();
	}
	
	/**
	 * Filtro para tarefas atrasadas
	 * @return
	 */
	public List<Tarefa> findTarefasAtrasadas() {
	    CriteriaBuilder cb = entityManager.getCriteriaBuilder();
	    CriteriaQuery<Tarefa> cq = cb.createQuery(Tarefa.class);
	    Root<Tarefa> root = cq.from(Tarefa.class);

	    Predicate dataFimMenorHoje = cb.lessThan(root.get("dataFim"), LocalDate.now());
	    Predicate naoEncerrada = cb.notEqual(root.get("status"), EStatus.ENCERRADA);

	    cq.where(cb.and(dataFimMenorHoje, naoEncerrada));

	    return entityManager.createQuery(cq).getResultList();
	}
	
	
	/**
	 * Filtro para buscar por usuário responsável e/ou status
	 * @param userId
	 * @param status
	 * @return
	 */
	public List<Tarefa> findByUsuarioStatus(Long userId, EStatus status) {
	    CriteriaBuilder cb = entityManager.getCriteriaBuilder();
	    CriteriaQuery<Tarefa> cq = cb.createQuery(Tarefa.class);
	    Root<Tarefa> root = cq.from(Tarefa.class);

	    Predicate pred = cb.equal(root.get("usuarioResponsavel").get("id"), userId);

	    if (status != null) {
	        pred = cb.and(pred, cb.equal(root.get("status"), status));
	    }

	    cq.where(pred);

	    return entityManager.createQuery(cq).getResultList();
	}
	
}
