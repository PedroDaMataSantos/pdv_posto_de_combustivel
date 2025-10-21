package com.br.pdvpostocombustivel.domain.repository;

import java.util.Optional;
import com.br.pdvpostocombustivel.domain.entity.Acesso;
import org.springframework.data.jpa.repository.JpaRepository;


public interface AcessoRepository extends JpaRepository<Acesso, Long> {

    Optional<Acesso> findByusuario(String usuario);

    boolean existsByusuario(String usuario);
}
