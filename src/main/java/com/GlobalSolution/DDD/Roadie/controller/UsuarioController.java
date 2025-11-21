package com.GlobalSolution.DDD.Roadie.controller;

import com.GlobalSolution.DDD.Roadie.model.TrilhaDeAprendizagem;
import com.GlobalSolution.DDD.Roadie.model.Usuario;
import com.GlobalSolution.DDD.Roadie.service.InscricaoService;
import com.GlobalSolution.DDD.Roadie.service.UsuarioService;
import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/usuarios")
public class UsuarioController {

    private final UsuarioService service;
    private final InscricaoService inscricaoService;

    public UsuarioController(UsuarioService service, InscricaoService  inscricaoService) {
        this.service = service;
        this.inscricaoService = inscricaoService;
    }

    @GetMapping
    public String usuarios(Model model){

        List<Usuario> usuarios = service.listarTodos();

        Map<Long, Integer> inscritosPorUsuario = new HashMap<>();

        Map<Long, List<TrilhaDeAprendizagem>> trilhasPorUsuario = new HashMap<>();


        for(Usuario u : usuarios){
            int qtd = inscricaoService.contarInscricoesPorUsuario(u.getId());
            inscritosPorUsuario.put(u.getId(), qtd);

            trilhasPorUsuario.put(u.getId(), inscricaoService.listarTrilhasDoUsuario(u.getId()));
        }

        model.addAttribute("listaUsuarios", service.listarTodos());
        model.addAttribute("inscricoesCount", inscritosPorUsuario);
        model.addAttribute("trilhasPorUsuario", trilhasPorUsuario);

        return "usuarios";
    }

    @GetMapping("/cadastrar_usuarios")
    public String pagina_cadastro(Model model){
        model.addAttribute("usuario", new Usuario());
        return "formulario_cadastro_usuario";
    }

    @PostMapping("/salvar")
    public String salvar(@Valid @ModelAttribute("usuario") Usuario usuario, BindingResult result){
        if (result.hasErrors()){
            return "formulario_cadastro_usuario";
        }

        service.salvar(usuario);
        return "redirect:/usuarios";
    }

    @GetMapping("/deletar/{id}")
    public String deletar(@PathVariable Long id){
        service.deletar(id);
        return "redirect:/usuarios";
    }

    @GetMapping("/editar/{id}")
    public String editar(@PathVariable Long id, Model model){
        model.addAttribute("usuario", service.buscarPorId(id));
        return "formulario_cadastro_usuario";
    }
}

