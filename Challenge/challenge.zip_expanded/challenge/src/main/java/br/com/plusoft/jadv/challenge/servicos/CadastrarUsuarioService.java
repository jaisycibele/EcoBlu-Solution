package br.com.plusoft.jadv.challenge.servicos;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.plusoft.jadv.challenge.entidades.Usuario;
import br.com.plusoft.jadv.challenge.repositorios.UsuarioRepository;
import br.com.plusoft.jadv.challenge.servicos.dtos.UsuarioDto;
import jakarta.transaction.Transactional;

@Service
public class CadastrarUsuarioService {

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Transactional
    public UsuarioDto criarNovoUsuario(String nome, String email, String senha, String chaveGoogleAds) {

        // Cria um novo usuário com os dados fornecidos
    	Usuario novoUsuario = new Usuario(nome, email, senha, chaveGoogleAds);
 // Chave Google ADS está sendo definida como null

        // Salva o novo usuário no banco de dados
        usuarioRepository.save(novoUsuario);

        // Retorna um DTO representando o novo usuário criado
        return new UsuarioDto(novoUsuario);
    }

}
