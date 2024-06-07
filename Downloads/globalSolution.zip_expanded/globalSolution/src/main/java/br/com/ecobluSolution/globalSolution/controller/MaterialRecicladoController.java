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

import br.com.ecobluSolution.globalSolution.entity.MaterialReciclado;
import br.com.ecobluSolution.globalSolution.services.dto.MaterialRecicladoDTO;
import br.com.ecobluSolution.globalSolution.servicos.MaterialRecicladoService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;

@RestController
@RequestMapping(value = "/materiais", produces = {"application/json"})
@Tag(name = "api-materialReciclado")
public class MaterialRecicladoController {

	private final MaterialRecicladoService materialRecicladoService;

    @Autowired
    public MaterialRecicladoController(MaterialRecicladoService materialRecicladoService) {
        this.materialRecicladoService = materialRecicladoService;
    }
    
    @Operation(summary = "Retorna todos os materiais reciclados em páginas de 5")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Busca realizada com sucesso"),
            @ApiResponse(responseCode = "204", description = "Nenhum material encontrado", content = {
                    @Content(schema = @Schema())
            })
    })
    @GetMapping
    public ResponseEntity<Page<MaterialRecicladoDTO>> buscarMateriais() {
        Page<MaterialRecicladoDTO> materiaisDTO = materialRecicladoService.buscarMateriais();
        if (materiaisDTO.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } else {
            return ResponseEntity.ok(materiaisDTO);
        }
    }
    
    @Operation(summary = "Retorna um material reciclado específico por id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Busca realizada com sucesso"),
            @ApiResponse(responseCode = "204", description = "Nenhum material encontrado para o id informado", content = {
                    @Content(schema = @Schema())
            })
    })
    @GetMapping("/{materialId}")
    public ResponseEntity<MaterialRecicladoDTO> buscarMaterialPorId(@PathVariable Long materialId) {
    	MaterialRecicladoDTO materialRecicladoDTO = materialRecicladoService.buscarMaterialPorId(materialId);
        if (materialRecicladoDTO == null) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } else {
            return ResponseEntity.ok(materialRecicladoDTO);
        }
    }

    @Operation(summary = "Grava um material reciclado")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Material gravado com sucesso"),
            @ApiResponse(responseCode = "400", description = "Erro de validação dos dados", content = {
                    @Content(schema = @Schema())
            })
    })
    @PostMapping
    public ResponseEntity<MaterialReciclado> gravarTransacao(@Valid @RequestBody MaterialReciclado materialReciclado) {
    	MaterialReciclado materialSalvo = materialRecicladoService.salvarMaterial(materialReciclado);
        return ResponseEntity.status(HttpStatus.CREATED).body(materialSalvo);
    }
    
    @Operation(summary = "Atualiza um material reciclado com base no id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Material atualizado com sucesso"),
            @ApiResponse(responseCode = "400", description = "Erro de validação dos dados", content = {
                    @Content(schema = @Schema())
            })
    })
    @PutMapping("/{materialId}")
    public ResponseEntity<MaterialReciclado> atualizarMaterial(@PathVariable Long materialId, @Valid @RequestBody MaterialReciclado materialReciclado) {
    	MaterialReciclado materialAtualizado = materialRecicladoService.atualizarMaterial(materialId, materialReciclado);
        if (materialAtualizado == null) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return ResponseEntity.status(HttpStatus.CREATED).body(materialAtualizado);
    }

    @Operation(summary = "Exclui um material com base no id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Material excluído com sucesso", content = {
                    @Content(schema = @Schema())
            })
    })
    @DeleteMapping("/{materialId}")
    public ResponseEntity<Void> deletarMaterial(@PathVariable Long materialId) {
        materialRecicladoService.deletarMaterial(materialId);
        return ResponseEntity.noContent().build();
    }
	
	
}
