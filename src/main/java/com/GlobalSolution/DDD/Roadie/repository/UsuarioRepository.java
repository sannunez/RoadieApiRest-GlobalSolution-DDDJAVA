package com.GlobalSolution.DDD.Roadie.repository;

import com.GlobalSolution.DDD.Roadie.model.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UsuarioRepository extends JpaRepository<Usuario, Long> {

}
