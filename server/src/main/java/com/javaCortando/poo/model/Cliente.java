package com.javaCortando.poo.model;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import jakarta.persistence.*;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "clientes")
public class Cliente extends Usuario{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "cortesAgendadosCliente")
    @ElementCollection
    private List<Float> cortesAgendados;

    @OneToMany(mappedBy = "cliente")
    @JsonIgnoreProperties("cliente")
    private List<Corte> cortes;

    @ElementCollection(fetch = FetchType.EAGER)
    @CollectionTable(name = "table_cliente_roles", joinColumns = @JoinColumn(name = "cliente_id"))
    @Column(name = "role_id")
    private List<String> roles = new ArrayList<>();

    @Override
    public String toString() {
        return "Cliente{" +
                "id=" + id +
                ", cortesAgendados=" + cortesAgendados +
                ", cortes=" + cortes +
                ", roles=" + roles +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Cliente cliente = (Cliente) o;
        return id != null && id.equals(cliente.id);
    }

    @Override
    public int hashCode() {
        return id != null ? id.hashCode() : 0;
    }


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public List<Float> getCortesAgendados() {
        return cortesAgendados;
    }

    public void setCortesAgendados(List<Float> cortesAgendados) {
        this.cortesAgendados = cortesAgendados;
    }

    public List<Corte> getCortes() {
        return cortes;
    }

    public void setCortes(List<Corte> cortes) {
        this.cortes = cortes;
    }

    public List<String> getRoles() {
        return roles;
    }

    public void setRoles(List<String> roles) {
        this.roles = roles;
    }
}
