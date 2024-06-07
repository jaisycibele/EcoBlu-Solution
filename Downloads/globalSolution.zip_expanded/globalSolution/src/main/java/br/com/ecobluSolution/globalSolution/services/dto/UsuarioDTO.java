package br.com.ecobluSolution.globalSolution.services.dto;

import java.util.Date;

import org.springframework.hateoas.Link;
public record UsuarioDTO(
		Long usuarioId,
		String nome, 
		String email,
		String senha,
		Integer tipoUsuarioId,
		Date dataRegistro,
		Link link
		) {

}
