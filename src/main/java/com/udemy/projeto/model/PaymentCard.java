package com.udemy.projeto.model;

import com.udemy.projeto.model.enums.PaymentState;

public class PaymentCard extends Payment{
    private static final long serialVersionUID = 1l;

    private Integer numberOfInstallments;

    public PaymentCard(){
    }

    public PaymentCard(Integer id, PaymentState paymentState, Order order, Client client, Address address, Integer numberOfInstallments) {
        super(id, paymentState, order, client, address);
        this.numberOfInstallments = numberOfInstallments;
    }

    public Integer getNumberOfInstallments() {
        return numberOfInstallments;
    }

    public void setNumberOfInstallments(Integer numberOfInstallments) {
        this.numberOfInstallments = numberOfInstallments;
    }
}
