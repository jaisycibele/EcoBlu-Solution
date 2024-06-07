package br.com.ecobluSolution.globalSolution.entity;

import jakarta.persistence.*;
import lombok.Builder;

import java.util.Date;

@Builder
@Entity
@Table(name = "TREINAMENTO")
public class Treinamento {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "TREINAMENTOID")
    private Long treinamentoId;

    @Column(name = "TITULO", nullable = false, length = 100)
    private String titulo;

    @Column(name = "DESCRICAO", nullable = false, columnDefinition = "CLOB")
    private String descricao;

    @Column(name = "DATANINICIO", nullable = false)
    private Date dataInicio;

    @Column(name = "DATAFIM", nullable = false)
    private Date dataFim;

    @ManyToOne(fetch = FetchType.EAGER, cascade = {CascadeType.MERGE})
    @JoinColumn(name = "USUARIOID", 
    nullable = false,
    referencedColumnName = "USUARIOID",
    foreignKey = @ForeignKey(name = "FK_TREINAMENTO_USUARIO"))
    private Usuario usuario;

	public Long getTreinamentoId() {
		return treinamentoId;
	}

	public void setTreinamentoId(Long treinamentoId) {
		this.treinamentoId = treinamentoId;
	}

	public String getTitulo() {
		return titulo;
	}

	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public Date getDataInicio() {
		return dataInicio;
	}

	public void setDataInicio(Date dataInicio) {
		this.dataInicio = dataInicio;
	}

	public Date getDataFim() {
		return dataFim;
	}

	public void setDataFim(Date dataFim) {
		this.dataFim = dataFim;
	}

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	public Treinamento(Long treinamentoId, String titulo, String descricao, Date dataInicio, Date dataFim,
			Usuario usuario) {
		super();
		this.treinamentoId = treinamentoId;
		this.titulo = titulo;
		this.descricao = descricao;
		this.dataInicio = dataInicio;
		this.dataFim = dataFim;
		this.usuario = usuario;
	}

	public Treinamento() {
		super();
	}

	
	
}

