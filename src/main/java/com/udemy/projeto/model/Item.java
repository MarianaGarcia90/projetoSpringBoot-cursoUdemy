package com.udemy.projeto.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import java.io.Serializable;
import java.text.NumberFormat;
import java.util.Locale;
import java.util.Objects;

@Entity
public class Item implements Serializable {
    private static final long serialVersionUID = 1l;

    @JsonIgnore //não serializa ngm
    @EmbeddedId //id embutido num tipo auxiliar
    private ItemPK id = new ItemPK();

    private Double discount;
    private Integer amount;
    private Double price;

    public Item(){
    }

    public Item(Pedido pedido, Product product, Double discount, Integer amount, Double price) {
        id.setPedido(pedido);
        id.setProduct(product);
        this.discount = discount;
        this.amount = amount;
        this.price = price;
    }

    public double getSubTotal() {
        return (price - discount) * amount;
    }

    public ItemPK getId() {
        return id;
    }

    public void setId(ItemPK id) {
        this.id = id;
    }

    public Double getDiscount() {
        return discount;
    }

    public void setDiscount(Double discount) {
        this.discount = discount;
    }

    public Integer getAmount() {
        return amount;
    }

    public void setAmount(Integer amount) {
        this.amount = amount;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    @JsonIgnore
    public Pedido getPedido() {
        return id.getPedido();
    }

    public void setPedido(Pedido pedido) {
        id.setPedido(pedido);
    }

    public Product getProduct() {
        return id.getProduct();
    }

    public void setProduct(Product product) {
        id.setProduct(product);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Item)) return false;
        Item item = (Item) o;
        return getId().equals(item.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId());
    }


    @Override
    public String toString() {
        NumberFormat nf = NumberFormat.getCurrencyInstance(new Locale("pt", "BR"));
        final StringBuilder builder = new StringBuilder("Item{");
        builder.append(getProduct().getName());
        builder.append(", Amount: ");
        builder.append(getAmount());
        builder.append(", Unit price: ");
        builder.append(getPrice());
        builder.append(", Subtotal: ");
        builder.append(nf.format(getSubTotal()));
        builder.append("\n");
        return builder.toString();
    }

}
