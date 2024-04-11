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
@Table(name = "tb_usuario")
@Getter @Setter
@ToString
public class Usuario {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idUsuario;
    
    @Column(name = "nome")
    private String nome;
    
    @Column(name = "email")
    private String email;
    
    @Column(name = "senha")
    private String senha;
    
    @Column(name = "ds_chave_google_ads")
    private String chaveGoogleAds;

    // Construtor para criar um usuário com todos os campos
    public Usuario(String nome, String email, String senha, String chaveGoogleAds) {
        this.nome = nome;
        this.email = email;
        this.senha = senha;
        this.chaveGoogleAds = chaveGoogleAds;
    }

    // Construtor padrão vazio para uso do JPA
    public Usuario() {}


}
