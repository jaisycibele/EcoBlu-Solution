package br.com.plusoft.jadv.challenge.servicos;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.plusoft.jadv.challenge.entidades.Campanha;
import br.com.plusoft.jadv.challenge.entidades.Usuario;
import br.com.plusoft.jadv.challenge.repositorios.UsuarioRepository;
import br.com.plusoft.jadv.challenge.servicos.dtos.UsuarioDto;
import jakarta.transaction.Transactional;

@Service
public class CadastrarUsuarioService {

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Transactional
    public UsuarioDto criarNovoUsuario(String nome, String email, String senha, String chaveGoogleAds, Campanha campanha) {

    	Usuario novoUsuario = new Usuario(nome, email, senha, chaveGoogleAds, campanha);
 
        usuarioRepository.save(novoUsuario);

        return new UsuarioDto(novoUsuario);
    }

}
