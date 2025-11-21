package com.GlobalSolution.DDD.Roadie.service;

import com.GlobalSolution.DDD.Roadie.exceptions.TrilhaJaExistenteException;
import com.GlobalSolution.DDD.Roadie.exceptions.TrilhaNaoEncontradaException;
import com.GlobalSolution.DDD.Roadie.model.TrilhaDeAprendizagem;
import com.GlobalSolution.DDD.Roadie.repository.TrilhaDeAprendizagemRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TrilhaDeAprendizagemService {
    // REPOSITORY
    private final TrilhaDeAprendizagemRepository repository;

    public TrilhaDeAprendizagemService(TrilhaDeAprendizagemRepository repository){this.repository = repository;}

    // LISTAR TODOS AS TRUKGAS
    public List<TrilhaDeAprendizagem> listarTodos(){return repository.findAll();}

    // SALVAR(CREATE e UPDATE)
    public TrilhaDeAprendizagem salvar(TrilhaDeAprendizagem trilha){
        boolean existe = repository.existsByNome(trilha.getNome());
        if (existe){
            throw new TrilhaJaExistenteException("Já existe uma trilha com o nome: " + trilha.getNome());
        }
        return repository.save(trilha);

    }

    // READ
    public TrilhaDeAprendizagem buscarPorId(Long id){
        return repository.findById(id)
                .orElseThrow(() -> new TrilhaNaoEncontradaException("Trilha com ID " + id + " não encontrada"));
    }

    // DELETE
    public void deletar(Long id){
        if(!repository.existsById(id)){
            throw new TrilhaNaoEncontradaException("Trilha com ID " + id + " não encontrada");
        }
        repository.deleteById(id);
    }


}
