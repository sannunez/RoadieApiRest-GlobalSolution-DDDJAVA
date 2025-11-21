package com.GlobalSolution.DDD.Roadie.model;

import jakarta.persistence.GenerationType;
import jakarta.persistence.*;
import jakarta.validation.constraints.*;

import java.util.List;

@Entity
public class TrilhaDeAprendizagem {
    // ATRIBUTOS
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "Campo Obrigatório")
    @Column(nullable = false, unique = true)
    private String nome;

    @NotBlank(message = "Campo Obrigatório")
    @Column(nullable = false)
    private String descricao;

    @NotBlank(message = "Campo Obrigatório")
    @Column(nullable = false)
    private String nivel;


    @NotNull(message = "Campo Obrigatório")
    @Min(value = 1, message = "A carga horária deve ser maior que zero")
    @Column(nullable = false)
    private Integer carga_horaria;


    @NotBlank(message = "Campo Obrigatório")
    @Column(nullable = false)
    private String foco_principal;

    // CONSTRUTORES
    public TrilhaDeAprendizagem(){}

    public TrilhaDeAprendizagem(Long id, String nome, String descricao, String nivel, int carga_horaria, String foco_principal) {
        this.id = id;
        this.nome = nome;
        this.descricao = descricao;
        this.nivel = nivel;
        this.carga_horaria = carga_horaria;
        this.foco_principal = foco_principal;
    }

    // GETTERS e SETTERS
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public String getNivel() {
        return nivel;
    }

    public void setNivel(String nivel) {
        this.nivel = nivel;
    }

    public Integer getCarga_horaria() {
        return carga_horaria;
    }

    public void setCarga_horaria(Integer carga_horaria) {
        this.carga_horaria = carga_horaria;
    }

    public String getFoco_principal() {
        return foco_principal;
    }

    public void setFoco_principal(String foco_principal) {
        this.foco_principal = foco_principal;
    }
}
