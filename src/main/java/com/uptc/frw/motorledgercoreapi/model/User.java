package com.uptc.frw.motorledgercoreapi.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name = "users")
public class User {
    @Id
    @Column(name = "user_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @Column(name = "user_name")
    private String name;
    @Column(name = "user_address")
    private String address;
    @Column(name = "user_phone")
    private String phone;
    @JsonIgnore
    @OneToMany(mappedBy = "customer")
    private List<TradeIn> tradeIns;
    @JsonIgnore
    @OneToMany(mappedBy = "customer")
    private List<Sale> customerSales;
    @JsonIgnore
    @OneToMany(mappedBy = "seller")
    private List<Sale> sellerSales;

    public User() {
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public List<TradeIn> getTradeIns() {
        return tradeIns;
    }

    public void setTradeIns(List<TradeIn> tradeIns) {
        this.tradeIns = tradeIns;
    }

    public List<Sale> getCustomerSales() {
        return customerSales;
    }

    public void setCustomerSales(List<Sale> customerSales) {
        this.customerSales = customerSales;
    }

    public List<Sale> getSellerSales() {
        return sellerSales;
    }

    public void setSellerSales(List<Sale> sellerSales) {
        this.sellerSales = sellerSales;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", address='" + address + '\'' +
                ", phone='" + phone + '\'' +
                '}';
    }
}
