package br.com.ecobluSolution.globalSolution.services.dto;

import java.util.Date;

import org.springframework.hateoas.Link;

import br.com.ecobluSolution.globalSolution.entity.MaterialReciclado;
import br.com.ecobluSolution.globalSolution.entity.Usuario;

public record TransacaoDTO(
		
		Long transacaoId,
		Date dataTransacao,
		Float quantidade,
		String status,
		Usuario usuario,
		MaterialReciclado materialReciclado,
		Link link
		) {

}
