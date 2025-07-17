package model;

import java.time.LocalDate;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import util.EStatus;
import util.IModel;

@Entity
public class Tarefa implements IModel<Long>{
	
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private LocalDate dataInicio;
	private LocalDate dataFim;
	private String nome;
	
	@Enumerated(EnumType.STRING)
	private EStatus status;
	
	@ManyToOne
    @JoinColumn(name = "usuario_responsavel_id", nullable = false)
    private Usuario usuarioResponsavel;
	
	@ManyToOne
    @JoinColumn(name = "tarefa_principal_id") 
    private Tarefa tarefaPrincipal;
	
	

	@Override
	public Long getId() {
		// TODO Auto-generated method stub
		return this.id;
	}



	public LocalDate getDataInicio() {
		return dataInicio;
	}



	public void setDataInicio(LocalDate dataInicio) {
		this.dataInicio = dataInicio;
	}



	public LocalDate getDataFim() {
		return dataFim;
	}



	public void setDataFim(LocalDate dataFim) {
		this.dataFim = dataFim;
	}



	public String getNome() {
		return nome;
	}



	public void setNome(String nome) {
		this.nome = nome;
	}



	public EStatus getStatus() {
		return status;
	}



	public void setStatus(EStatus status) {
		this.status = status;
	}



	public Usuario getUsuarioResponsavel() {
		return usuarioResponsavel;
	}



	public void setUsuarioResponsavel(Usuario usuarioResponsavel) {
		this.usuarioResponsavel = usuarioResponsavel;
	}



	public Tarefa getTarefaPrincipal() {
		return tarefaPrincipal;
	}



	public void setTarefaPrincipal(Tarefa tarefaPrincipal) {
		this.tarefaPrincipal = tarefaPrincipal;
	}



	public void setId(Long id) {
		this.id = id;
	}
    
	
	

}
