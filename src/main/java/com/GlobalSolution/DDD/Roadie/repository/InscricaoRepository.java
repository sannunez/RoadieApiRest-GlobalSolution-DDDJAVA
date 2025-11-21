package com.GlobalSolution.DDD.Roadie.repository;

import com.GlobalSolution.DDD.Roadie.model.Inscricao;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface InscricaoRepository extends JpaRepository<Inscricao, Long> {

    int countByUsuarioId(Long usuarioId);

    List<Inscricao> findByUsuarioId(Long usuarioId);

    int countByTrilhaId(Long trilhaId);

    Inscricao findByUsuarioIdAndTrilhaId(Long usuarioId, Long trilhaId);


}
