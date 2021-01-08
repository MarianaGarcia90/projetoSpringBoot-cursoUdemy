package com.udemy.projeto.model;

import com.fasterxml.jackson.annotation.JsonFormat;

import javax.persistence.*;
import java.io.Serializable;
import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashSet;
import java.util.Locale;
import java.util.Set;

@Entity
public class Pedido implements Serializable {
    private static final long serialVersionUID = 1l;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @JsonFormat(pattern = "dd/MM/yyyy HH:mm")
    private Date instant;

    @OneToOne(cascade = CascadeType.ALL, mappedBy = "pedido") //para acertar a transição
    private Payment payment;

    @ManyToOne
    @JoinColumn(name = "costumer_id")
    private Costumer costumer;

    @ManyToOne
    @JoinColumn(name = "delivery_address_id")
    private Address deliveryAddress;

    @OneToMany(mappedBy = "id.pedido")
    private Set<Item> items = new HashSet<>(); //para não repetir

    public Pedido(){}

    public Pedido(Integer id, Date instant, Costumer costumer, Address deliveryAddress) {
        this.id = id;
        this.instant = instant;
        this.costumer = costumer;
        this.deliveryAddress = deliveryAddress;
    }

    public double getAmount(){
        double sum = 0.0;
        for(Item item : items){
            sum = sum + item.getSubTotal();
        }
        return sum;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Date getInstant() {
        return instant;
    }

    public void setInstant(Date instant) {
        this.instant = instant;
    }

    public Payment getPayment() {
        return payment;
    }

    public void setPayment(Payment payment) {
        this.payment = payment;
    }

    public Costumer getCostumer() {
        return costumer;
    }

    public void setCostumer(Costumer costumer) {
        this.costumer = costumer;
    }

    public Address getDeliveryAddress() {
        return deliveryAddress;
    }

    public void setDeliveryAddress(Address deliveryAddress) {
        this.deliveryAddress = deliveryAddress;
    }

    public Set<Item> getItems() {
        return items;
    }

    public void setItems(Set<Item> items) {
        this.items = items;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Pedido)) return false;

        Pedido pedido = (Pedido) o;

        return getId().equals(pedido.getId());
    }

    @Override
    public int hashCode() {
        return getId().hashCode();
    }

    @Override
    public String toString() {
        NumberFormat nf = NumberFormat.getCurrencyInstance(new Locale("pt", "BR"));
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
        final StringBuilder builder = new StringBuilder();
        builder.append("Order's number: ");
        builder.append(getId());
        builder.append(", Instant: ");
        builder.append(sdf.format(getInstant()));
        builder.append(", Costumer: ");
        builder.append(getCostumer().getName());
        builder.append(", Payment status: ");
        builder.append(getPayment().getPaymentState().getDescription());
        builder.append("\nDetalhes:\n");
        for(Item item : getItems()) {
            builder.append(item.toString());
        }
        builder.append("Amount: ");
        builder.append(nf.format(getAmount()));
        return builder.toString();
    }
}
