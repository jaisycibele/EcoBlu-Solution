package br.com.ecobluSolution.globalSolution.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.ecobluSolution.globalSolution.entity.MaterialReciclado;

@Repository
public interface MaterialRecicladoRepository extends JpaRepository<MaterialReciclado, Long> {
}
