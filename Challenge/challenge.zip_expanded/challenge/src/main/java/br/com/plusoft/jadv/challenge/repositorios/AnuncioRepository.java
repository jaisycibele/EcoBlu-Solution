package br.com.plusoft.jadv.challenge.repositorios;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.plusoft.jadv.challenge.entidades.Anuncio;

public interface AnuncioRepository extends JpaRepository<Anuncio, Long> {

}
