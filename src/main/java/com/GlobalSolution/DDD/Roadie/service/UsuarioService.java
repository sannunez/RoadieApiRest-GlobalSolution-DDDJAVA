package com.GlobalSolution.DDD.Roadie.service;

import com.GlobalSolution.DDD.Roadie.exceptions.UsuarioJaExistenteException;
import com.GlobalSolution.DDD.Roadie.exceptions.UsuarioNaoEncontradoException;
import com.GlobalSolution.DDD.Roadie.model.Usuario;
import com.GlobalSolution.DDD.Roadie.repository.UsuarioRepository;
import jakarta.persistence.Id;
import org.springframework.stereotype.Service;


import java.time.LocalDate;
import java.util.List;

@Service
public class UsuarioService {
    // REPOSITORY
    private final UsuarioRepository repository;

    public UsuarioService(UsuarioRepository repository){this.repository = repository;}

    // LISTAR TODOS OS USUARIOS
    public List<Usuario> listarTodos(){return repository.findAll();}

    // SALVAR(CREATE e UPDATE)
    public Usuario salvar(Usuario usuario){
        boolean emailExistente = repository.findAll().stream()
                .anyMatch(u -> u.getEmail().equals(usuario.getEmail()) && !u.getId().equals(usuario.getId()));

        if(emailExistente){
            throw new UsuarioJaExistenteException("Email já cadastrado: " + usuario.getEmail());
        }

        usuario.setData_cadastro(LocalDate.now());
        return repository.save(usuario);
    }

    // READ
    public Usuario buscarPorId(Long id){
        return repository.findById(id)
                .orElseThrow(() -> new UsuarioNaoEncontradoException("Usuário com ID " + id + " não encontrado"));
    }

    // DELETE
    public void deletar(Long id){
        if(!repository.existsById(id)){
            throw new UsuarioNaoEncontradoException("Usuário com ID " + id + " não encontrado");
        }
        repository.deleteById(id);
    }

}

