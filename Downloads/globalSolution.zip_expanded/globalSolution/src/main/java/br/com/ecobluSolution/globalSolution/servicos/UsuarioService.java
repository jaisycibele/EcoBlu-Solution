package br.com.ecobluSolution.globalSolution.servicos;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.hateoas.Link;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.com.ecobluSolution.globalSolution.controller.UsuarioController;
import br.com.ecobluSolution.globalSolution.entity.Usuario;
import br.com.ecobluSolution.globalSolution.repository.UsuarioRepository;
import br.com.ecobluSolution.globalSolution.services.dto.UsuarioDTO;

@Service
public class UsuarioService {

    private final UsuarioRepository usuarioRepository;
    private static final Pageable paginacaoPersonalizada = PageRequest.of(0, 5, Sort.by("nome").ascending());

    @Autowired
    public UsuarioService(UsuarioRepository usuarioRepository) {
        this.usuarioRepository = usuarioRepository;
    }

    public Page<UsuarioDTO> buscarUsuarios() {
        return usuarioRepository.findAll(paginacaoPersonalizada).map(usuario -> toDTO(usuario, true));
    }

    public UsuarioDTO buscarUsuarioPorId(Long usuarioId) {
        return usuarioRepository.findById(usuarioId).map(usuario -> toDTO(usuario, false)).orElse(null);
    }

    @Transactional
    public Usuario salvarUsuario(Usuario usuario){
        return usuarioRepository.save(usuario);
    }

    @Transactional
    public Usuario atualizarUsuario(Long usuarioId, Usuario usuario){
        Optional<Usuario> usuarioOptional = usuarioRepository.findById(usuarioId);
        if (usuarioOptional.isPresent()) {
            Usuario usuarioAtual = usuarioOptional.get();
            usuarioAtual.setNome(usuario.getNome());
            usuarioAtual.setEmail(usuario.getEmail());
            usuarioAtual.setSenha(usuario.getSenha());
            usuarioAtual.setTipoUsuarioId(usuario.getTipoUsuarioId());
            usuarioAtual.setDataRegistro(usuario.getDataRegistro());
            
            return usuarioRepository.save(usuarioAtual);
        }
        return null;
    }

    public void deletarUsuario(Long usuarioId) {
        Optional<Usuario> usuarioOptional = usuarioRepository.findById(usuarioId);
        usuarioOptional.ifPresent(usuarioRepository::delete);
    }

    private UsuarioDTO toDTO(Usuario usuario, boolean self) {
        Link link;
        if (self) {
            link = linkTo(methodOn(UsuarioController.class).buscarUsuarioPorId(usuario.getUsuarioId())).withSelfRel();
        } else {
            link = linkTo(methodOn(UsuarioController.class).buscarUsuarios()).withRel("Lista de Usuarios");
        }
        return new UsuarioDTO(
                usuario.getUsuarioId(),
                usuario.getNome(),
                usuario.getEmail(),
                usuario.getSenha(),
                usuario.getTipoUsuarioId(),
                usuario.getDataRegistro(),
                link
        );
    }

	public Usuario findById(Long usuarioId) {
		// TODO Auto-generated method stub
		return null;
	}


}
