package br.com.plusoft.jadv.challenge.servicos.dtos;

import br.com.plusoft.jadv.challenge.entidades.Campanha;
import jakarta.validation.constraints.NotBlank;

public record CampanhaDto(
		@NotBlank (message="Nome da campanha deve ser informado")
		String nomeCampanha,
		@NotBlank (message="Data Inicio da campanha deve ser informado")
		String dataInicio
		) {
	
	public CampanhaDto (Campanha campanha){
		this(campanha.getNomeCampanha(), campanha.getDataInicio());

}
}