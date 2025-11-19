package com.GlobalSolution.DDD.Roadie.config;

import com.GlobalSolution.DDD.Roadie.model.Usuario;
import com.GlobalSolution.DDD.Roadie.model.TrilhaDeAprendizagem;
import com.GlobalSolution.DDD.Roadie.repository.TrilhaDeAprendizagemRepository;
import com.GlobalSolution.DDD.Roadie.repository.UsuarioRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.List;

@Component
public class SeedData implements CommandLineRunner {

    private final  UsuarioRepository usuarioRepository;
    private final TrilhaDeAprendizagemRepository trilhaDeAprendizagemRepository;

    public SeedData(UsuarioRepository usuarioRepository, TrilhaDeAprendizagemRepository trilhaDeAprendizagemRepository) {
        this.usuarioRepository = usuarioRepository;
        this.trilhaDeAprendizagemRepository = trilhaDeAprendizagemRepository;
    }

    @Override
    public void run(String... args){

        if(usuarioRepository.count() == 0){

            List<Usuario> usuariosIniciais = List.of(
                    new Usuario(null, "Carlos Silva", "carlos@example.com", "TI", "Júnior", LocalDate.now()),
                    new Usuario(null, "Ana Pereira", "ana@example.com", "Marketing", "Pleno", LocalDate.now()),
                    new Usuario(null, "João Costa", "joao@example.com", "RH", "Sênior", LocalDate.now()),
                    new Usuario(null, "Mariana Lopes", "mariana@example.com", "Design", "Júnior", LocalDate.now()),
                    new Usuario(null, "Ricardo Nunes", "ricardo@example.com", "TI", "Pleno", LocalDate.now())
            );

            usuarioRepository.saveAll(usuariosIniciais);
        }

        if(trilhaDeAprendizagemRepository.count() == 0){

            List<TrilhaDeAprendizagem> trilhasIniciais = List.of(
                    new TrilhaDeAprendizagem(null, "Arquitetura - Desenvolvimento de Plantas 3D", "Conheça as tecnologias emergentes no mundo da Arquitetura, aprenda sobre como desenvolver suas obras em um ambiente completamente tridimensional.", "INICIANTE", 12, "Modelagem 3D, Autodesk Maya, Unreal Engine"),
                    new TrilhaDeAprendizagem(null, "Ciência de Dados – Fundamentos e Machine Learning", "Aprenda a trabalhar com grandes volumes de dados, dominar estatística aplicada e criar modelos de Machine Learning para resolver problemas reais.", "INTERMEDIÁRIO", 16, "Python, Pandas, Scikit-Learn, Visualização de Dados"),
                    new TrilhaDeAprendizagem(null, "Cibersegurança – Proteção de Infraestruturas Digitais", "Domine conceitos essenciais de segurança digital, aprenda a identificar vulnerabilidades e aplicar práticas modernas para proteger sistemas corporativos.", "INICIANTE", 10, "Redes, Firewalls, OWASP, Pentest Básico"),
                    new TrilhaDeAprendizagem(null, "Desenvolvimento Web – Full Stack Moderno", "Aprenda a construir aplicações completas, do frontend ao backend, utilizando as tecnologias mais adotadas no mercado atual.", "AVANÇADO", 20, "JavaScript, React, Node.js, APIs REST"),
                    new TrilhaDeAprendizagem(null, "Inteligência Artificial Generativa – Aplicações no Ambiente Corporativo", "Entenda como modelos generativos estão transformando empresas, aprenda a utilizá-los em fluxos produtivos e criar automações inteligentes.", "INTERMEDIÁRIO", 14, "IA Generativa, Prompt Engineering, LLMs, Automação")
            );

            trilhaDeAprendizagemRepository.saveAll(trilhasIniciais);
        }
    }
}
