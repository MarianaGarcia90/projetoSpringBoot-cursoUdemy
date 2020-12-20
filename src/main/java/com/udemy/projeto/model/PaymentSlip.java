package com.udemy.projeto.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.udemy.projeto.model.enums.PaymentState;

import javax.persistence.Entity;
import java.util.Date;

@Entity
public class PaymentSlip extends Payment {
    private static final long serialVersionUID = 1l;

    @JsonFormat(pattern = "dd/MM/yyyy")
    private Date dueDate;
    @JsonFormat(pattern = "dd/MM/yyyy")
    private Date payday;

    public PaymentSlip() {
    }

    public PaymentSlip(Integer id, PaymentState paymentState, Pedido pedido, Date dueDate, Date payday) {
        super(id, paymentState, pedido);
        this.dueDate = dueDate;
        this.payday = payday;
    }

    public Date getDueDate() {
        return dueDate;
    }

    public void setDueDate(Date dueDate) {
        this.dueDate = dueDate;
    }

    public Date getPayday() {
        return payday;
    }

    public void setPayday(Date payday) {
        this.payday = payday;
    }
}
