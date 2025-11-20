package com.GlobalSolution.DDD.Roadie.service;

import com.GlobalSolution.DDD.Roadie.model.Usuario;
import com.GlobalSolution.DDD.Roadie.repository.UsuarioRepository;
import jakarta.persistence.Id;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class UsuarioService {
    private final UsuarioRepository repository;

    public UsuarioService(UsuarioRepository repository){this.repository = repository;}

    public List<Usuario> listarTodos(){return repository.findAll();}

    public Usuario salvar(Usuario usuario){
        usuario.setData_cadastro(LocalDate.now());
        return repository.save(usuario);
    }

    public Usuario buscarPorId(Long id){return repository.findById(id).orElse(null);}

    public void deletar(Long id){repository.deleteById(id);}

}

