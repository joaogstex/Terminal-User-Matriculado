package br.com.ecoground.user.user_ecoground.auth.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.ecoground.user.user_ecoground.auth.entity.UsuarioSignUp;

public interface UserRepository extends JpaRepository<UsuarioSignUp, Long> {
    Optional<UsuarioSignUp> findByUsername(String username);
}

