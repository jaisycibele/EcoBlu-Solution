package br.com.plusoft.jadv.challenge.servicos.dtos;

import br.com.plusoft.jadv.challenge.entidades.Anuncio;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AnuncioDto {
    
	 @NotBlank(message = "Titulo do anuncio deve ser informado")
	    private String titulo;
	    
	    @NotBlank(message = "Texto do anuncio deve ser informado")
	    private String textoAnuncio;
	    
	    @NotBlank(message = "URL do anuncio deve ser informado")
	    private String urlAnuncio;
	    
	    private String tipoAnuncio;
	    private String dataCriacao;
	    private Long impressoes;
	    private Long qtdCliques;
	    
	    @NotBlank(message = "Custo do anuncio deve ser informado")
	    private Long custoAnuncio;
	    
	    public AnuncioDto(String titulo, String textoAnuncio, String urlAnuncio, String tipoAnuncio, String dataCriacao, Long impressoes, Long qtdCliques, Long custoAnuncio) {
	        this.titulo = titulo;
	        this.textoAnuncio = textoAnuncio;
	        this.urlAnuncio = urlAnuncio;
	        this.tipoAnuncio = tipoAnuncio;
	        this.dataCriacao = dataCriacao;
	        this.impressoes = impressoes;
	        this.qtdCliques = qtdCliques;
	        this.custoAnuncio = custoAnuncio;
	    }
	    
	    public AnuncioDto(Anuncio anuncio) {
	        this(anuncio.getTitulo(), anuncio.getTextoAnuncio(), anuncio.getUrlAnuncio(), anuncio.getTipoAnuncio(), anuncio.getDataCriacao(), anuncio.getImpressoes(), anuncio.getQtdCliques(), anuncio.getCustoAnuncio());
	    }
}
