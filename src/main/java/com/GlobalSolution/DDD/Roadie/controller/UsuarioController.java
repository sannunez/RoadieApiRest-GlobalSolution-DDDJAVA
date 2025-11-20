package com.GlobalSolution.DDD.Roadie.controller;

import com.GlobalSolution.DDD.Roadie.model.Usuario;
import com.GlobalSolution.DDD.Roadie.service.UsuarioService;
import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/usuarios")
public class UsuarioController {

    private final UsuarioService service;

    public UsuarioController(UsuarioService service) {
        this.service = service;
    }

    @GetMapping
    public String usuarios(Model model){
        model.addAttribute("listaUsuarios", service.listarTodos());
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

}

