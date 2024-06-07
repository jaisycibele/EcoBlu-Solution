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


import br.com.ecobluSolution.globalSolution.entity.Usuario;
import br.com.ecobluSolution.globalSolution.services.dto.UsuarioDTO;
import br.com.ecobluSolution.globalSolution.servicos.UsuarioService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;

@RestController
@RequestMapping(value = "/usuarios", produces = {"application/json"})
@Tag(name = "api-usuario")
public class UsuarioController {

    private final UsuarioService usuarioService;

    @Autowired
    public UsuarioController(UsuarioService usuarioService) {
        this.usuarioService = usuarioService;
    }

    @Operation(summary = "Retorna todos os usuarios em páginas de 5")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Busca realizada com sucesso"),
            @ApiResponse(responseCode = "204", description = "Nenhum usuario encontrado", content = {
                    @Content(schema = @Schema())
            })
    })
    @GetMapping
    public ResponseEntity<Page<UsuarioDTO>> buscarUsuarios() {
        Page<UsuarioDTO> usuariosDTO = usuarioService.buscarUsuarios();
        if (usuariosDTO.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } else {
            return ResponseEntity.ok(usuariosDTO);
        }
    }

    @Operation(summary = "Retorna um usuario específico por id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Busca realizada com sucesso"),
            @ApiResponse(responseCode = "204", description = "Nenhum usuario encontrado para o id informado", content = {
                    @Content(schema = @Schema())
            })
    })
    @GetMapping("/{usuarioId}")
    public ResponseEntity<UsuarioDTO> buscarUsuarioPorId(@PathVariable Long usuarioId) {
        UsuarioDTO usuarioDTO = usuarioService.buscarUsuarioPorId(usuarioId);
        if (usuarioDTO == null) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } else {
            return ResponseEntity.ok(usuarioDTO);
        }
    }

    @Operation(summary = "Grava um usuario")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Usuario gravado com sucesso"),
            @ApiResponse(responseCode = "400", description = "Erro de validação dos dados", content = {
                    @Content(schema = @Schema())
            })
    })
    @PostMapping
    public ResponseEntity<Usuario> gravarUsuario(@Valid @RequestBody Usuario usuario) {
        Usuario usuarioSalvo = usuarioService.salvarUsuario(usuario);
        return ResponseEntity.status(HttpStatus.CREATED).body(usuarioSalvo);
    }

    
    @Operation(summary = "Atualiza um usuario com base no id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Usuario atualizado com sucesso"),
            @ApiResponse(responseCode = "400", description = "Erro de validação dos dados", content = {
                    @Content(schema = @Schema())
            })
    })
    @PutMapping("/{usuarioId}")
    public ResponseEntity<Usuario> atualizarUsuario(@PathVariable Long usuarioId, @Valid @RequestBody Usuario usuario) {
        Usuario usuarioAtualizado = usuarioService.atualizarUsuario(usuarioId, usuario);
        if (usuarioAtualizado == null) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return ResponseEntity.status(HttpStatus.CREATED).body(usuarioAtualizado);
    }

    @Operation(summary = "Exclui um usuario com base no id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Usuario excluído com sucesso", content = {
                    @Content(schema = @Schema())
            })
    })
    @DeleteMapping("/{usuarioId}")
    public ResponseEntity<Void> deletarTreinamento(@PathVariable Long usuarioId) {
        usuarioService.deletarUsuario(usuarioId);
        return ResponseEntity.noContent().build();
    }
}
