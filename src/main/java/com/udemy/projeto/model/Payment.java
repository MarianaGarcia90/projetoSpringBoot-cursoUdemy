package com.udemy.projeto.model;

import com.udemy.projeto.model.enums.PaymentState;

import javax.persistence.*;
import java.io.Serializable;

@Entity
public class Payment implements Serializable {
    private static final long serialVersionUID = 1l;

    @Id
    private Integer id;
    private PaymentState paymentState;

    @OneToOne
    @JoinColumn(name = "order_id") //essas duas anotações (Join e Maps) são para manter o mesmo id para order e payment
    @MapsId
    private Order order;

    private Client client;

    private Address address;

    public Payment(){}

    public Payment(Integer id, PaymentState paymentState, Order order, Client client, Address address) {
        this.id = id;
        this.paymentState = paymentState;
        this.order = order;
        this.client = client;
        this.address = address;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public PaymentState getPaymentState() {
        return paymentState;
    }

    public void setPaymentState(PaymentState paymentState) {
        this.paymentState = paymentState;
    }

    public Order getOrder() {
        return order;
    }

    public void setOrder(Order order) {
        this.order = order;
    }

    public Client getClient() {
        return client;
    }

    public void setClient(Client client) {
        this.client = client;
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Payment)) return false;

        Payment payment = (Payment) o;

        return getId().equals(payment.getId());
    }

    @Override
    public int hashCode() {
        return getId().hashCode();
    }
}
