package br.com.ecobluSolution.globalSolution.services.dto;

import java.util.Date;

import org.springframework.hateoas.Link;

import br.com.ecobluSolution.globalSolution.entity.Usuario;

public record TreinamentoDTO(
		
		Long treinamentoId,
		String titulo,
		String descricao,
		Date dataInicio,
		Date dataFim,
		Usuario usuario,
		Link link
		) {

}
