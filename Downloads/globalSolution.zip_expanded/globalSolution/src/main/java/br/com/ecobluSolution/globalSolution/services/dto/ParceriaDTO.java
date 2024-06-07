package br.com.ecobluSolution.globalSolution.services.dto;

import org.springframework.hateoas.Link;

public record ParceriaDTO(
		
		Long parceriaId,
		String nomeParceiro,
		String tipoParceiro,
		String descricao, 
		Link link
		
		) {

}
