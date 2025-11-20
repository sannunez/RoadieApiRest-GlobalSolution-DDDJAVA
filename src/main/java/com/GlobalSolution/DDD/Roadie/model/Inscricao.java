package com.GlobalSolution.DDD.Roadie.model;

import jakarta.persistence.*;


@Entity
@Table(
        name="inscricao",
        uniqueConstraints = {@UniqueConstraint(columnNames = {"usuario_id", "trilha_id"})}
)
public class Inscricao {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "usuario_id", nullable = false)
    private Usuario usuario;

    @ManyToOne
    @JoinColumn(name = "trilha_id", nullable = false)
    private TrilhaDeAprendizagem trilha;

    public Inscricao(){};

    public Inscricao(Long id, Usuario usuario, TrilhaDeAprendizagem trilha) {
        this.id = id;
        this.usuario = usuario;
        this.trilha = trilha;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public TrilhaDeAprendizagem getTrilha() {
        return trilha;
    }

    public void setTrilha(TrilhaDeAprendizagem trilha) {
        this.trilha = trilha;
    }
}
