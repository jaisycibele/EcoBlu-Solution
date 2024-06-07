package br.com.ecobluSolution.globalSolution.controller;

import br.com.ecobluSolution.globalSolution.entity.Treinamento;
import br.com.ecobluSolution.globalSolution.services.dto.TreinamentoDTO;
import br.com.ecobluSolution.globalSolution.servicos.TreinamentoService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/treinamentos", produces = {"application/json"})
@Tag(name = "api-treinamento")
public class TreinamentoController {

    private final TreinamentoService treinamentoService;

    @Autowired
    public TreinamentoController(TreinamentoService treinamentoService) {
        this.treinamentoService = treinamentoService;
    }

    @Operation(summary = "Retorna todos os treinamentos em páginas de 5")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Busca realizada com sucesso"),
            @ApiResponse(responseCode = "204", description = "Nenhum treinamento encontrado", content = {
                    @Content(schema = @Schema())
            })
    })
    @GetMapping
    public ResponseEntity<Page<TreinamentoDTO>> buscarTreinamentos() {
        Page<TreinamentoDTO> treinamentosDTO = treinamentoService.buscarTreinamentos();
        if (treinamentosDTO.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } else {
            return ResponseEntity.ok(treinamentosDTO);
        }
    }

    @Operation(summary = "Retorna um treinamento específico por id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Busca realizada com sucesso"),
            @ApiResponse(responseCode = "204", description = "Nenhum treinamento encontrado para o id informado", content = {
                    @Content(schema = @Schema())
            })
    })
    @GetMapping("/{treinamentoId}")
    public ResponseEntity<TreinamentoDTO> buscarTreinamentoPorId(@PathVariable Long treinamentoId) {
        TreinamentoDTO treinamentoDTO = treinamentoService.buscarTreinamentoPorId(treinamentoId);
        if (treinamentoDTO == null) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } else {
            return ResponseEntity.ok(treinamentoDTO);
        }
    }

    @Operation(summary = "Grava um treinamento")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Treinamento gravado com sucesso"),
            @ApiResponse(responseCode = "400", description = "Erro de validação dos dados", content = {
                    @Content(schema = @Schema())
            })
    })
    @PostMapping
    public ResponseEntity<Treinamento> gravarTreinamento(@Valid @RequestBody Treinamento treinamento) {
        Treinamento treinamentoSalvo = treinamentoService.salvarTreinamento(treinamento);
        return ResponseEntity.status(HttpStatus.CREATED).body(treinamentoSalvo);
    }

    @Operation(summary = "Atualiza um treinamento com base no id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Treinamento atualizado com sucesso"),
            @ApiResponse(responseCode = "400", description = "Erro de validação dos dados", content = {
                    @Content(schema = @Schema())
            })
    })
    @PutMapping("/{id}")
    public ResponseEntity<Treinamento> atualizarTreinamento(@PathVariable Long id, @Valid @RequestBody Treinamento treinamento) {
        Treinamento treinamentoAtualizado = treinamentoService.atualizarTreinamento(id, treinamento);
        if (treinamentoAtualizado == null) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return ResponseEntity.status(HttpStatus.CREATED).body(treinamentoAtualizado);
    }

    @Operation(summary = "Exclui um treinamento com base no id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Treinamento excluído com sucesso", content = {
                    @Content(schema = @Schema())
            })
    })
    @DeleteMapping("/{treinamentoId}")
    public ResponseEntity<Void> deletarTreinamento(@PathVariable Long treinamentoId) {
        treinamentoService.deletarTreinamento(treinamentoId);
        return ResponseEntity.noContent().build();
    }
}
