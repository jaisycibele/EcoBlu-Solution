package br.com.plusoft.jadv.challenge.repositorios;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.plusoft.jadv.challenge.entidades.Usuario;

public interface UsuarioRepository extends JpaRepository<Usuario, Long> {

}
