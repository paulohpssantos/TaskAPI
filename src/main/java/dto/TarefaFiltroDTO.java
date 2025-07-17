package dto;

public class TarefaFiltroDTO {
	
	 private String inicio;
	 private String fim;
	 private Long userId;
	 private String status;
	 private boolean atrasadas;
	 public String getInicio() {
		 return inicio;
	 }
	 public void setInicio(String inicio) {
		 this.inicio = inicio;
	 }
	 public String getFim() {
		 return fim;
	 }
	 public void setFim(String fim) {
		 this.fim = fim;
	 }
	 public Long getUserId() {
		 return userId;
	 }
	 public void setUserId(Long userId) {
		 this.userId = userId;
	 }
	 public String getStatus() {
		 return status;
	 }
	 public void setStatus(String status) {
		 this.status = status;
	 }
	 public boolean isAtrasadas() {
		 return atrasadas;
	 }
	 public void setAtrasadas(boolean atrasadas) {
		 this.atrasadas = atrasadas;
	 }
	 
	 

}
