package com.udemy.projeto.model;

import com.udemy.projeto.model.enums.PaymentState;

import java.util.Date;

public class PaymentSlip extends Payment {
    private static final long serialVersionUID = 1l;

    private Date dueDate;
    private Date payday;

    public PaymentSlip() {
    }

    public PaymentSlip(Integer id, PaymentState paymentState, Order order, Client client, Address address, Date dueDate, Date payday) {
        super(id, paymentState, order, client, address);
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
