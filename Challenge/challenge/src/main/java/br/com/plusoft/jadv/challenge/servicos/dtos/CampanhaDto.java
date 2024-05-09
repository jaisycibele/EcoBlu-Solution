package br.com.plusoft.jadv.challenge.servicos.dtos;

import br.com.plusoft.jadv.challenge.entidades.Campanha;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CampanhaDto {
    
    @NotBlank(message = "Nome da campanha deve ser informado")
    private String nomeCampanha;
    
    @NotBlank(message = "Data Inicio da campanha deve ser informado")
    private String dataInicio;
    
    @NotBlank(message = "Orçamento da campanha deve ser informado")
    private String orcamento;
    
    private String dataTermino;
    private String status;
    private String tipoCampanha;
    
    public CampanhaDto(String nomeCampanha, String dataInicio, String orcamento, String dataTermino, String status, String tipoCampanha) {
        this.nomeCampanha = nomeCampanha;
        this.dataInicio = dataInicio;
        this.orcamento = orcamento;
        this.dataTermino = dataTermino;
        this.status = status;
        this.tipoCampanha = tipoCampanha;
    }
    
    public CampanhaDto(Campanha campanha) {
        this(campanha.getNomeCampanha(), campanha.getDataInicio(), campanha.getOrcamento(), campanha.getDataTermino(), campanha.getStatus(), campanha.getTipoCampanha());
    }
}
