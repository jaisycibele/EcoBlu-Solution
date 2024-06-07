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

import br.com.ecobluSolution.globalSolution.entity.Parceria;
import br.com.ecobluSolution.globalSolution.services.dto.ParceriaDTO;
import br.com.ecobluSolution.globalSolution.servicos.ParceriaService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;

@RestController
@RequestMapping(value = "/parcerias", produces = {"application/json"})
@Tag(name = "api-parcerias")
public class ParceriaController {

	 private final ParceriaService parceriaService;

	    @Autowired
	    public ParceriaController(ParceriaService parceriaService) {
	        this.parceriaService = parceriaService;
	    }

	    @Operation(summary = "Retorna todos as parcerias em páginas de 5")
	    @ApiResponses(value = {
	            @ApiResponse(responseCode = "200", description = "Busca realizada com sucesso"),
	            @ApiResponse(responseCode = "204", description = "Nenhuma parceria encontrada", content = {
	                    @Content(schema = @Schema())
	            })
	    })
	    @GetMapping
	    public ResponseEntity<Page<ParceriaDTO>> buscarParcerias() {
	        Page<ParceriaDTO> parceriaDTO = parceriaService.buscarParcerias();
	        if (parceriaDTO.isEmpty()) {
	            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	        } else {
	            return ResponseEntity.ok(parceriaDTO);
	        }
	    }

	    @Operation(summary = "Retorna uma parceria específica por id")
	    @ApiResponses(value = {
	            @ApiResponse(responseCode = "200", description = "Busca realizada com sucesso"),
	            @ApiResponse(responseCode = "204", description = "Nenhuma parceria encontrada para o id informado", content = {
	                    @Content(schema = @Schema())
	            })
	    })
	    @GetMapping("/{parceriaId}")
	    public ResponseEntity<ParceriaDTO> buscarParceriaPorId(@PathVariable Long parceriaId) {
	    	ParceriaDTO parceriaDTO = parceriaService.buscarParceriaPorId(parceriaId);
	        if (parceriaDTO == null) {
	            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	        } else {
	            return ResponseEntity.ok(parceriaDTO);
	        }
	    }

	    @Operation(summary = "Grava uma parceria")
	    @ApiResponses(value = {
	            @ApiResponse(responseCode = "201", description = "Parceria gravada com sucesso"),
	            @ApiResponse(responseCode = "400", description = "Erro de validação dos dados", content = {
	                    @Content(schema = @Schema())
	            })
	    })
	    @PostMapping
	    public ResponseEntity<Parceria> gravarParceria(@Valid @RequestBody Parceria parceria) {
	    	Parceria parceriaSalvo = parceriaService.salvarParceria(parceria);
	        return ResponseEntity.status(HttpStatus.CREATED).body(parceriaSalvo);
	    }

	    @Operation(summary = "Atualiza uma parceria com base no id")
	    @ApiResponses(value = {
	            @ApiResponse(responseCode = "201", description = "Parceria atualizada com sucesso"),
	            @ApiResponse(responseCode = "400", description = "Erro de validação dos dados", content = {
	                    @Content(schema = @Schema())
	            })
	    })
	    @PutMapping("/{parceriaId}")
	    public ResponseEntity<Parceria> atualizarParceria(@PathVariable Long parceriaId, @Valid @RequestBody Parceria parceria) {
			Parceria parceriaAtualizado = parceriaService.atualizarParceria(parceriaId, parceria);
	        if (parceriaAtualizado == null) {
	            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	        }
	        return ResponseEntity.status(HttpStatus.CREATED).body(parceriaAtualizado);
	    }

	    
	    @Operation(summary = "Exclui uma parceria com base no id")
	    @ApiResponses(value = {
	            @ApiResponse(responseCode = "204", description = "Parceria excluído com sucesso", content = {
	                    @Content(schema = @Schema())
	            })
	    })
	    @DeleteMapping("/{parceriaId}")
	    public ResponseEntity<Void> deletarParceria(@PathVariable Long parceriaId) {
	        parceriaService.deletarParceria(parceriaId);
	        return ResponseEntity.noContent().build();
	    }
	
}
