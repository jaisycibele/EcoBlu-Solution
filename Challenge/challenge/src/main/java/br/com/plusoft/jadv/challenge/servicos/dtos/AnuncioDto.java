package br.com.plusoft.jadv.challenge.servicos.dtos;

import br.com.plusoft.jadv.challenge.entidades.Anuncio;
import jakarta.validation.constraints.NotBlank;

public record AnuncioDto(
		@NotBlank(message="Titulo do anuncio deve ser informado")
		String titulo,
		@NotBlank(message="Texto do anuncio deve ser informado")
		String textoAnuncio,
		@NotBlank(message="URL do anuncio deve ser informado")
		String urlAnuncio,
		@NotBlank(message="Custo do anuncio deve ser informado")
		Long custoAnuncio 
		) {

	public AnuncioDto(Anuncio anuncio) {
		this(anuncio.getTitulo(), anuncio.getTextoAnuncio(), anuncio.getUrlAnuncio(), anuncio.getCustoAnuncio());
	}
}
