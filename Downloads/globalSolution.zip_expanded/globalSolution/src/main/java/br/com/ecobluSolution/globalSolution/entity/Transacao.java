package br.com.ecobluSolution.globalSolution.entity;

import jakarta.persistence.*;
import lombok.Builder;

import java.util.Date;

@Builder
@Entity
@Table(name = "TRANSACAO")
public class Transacao {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "TRANSACAOID")
    private Long transacaoId;

    @Column(name = "DATATRANSACAO", nullable = false)
    private Date dataTransacao;

    @Column(name = "QUANTIDADE", nullable = false)
    private Float quantidade;

    @Column(name = "STATUS", nullable = false, length = 50)
    private String status;

    @ManyToOne(fetch = FetchType.EAGER, cascade = {CascadeType.MERGE})
    @JoinColumn(name = "USUARIOID", 
    		nullable = false,
    	    referencedColumnName = "USUARIOID",
    	    foreignKey = @ForeignKey(name = "FK_TRANSACAO_USUARIO"))
    private Usuario usuario;

    @ManyToOne(fetch = FetchType.EAGER, cascade = {CascadeType.ALL})
    @JoinColumn(name = "MATERIALID", 
    		nullable = false,
    	    referencedColumnName = "MATERIALID",
    	    foreignKey = @ForeignKey(name = "FK_TRANSACAO_MATERIAL"))
    private MaterialReciclado materialReciclado;

	public Long getTransacaoId() {
		return transacaoId;
	}

	public void setTransacaoId(Long transacaoId) {
		this.transacaoId = transacaoId;
	}

	public Date getDataTransacao() {
		return dataTransacao;
	}

	public void setDataTransacao(Date dataTransacao) {
		this.dataTransacao = dataTransacao;
	}

	public Float getQuantidade() {
		return quantidade;
	}

	public void setQuantidade(Float quantidade) {
		this.quantidade = quantidade;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	public MaterialReciclado getMaterialReciclado() {
		return materialReciclado;
	}

	public void setMaterialReciclado(MaterialReciclado materialReciclado) {
		this.materialReciclado = materialReciclado;
	}

	public Transacao(Long transacaoId, Date dataTransacao, Float quantidade, String status, Usuario usuario,
			MaterialReciclado materialReciclado) {
		super();
		this.transacaoId = transacaoId;
		this.dataTransacao = dataTransacao;
		this.quantidade = quantidade;
		this.status = status;
		this.usuario = usuario;
		this.materialReciclado = materialReciclado;
	}

	public Transacao() {
		super();
	}
    
	
    
}
