package br.com.plusoft.jadv.challenge.entidades;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Entity
@Table(name = "tb_campanha")
@Getter @Setter
@ToString
public class Campanha {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long idCampanha;
	
	@Column(name="nome_campanha")
	private String nomeCampanha;
	
	@Column(name="orcamento")
	private String orcamento;
	
	@Column(name="data_inicio")
	private String dataInicio;
	
	@Column(name="data_termino")
	private String dataTermino;
	
	@Column(name="status")
	private String status;
	
	@Column(name="tipo_campanha")
	private String tipoCampanha;
	

}
