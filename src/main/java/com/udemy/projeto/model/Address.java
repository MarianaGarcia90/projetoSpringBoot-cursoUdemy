package com.udemy.projeto.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.io.Serializable;

@Entity
public class Address implements Serializable{
    private static final long serialVersionUID = 1l;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String address;
    private String number;
    private String complement;
    private String neighborhood;
    private String zipcode;

    @JsonBackReference
    @ManyToOne
    @JoinColumn(name = "costumer_id")
    private Costumer costumer;

    @ManyToOne
    @JoinColumn(name = "city_id")
    private City city;

    public Address(){
    }

    public Address(Integer id, String address, String number, String complement, String neighborhood, String zipcode, Costumer costumer, City city) {
        this.id = id;
        this.address = address;
        this.number = number;
        this.complement = complement;
        this.neighborhood = neighborhood;
        this.zipcode = zipcode;
        this.costumer = costumer;
        this.city = city;
    }

    public static long getSerialVersionUID() {
        return serialVersionUID;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public String getComplement() {
        return complement;
    }

    public void setComplement(String complement) {
        this.complement = complement;
    }

    public String getNeighborhood() {
        return neighborhood;
    }

    public void setNeighborhood(String neighborhood) {
        this.neighborhood = neighborhood;
    }

    public String getZipcode() {
        return zipcode;
    }

    public void setZipcode(String zipcode) {
        this.zipcode = zipcode;
    }

    @JsonIgnore
    public Costumer getClient() {
        return costumer;
    }

    public void setClient(Costumer costumer) {
        this.costumer = costumer;
    }

    public City getCity() {
        return city;
    }

    public void setCity(City city) {
        this.city = city;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Address)) return false;

        Address address = (Address) o;

        return getId().equals(address.getId());
    }

    @Override
    public int hashCode() {
        return getId().hashCode();
    }
}
