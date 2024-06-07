package br.com.ecobluSolution.globalSolution.services.dto;

import java.util.Date;

import org.springframework.hateoas.Link;

public record MaterialRecicladoDTO(
		
		Long materialId,
		String tipoMaterial,
		String descricao,
		Float quantidade,
		Date dataColeta,
		String origem,
		Link link
		
		) {

}
