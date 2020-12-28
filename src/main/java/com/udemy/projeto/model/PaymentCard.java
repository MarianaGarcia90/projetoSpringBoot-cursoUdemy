package com.udemy.projeto.model;

import com.fasterxml.jackson.annotation.JsonTypeName;
import com.udemy.projeto.model.enums.PaymentState;

import javax.persistence.Entity;

@Entity
@JsonTypeName("pagamentoComCartao")
public class PaymentCard extends Payment{
    private static final long serialVersionUID = 1l;

    private Integer numberOfInstallments;

    public PaymentCard(){
    }

    public PaymentCard(Integer id, PaymentState paymentState, Pedido pedido, Integer numberOfInstallments) {
        super(id, paymentState, pedido);
        this.numberOfInstallments = numberOfInstallments;
    }

    public Integer getNumberOfInstallments() {
        return numberOfInstallments;
    }

    public void setNumberOfInstallments(Integer numberOfInstallments) {
        this.numberOfInstallments = numberOfInstallments;
    }
}
