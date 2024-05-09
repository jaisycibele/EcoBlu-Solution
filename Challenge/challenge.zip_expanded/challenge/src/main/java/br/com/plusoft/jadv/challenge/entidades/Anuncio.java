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
@Table(name = "tb_anuncio")
@Getter @Setter
@ToString
public class Anuncio {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long idAnuncio;
	
	@Column(name="Titulo")
	private String titulo;
	
	@Column(name="ds_texto_anuncio")
	private String textoAnuncio;
	
	@Column(name="ds_url_anuncio")
	private String urlAnuncio;
	
	@Column(name="tipo_anuncio")
	private String tipoAnuncio;
	
	@Column(name="data_criacao_anuncio")
	private String dataCriacao;
	
	@Column(name="impressoes")
	private Long impressoes;
	
	@Column(name="qtd_cliques")
	private Long qtdCliques;
	
	@Column(name="custo_anuncio")
	private Long custoAnuncio;
	
}
