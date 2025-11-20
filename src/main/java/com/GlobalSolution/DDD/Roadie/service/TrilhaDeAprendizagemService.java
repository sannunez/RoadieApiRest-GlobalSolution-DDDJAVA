package com.GlobalSolution.DDD.Roadie.service;

import com.GlobalSolution.DDD.Roadie.model.TrilhaDeAprendizagem;
import com.GlobalSolution.DDD.Roadie.model.Usuario;
import com.GlobalSolution.DDD.Roadie.repository.TrilhaDeAprendizagemRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TrilhaDeAprendizagemService {

    private final TrilhaDeAprendizagemRepository repository;

    public TrilhaDeAprendizagemService(TrilhaDeAprendizagemRepository repository){this.repository = repository;}

    public List<TrilhaDeAprendizagem> listarTodos(){return repository.findAll();}

    public TrilhaDeAprendizagem salvar(TrilhaDeAprendizagem trilha){return repository.save(trilha);}

    public TrilhaDeAprendizagem buscarPorId(Long id){return repository.findById(id).orElse(null);}

    public void deletar(Long id){repository.deleteById(id);}
}
