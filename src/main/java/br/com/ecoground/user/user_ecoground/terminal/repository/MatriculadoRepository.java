package br.com.ecoground.user.user_ecoground.terminal.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.ecoground.user.user_ecoground.terminal.entity.Matriculado;

@Repository
public interface MatriculadoRepository extends JpaRepository<Matriculado, Long>{
    Optional<Matriculado> findByMatricula(String matricula); 
}
