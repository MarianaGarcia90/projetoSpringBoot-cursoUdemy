package com.udemy.projeto.model;


import com.fasterxml.jackson.annotation.JsonIgnore;
import com.udemy.projeto.model.enums.CostumerType;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
public class Costumer implements Serializable {
    private static final long serialVersionUID = 1l;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String name;
    private String email;
    private String cpfOrCnpj;
    private Integer type;

    @OneToMany(mappedBy = "costumer", cascade = CascadeType.ALL)
    private List<Address> addresses= new ArrayList<>();

    @ElementCollection //para entidade fraca
    @CollectionTable(name = "PHONE")
    private Set<String> phone =  new HashSet<>();//dessa forma já garante que não terá repetição de números

    @JsonIgnore
    @OneToMany(mappedBy = "costumer")
    private List<Pedido> pedidos = new ArrayList<>();

    public Costumer(){
    }

    public Costumer(Integer id, String name, String email, String cpfOrCnpj, CostumerType type) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.cpfOrCnpj = cpfOrCnpj;
        this.type = (type==null) ? null : type.getCod();
    }

    public static long getSerialVersionUID() {
        return serialVersionUID;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getCpfOrCnpj() {
        return cpfOrCnpj;
    }

    public void setCpfOrCnpj(String cpfOrCnpj) {
        this.cpfOrCnpj = cpfOrCnpj;
    }

    public CostumerType getType() {
        return CostumerType.toEnum(type);
    }

    public void setType(CostumerType type) {
        this.type = type.getCod();
    }

    public List<Address> getAddresses() {
        return addresses;
    }

    public void setAddresses(List<Address> addresses) {
        this.addresses = addresses;
    }

    public Set<String> getPhone() {
        return phone;
    }

    public void setPhone(Set<String> phone) {
        this.phone = phone;
    }

    public List<Pedido> getPedidos() {
        return pedidos;
    }

    public void setPedidos(List<Pedido> pedidos) {
        this.pedidos = pedidos;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Costumer)) return false;

        Costumer costumer = (Costumer) o;

        return getId().equals(costumer.getId());
    }

    @Override
    public int hashCode() {
        return getId().hashCode();
    }
}
