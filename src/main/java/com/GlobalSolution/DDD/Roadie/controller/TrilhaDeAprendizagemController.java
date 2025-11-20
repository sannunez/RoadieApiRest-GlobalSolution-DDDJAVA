package com.GlobalSolution.DDD.Roadie.controller;

import com.GlobalSolution.DDD.Roadie.model.TrilhaDeAprendizagem;
import com.GlobalSolution.DDD.Roadie.service.TrilhaDeAprendizagemService;
import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/trilhas")
public class TrilhaDeAprendizagemController {

    private final TrilhaDeAprendizagemService service;

    public TrilhaDeAprendizagemController(TrilhaDeAprendizagemService service) {
        this.service = service;
    }

    @GetMapping
    public String trilhas(Model model){
        model.addAttribute("listaTrilhas", service.listarTodos());
        return "trilhas";
    }

    @GetMapping("/cadastrar_trilha")
    public String pagina_cadastro(Model model){
        model.addAttribute("trilha", new TrilhaDeAprendizagem());

        return "formulario_cadastro_trilha";
    }

    @PostMapping("/salvar")
    public String salvar(@Valid @ModelAttribute ("trilha") TrilhaDeAprendizagem trilha, BindingResult result){
        if (result.hasErrors()){
            return "formulario_cadastro_trilha";
        }

        service.salvar(trilha);
        return "redirect:/trilhas";
    }

    @GetMapping("/deletar/{id}")
    public String deletar(@PathVariable Long id){
        service.deletar(id);
        return "redirect:/trilhas";
    }

    @GetMapping("/editar/{id}")
    public String editar(@PathVariable Long id, Model model){
        model.addAttribute("trilha", service.buscarPorId(id));
        return "formulario_cadastro_trilha";
    }
}
