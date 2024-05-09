package br.com.plusoft.jadv.challenge.apis;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.plusoft.jadv.challenge.servicos.CadastrarUsuarioService;
import br.com.plusoft.jadv.challenge.servicos.dtos.UsuarioDto;

@RestController
@RequestMapping("/usuarios")
public class UsuarioAPI {

    @Autowired
    private CadastrarUsuarioService cadastrarUsuarioService;

    @PostMapping("/cadastrar")
    public ResponseEntity<UsuarioDto> cadastrarUsuario(@RequestBody UsuarioDto request) {
        UsuarioDto novoUsuario = cadastrarUsuarioService.criarNovoUsuario(request.getNome(), request.getEmail(), request.getSenha(), request.ChaveGoogleAds());
        return ResponseEntity.status(HttpStatus.CREATED).body(novoUsuario);
    }
}
