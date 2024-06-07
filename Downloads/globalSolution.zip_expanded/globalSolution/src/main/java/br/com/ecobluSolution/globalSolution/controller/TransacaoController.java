package br.com.ecobluSolution.globalSolution.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.ecobluSolution.globalSolution.entity.Transacao;
import br.com.ecobluSolution.globalSolution.services.dto.TransacaoDTO;
import br.com.ecobluSolution.globalSolution.servicos.TransacaoService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;

@RestController
@RequestMapping(value = "/transacoes", produces = {"application/json"})
@Tag(name = "api-transacao")
public class TransacaoController {
	
	private final TransacaoService transacaoService;
	
	@Autowired
    public TransacaoController(TransacaoService transacaoService) {
        this.transacaoService = transacaoService;
    }
	
	@Operation(summary = "Retorna todos as transacoews em páginas de 5")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Busca realizada com sucesso"),
            @ApiResponse(responseCode = "204", description = "Nenhuma transaçao encontrado", content = {
                    @Content(schema = @Schema())
            })
    })
	@GetMapping
    public ResponseEntity<Page<TransacaoDTO>> buscarTransacoes() {
        Page<TransacaoDTO> transacoesDTO = transacaoService.buscarTransacoes();
        if (transacoesDTO.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } else {
            return ResponseEntity.ok(transacoesDTO);
        }
    }
    		
	@Operation(summary = "Retorna uma transacao específica por id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Busca realizada com sucesso"),
            @ApiResponse(responseCode = "204", description = "Nenhuma transaçao encontrado para o id informado", content = {
                    @Content(schema = @Schema())
            })
    })
	@GetMapping("/{transacaoId}")
    public ResponseEntity<TransacaoDTO> buscarTransacaoPorId(@PathVariable Long transacaoId) {
		TransacaoDTO transacaoDTO = transacaoService.buscarTransacaoPorId(transacaoId);
        if (transacaoDTO == null) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } else {
            return ResponseEntity.ok(transacaoDTO);
        }
    }
	
	@Operation(summary = "Grava uma transacao")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Transaçao gravada com sucesso"),
            @ApiResponse(responseCode = "400", description = "Erro de validação dos dados", content = {
                    @Content(schema = @Schema())
            })
    })
	@PostMapping
    public ResponseEntity<Transacao> gravarTransacao(@Valid @RequestBody Transacao transacao) {
		Transacao transacaoSalvo = transacaoService.salvarTransacao(transacao);
        return ResponseEntity.status(HttpStatus.CREATED).body(transacaoSalvo);
    }
	
	@Operation(summary = "Atualiza uma transaçao com base no id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Transaçao atualizado com sucesso"),
            @ApiResponse(responseCode = "400", description = "Erro de validação dos dados", content = {
                    @Content(schema = @Schema())
            })
    })
	@PutMapping("/{transacaoId}")
    public ResponseEntity<Transacao> atualizarTransacao(@PathVariable Long transacaoId, @Valid @RequestBody Transacao transacao) {
		Transacao transacaoAtualizado = transacaoService.atualizarTransacao(transacaoId, transacao);
        if (transacaoAtualizado == null) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return ResponseEntity.status(HttpStatus.CREATED).body(transacaoAtualizado);
    }
	
	@Operation(summary = "Exclui uma transaçao com base no id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Transaçao excluído com sucesso", content = {
                    @Content(schema = @Schema())
            })
    })
	@DeleteMapping("/{transacaoId}")
    public ResponseEntity<Void> deletarTransacao(@PathVariable Long transacaoId) {
        transacaoService.deletarTransacao(transacaoId);
        return ResponseEntity.noContent().build();
    }
	

}
