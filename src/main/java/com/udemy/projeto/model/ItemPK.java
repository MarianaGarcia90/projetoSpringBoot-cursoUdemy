package com.udemy.projeto.model;

import javax.persistence.Embeddable;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import java.io.Serializable;
import java.util.Objects;

@Embeddable //pra avisar q é classe de subtipo (onde vamos gerar o id pra Item)
public class ItemPK implements Serializable {
    private static final long serialVersionUID = 1l;

    @ManyToOne
    @JoinColumn(name = "pedido_id")
    private Pedido pedido;

    @ManyToOne
    @JoinColumn(name = "product_id")
    private Product product;

    public Pedido getPedido() {
        return pedido;
    }

    public void setPedido(Pedido pedido) {
        this.pedido = pedido;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof ItemPK)) return false;
        ItemPK itemPK = (ItemPK) o;
        return getPedido().equals(itemPK.getPedido()) && getProduct().equals(itemPK.getProduct());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getPedido(), getProduct());
    }
}
