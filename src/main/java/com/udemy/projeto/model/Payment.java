package com.udemy.projeto.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import com.udemy.projeto.model.enums.PaymentState;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Inheritance(strategy = InheritanceType.JOINED) //para herança, ai escolher se vai ficar tudo num tabelão ou tabelinhas q precisam de join dpois
@JsonTypeInfo(use = JsonTypeInfo.Id.NAME, include = JsonTypeInfo.As.PROPERTY, property = "@type") // a classe vai ter um campo adicional type
public abstract class Payment implements Serializable {
    private static final long serialVersionUID = 1l;

    @Id
    private Integer id;
    private Integer paymentState;

    @JsonIgnore
    @OneToOne
    @JoinColumn(name = "pedido_id") //essas duas anotações (Join e Maps) são para manter o mesmo id para pedido e payment
    @MapsId
    private Pedido pedido;

    public Payment(){}

    public Payment(Integer id, PaymentState paymentState, Pedido pedido) {
        this.id = id;
        this.paymentState = (paymentState==null) ? null : paymentState.getCod();
        this.pedido = pedido;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public PaymentState getPaymentState() {
        return PaymentState.toEnum(paymentState);
    }

    public void setPaymentState(PaymentState paymentState) {
        this.paymentState = paymentState.getCod();
    }

    public Pedido getPedido() {
        return pedido;
    }

    public void setPedido(Pedido pedido) {
        this.pedido = pedido;
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
