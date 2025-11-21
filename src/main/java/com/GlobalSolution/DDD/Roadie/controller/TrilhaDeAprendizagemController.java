package com.GlobalSolution.DDD.Roadie.controller;

import com.GlobalSolution.DDD.Roadie.exceptions.TrilhaJaExistenteException;
import com.GlobalSolution.DDD.Roadie.model.TrilhaDeAprendizagem;
import com.GlobalSolution.DDD.Roadie.model.Usuario;
import com.GlobalSolution.DDD.Roadie.service.InscricaoService;
import com.GlobalSolution.DDD.Roadie.service.TrilhaDeAprendizagemService;
import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/trilhas")
public class TrilhaDeAprendizagemController {
    // SERVICES
    private final TrilhaDeAprendizagemService service;
    private final InscricaoService inscricaoService;

    public TrilhaDeAprendizagemController(TrilhaDeAprendizagemService service, InscricaoService inscricaoService) {
        this.service = service;
        this.inscricaoService = inscricaoService;
    }

    // READ
    @GetMapping
    public String trilhas(Model model){

        List<TrilhaDeAprendizagem> trilhas = service.listarTodos();

        Map<Long, Integer> inscritosEmTrilha = new HashMap<>();

        for(TrilhaDeAprendizagem t : trilhas){
            int qtd = inscricaoService.contarInscricoesEmTrilha(t.getId());
            inscritosEmTrilha.put(t.getId(), qtd);
        }

        model.addAttribute("listaTrilhas", service.listarTodos());
        model.addAttribute("inscritosEmTrilhaCount", inscritosEmTrilha);
        return "trilhas";
    }

    // CREATE
    @GetMapping("/cadastrar_trilha")
    public String pagina_cadastro(Model model){
        model.addAttribute("trilha", new TrilhaDeAprendizagem());

        return "formulario_cadastro_trilha";
    }

    @PostMapping("/salvar")
    public String salvar(
            @Valid @ModelAttribute("trilha") TrilhaDeAprendizagem trilha,
            BindingResult result,
            Model model
    ) {

        if (result.hasErrors()) {
            return "formulario_cadastro_trilha";
        }

        try {
            service.salvar(trilha);

        } catch (TrilhaJaExistenteException ex) {
            // TERMINAL
            System.err.println("----------------------------------------------------");
            System.err.println("ERRO 400 - Trilha duplicada");
            System.err.println("Mensagem: " + ex.getMessage());
            System.err.println("Endpoint: POST /trilhas/salvar");
            System.err.println("----------------------------------------------------");

            // mensagem na tela
            model.addAttribute("erroNome", ex.getMessage());

            return "formulario_cadastro_trilha";
        }

        return "redirect:/trilhas";
    }


    // DELETE
    @GetMapping("/deletar/{id}")
    public String deletar(@PathVariable Long id){
        service.deletar(id);
        return "redirect:/trilhas";
    }

    // UPDATE
    @GetMapping("/editar/{id}")
    public String editar(@PathVariable Long id, Model model){
        model.addAttribute("trilha", service.buscarPorId(id));
        return "formulario_cadastro_trilha";
    }
}
