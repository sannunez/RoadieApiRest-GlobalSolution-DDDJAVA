package com.GlobalSolution.DDD.Roadie.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;

import java.time.LocalDate;

@Entity
public class Usuario {
    // ATRIBUTOS
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "Nome obrigatório")
    @Column(nullable = false, unique = true)
    private String nome;

    @NotBlank(message = "Email obrigatório")
    @Email(message = "Email inválido")
    @Column(nullable = false, unique = true)
    private String email;

    private String area_atuacao;
    private String nivel_carreira;
    private LocalDate data_cadastro;

    // CONSTRUTORES
    public Usuario(){}

    public Usuario(Long id, String nome, String email, String area_atuacao, String nivel_carreira, LocalDate data_cadastro) {
        this.id = id;
        this.nome = nome;
        this.email = email;
        this.area_atuacao = area_atuacao;
        this.nivel_carreira = nivel_carreira;
        this.data_cadastro = data_cadastro;
    }

    // GETTERS E SETTERS
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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getArea_atuacao() {
        return area_atuacao;
    }

    public void setArea_atuacao(String area_atuacao) {
        this.area_atuacao = area_atuacao;
    }

    public String getNivel_carreira() {
        return nivel_carreira;
    }

    public void setNivel_carreira(String nivel_carreira) {
        this.nivel_carreira = nivel_carreira;
    }

    public LocalDate getData_cadastro() {
        return data_cadastro;
    }

    public void setData_cadastro(LocalDate data_cadastro) {
        this.data_cadastro = data_cadastro;
    }
}
