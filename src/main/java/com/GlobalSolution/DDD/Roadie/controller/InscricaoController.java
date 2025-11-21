package com.GlobalSolution.DDD.Roadie.controller;

import com.GlobalSolution.DDD.Roadie.model.Inscricao;
import com.GlobalSolution.DDD.Roadie.model.TrilhaDeAprendizagem;
import com.GlobalSolution.DDD.Roadie.model.Usuario;
import com.GlobalSolution.DDD.Roadie.service.InscricaoService;
import com.GlobalSolution.DDD.Roadie.service.TrilhaDeAprendizagemService;
import com.GlobalSolution.DDD.Roadie.service.UsuarioService;
import jakarta.persistence.Id;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class InscricaoController {
    //  SERVICES
    private final InscricaoService service;
    private  final UsuarioService usuarioService;
    private  final TrilhaDeAprendizagemService trilhaService;

    public InscricaoController(InscricaoService service, UsuarioService usuarioService, TrilhaDeAprendizagemService trilhaService){
        this.service = service;
        this.usuarioService = usuarioService;
        this.trilhaService = trilhaService;
    }

    //  CREATE
    @GetMapping("/trilhas/inscrever_trilha/{id}")
    public String pagina_inscrever(@PathVariable Long id, Model model){

        Inscricao inscricao = new Inscricao();

        TrilhaDeAprendizagem trilha = trilhaService.buscarPorId(id);

        inscricao.setTrilha(trilha);

        model.addAttribute("inscricaoFormulario", inscricao);
        model.addAttribute("listaUsuarios", usuarioService.listarTodos());
        model.addAttribute("trilhaNome",trilha.getNome());

        return  "formulario_inscrever";
    }

    @PostMapping("/trilhas/inscrever")
    public String salvar(@ModelAttribute Inscricao inscricao, Model model){

        try{
            service.salvar(inscricao.getUsuario(), inscricao.getTrilha());
            return "redirect:/trilhas";
        } catch (RuntimeException e) {

            TrilhaDeAprendizagem trilha = trilhaService.buscarPorId(inscricao.getTrilha().getId());

            model.addAttribute("inscricaoFormulario", inscricao);
            model.addAttribute("listaUsuarios", usuarioService.listarTodos());
            model.addAttribute("trilhaNome", trilha.getNome());
            model.addAttribute("mensagemErro", e.getMessage());

            return "formulario_inscrever";
        }

    }

    // DELETE
    @GetMapping("/inscricoes/cancelar/{userId}/{trilhaId}")
    public String cancelarInscricao(@PathVariable("userId") Long usuarioId,
                                    @PathVariable Long trilhaId) {

        service.cancelarInscricao(usuarioId, trilhaId);
        return "redirect:/usuarios";
    }


}
