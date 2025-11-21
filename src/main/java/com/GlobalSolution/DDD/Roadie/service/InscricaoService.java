package com.GlobalSolution.DDD.Roadie.service;

import com.GlobalSolution.DDD.Roadie.model.Inscricao;
import com.GlobalSolution.DDD.Roadie.model.TrilhaDeAprendizagem;
import com.GlobalSolution.DDD.Roadie.model.Usuario;
import com.GlobalSolution.DDD.Roadie.repository.InscricaoRepository;
import com.GlobalSolution.DDD.Roadie.repository.TrilhaDeAprendizagemRepository;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class InscricaoService {
    // REPOSITORY
    private InscricaoRepository repository;

    public InscricaoService(InscricaoRepository repository){this.repository = repository;}

    // SALVAR(CREATE)
    public void salvar(Usuario usuario, TrilhaDeAprendizagem trilha){
        try{
            Inscricao inscricao = new Inscricao();
            inscricao.setUsuario(usuario);
            inscricao.setTrilha(trilha);

            repository.save(inscricao);
        } catch (DataIntegrityViolationException ex){
            throw new RuntimeException("Usuário já está inscrito nesta trilha!");
        }

    }

    // LISTAR TODAS
    public List<Inscricao> listarTodos(){return repository.findAll();}

    // READ
    public Inscricao buscarPorId(Long id){return repository.findById(id).orElse(null);}

    // DELETE
    public void deletar(Long id){repository.deleteById(id);}

    // CONTADOR DE INSCRIÇÕES QUE USUARIO POSSUI
    public int contarInscricoesPorUsuario(Long usuarioId){
        return repository.countByUsuarioId(usuarioId);
    }


    public List<TrilhaDeAprendizagem> listarTrilhasDoUsuario(Long usuarioId) {
        List<Inscricao> inscricoes = repository.findByUsuarioId(usuarioId);
        return inscricoes.stream()
                .map(Inscricao::getTrilha) // pega só a trilha
                .toList();
    }

    // CONTADOR DE USUARIOS QUE UMA TRILHA POSSUI CADASTRADOS
    public int contarInscricoesEmTrilha(Long trilhaId){
        return repository.countByTrilhaId(trilhaId);
    }

    // DELETE(CANCELAR INSCRICAO)
    public void cancelarInscricao(Long usuarioId, Long trilhaId) {
        Inscricao inscricao = repository
                .findByUsuarioIdAndTrilhaId(usuarioId, trilhaId);

        if (inscricao != null) {
            repository.delete(inscricao);
        }
    }


}
