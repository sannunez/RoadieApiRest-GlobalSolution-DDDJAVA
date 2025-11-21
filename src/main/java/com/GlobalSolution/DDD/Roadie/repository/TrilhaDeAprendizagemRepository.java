package com.GlobalSolution.DDD.Roadie.repository;

import com.GlobalSolution.DDD.Roadie.model.TrilhaDeAprendizagem;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TrilhaDeAprendizagemRepository extends JpaRepository<TrilhaDeAprendizagem, Long> {
    boolean existsByNome(String nome);
}
