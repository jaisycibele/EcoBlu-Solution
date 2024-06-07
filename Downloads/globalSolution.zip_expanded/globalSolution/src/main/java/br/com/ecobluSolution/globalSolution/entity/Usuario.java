package br.com.ecobluSolution.globalSolution.entity;

import java.util.Date;


import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

import jakarta.persistence.Table;
import lombok.Builder;

@Builder
@Entity
@Table(name = "USUARIO")
public class Usuario {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "USUARIOID")
    private Long usuarioId;

    @Column(name = "NOME", nullable = false, length = 100)
    private String nome;

    @Column(name = "EMAIL", nullable = false, length = 100, unique = true)
    private String email;

    @Column(name = "SENHA", nullable = false, length = 100)
    private String senha;

    @Column(name = "TIPOUSUARIOID", nullable = false)
    private Integer tipoUsuarioId;

    @Column(name = "DATA_REGISTRO", nullable = false)
    private Date dataRegistro;

	public Long getUsuarioId() {
		return usuarioId;
	}

	public void setUsuarioId(Long usuarioId) {
		this.usuarioId = usuarioId;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getSenha() {
		return senha;
	}

	public void setSenha(String senha) {
		this.senha = senha;
	}

	public Integer getTipoUsuarioId() {
		return tipoUsuarioId;
	}

	public void setTipoUsuarioId(Integer tipoUsuarioId) {
		this.tipoUsuarioId = tipoUsuarioId;
	}

	public Date getDataRegistro() {
		return dataRegistro;
	}

	public void setDataRegistro(Date dataRegistro) {
		this.dataRegistro = dataRegistro;
	}


	public Usuario(Long usuarioId, String nome, String email, String senha, Integer tipoUsuarioId, Date dataRegistro) {
		super();
		this.usuarioId = usuarioId;
		this.nome = nome;
		this.email = email;
		this.senha = senha;
		this.tipoUsuarioId = tipoUsuarioId;
		this.dataRegistro = dataRegistro;
		
	}

	public Usuario() {
		super();
	}

	
	
    
}
