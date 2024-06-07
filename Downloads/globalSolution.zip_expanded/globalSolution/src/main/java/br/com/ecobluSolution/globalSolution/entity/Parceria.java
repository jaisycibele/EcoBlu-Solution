package br.com.ecobluSolution.globalSolution.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name = "PARCERIA")
public class Parceria {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "PARCERIAID")
    private Long parceriaId;

    @Column(name = "NOMEPARCERIO", nullable = false, length = 100)
    private String nomeParceiro;

    @Column(name = "TIPOPARCERIO", nullable = false, length = 100)
    private String tipoParceiro;

    @Column(name = "DESCRICAO", nullable = false, columnDefinition = "CLOB")
    private String descricao;
    
    
}
