package com.udemy.projeto.model;

import java.io.Serializable;

public class Address implements Serializable{
    private static final long serialVersionUID = 1l;

    private Integer id;
    private String address;
    private String number;
    private String complement;
    private String neighborhood;
    private String zipcode;

    private Client client;

    private City city;

    public Address(){
    }

    public Address(Integer id, String address, String number, String complement, String neighborhood, String zipcode, Client client, City city) {
        this.id = id;
        this.address = address;
        this.number = number;
        this.complement = complement;
        this.neighborhood = neighborhood;
        this.zipcode = zipcode;
        this.client = client;
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

    public Client getClient() {
        return client;
    }

    public void setClient(Client client) {
        this.client = client;
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
