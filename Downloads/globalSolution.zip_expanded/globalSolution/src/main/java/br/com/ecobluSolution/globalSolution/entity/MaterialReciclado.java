package br.com.ecobluSolution.globalSolution.entity;

import jakarta.persistence.*;
import lombok.Builder;

import java.util.Date;


@Builder
@Entity
@Table(name = "MATERIALRECICLADO")
public class MaterialReciclado {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "MATERIALID")
    private Long materialId;

    @Column(name = "TIPOMATERIAL",  length = 100)
    private String tipoMaterial;

    @Column(name = "DESCRICAO",columnDefinition = "CLOB")
    private String descricao;

    @Column(name = "QUANTIDADE")
    private Float quantidade;

    @Column(name = "DATACOLETA")
    private Date dataColeta;

    @Column(name = "ORIGEM",  length = 100)
    private String origem;


	public Long getMaterialId() {
		return materialId;
	}

	public void setMaterialId(Long materialId) {
		this.materialId = materialId;
	}

	public String getTipoMaterial() {
		return tipoMaterial;
	}

	public void setTipoMaterial(String tipoMaterial) {
		this.tipoMaterial = tipoMaterial;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public Float getQuantidade() {
		return quantidade;
	}

	public void setQuantidade(Float quantidade) {
		this.quantidade = quantidade;
	}

	public Date getDataColeta() {
		return dataColeta;
	}

	public void setDataColeta(Date dataColeta) {
		this.dataColeta = dataColeta;
	}

	public String getOrigem() {
		return origem;
	}

	public void setOrigem(String origem) {
		this.origem = origem;
	}


	public MaterialReciclado(Long materialId, String tipoMaterial, String descricao, Float quantidade, Date dataColeta,
			String origem) {
		super();
		this.materialId = materialId;
		this.tipoMaterial = tipoMaterial;
		this.descricao = descricao;
		this.quantidade = quantidade;
		this.dataColeta = dataColeta;
		this.origem = origem;
		
	}

	public MaterialReciclado() {
		super();
	}
	
	
    
    
}
