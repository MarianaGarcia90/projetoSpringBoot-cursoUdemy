package com.udemy.projeto.dto;

import com.udemy.projeto.model.Costumer;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;

public class CostumerDTO {
    private static final long serialVersionUID = 1l;

    private Integer id;

    @NotEmpty(message = "Preenchimento obrigatório")
    @Length(min = 5, max = 120, message = "O tamanho deve ser entre 5 e 120 caracteres")
    private String name;

    @NotEmpty(message = "Preenchimento obrigatório")
    @Email(message = "Email inválido")
    private String email;

    public CostumerDTO() {
    }

    public CostumerDTO(Costumer costumer) {
        id = costumer.getId();
        name = costumer.getName();
        email = costumer.getEmail();
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
}
