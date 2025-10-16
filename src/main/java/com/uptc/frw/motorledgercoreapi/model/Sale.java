package com.uptc.frw.motorledgercoreapi.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

import java.util.Date;
@Entity
@Table(name = "sales")
public class Sale {
    @Id
    @Column(name = "sale_id")
    private long idSales;
    @Column(name = "sale_date")
    private Date date;
    @Column(name = "sale_new_plate")
    private String newPlate;
    @Column(name = "sale_total_price")
    private long totalPrice;
    @Column(name = "sale_customer_id")
    private long idCustomer;
    @Column(name = "sale_seller_id")
    private long idSeller;
    @Column(name = "model_id")
    private long modelId;
    @Column(name = "trade_in_id")
    private long idTradeIns;

    public Sale() {
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public long getIdSales() {
        return idSales;
    }

    public void setIdSales(long idSales) {
        this.idSales = idSales;
    }

    public String getNewPlate() {
        return newPlate;
    }

    public void setNewPlate(String newPlate) {
        this.newPlate = newPlate;
    }

    public long getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(long totalPrice) {
        this.totalPrice = totalPrice;
    }

    public long getIdCustomer() {
        return idCustomer;
    }

    public void setIdCustomer(long idCustomer) {
        this.idCustomer = idCustomer;
    }

    public long getIdSeller() {
        return idSeller;
    }

    public void setIdSeller(long idSeller) {
        this.idSeller = idSeller;
    }

    public long getModelId() {
        return modelId;
    }

    public void setModelId(long modelId) {
        this.modelId = modelId;
    }

    public long getIdTradeIns() {
        return idTradeIns;
    }

    public void setIdTradeIns(long idTradeIns) {
        this.idTradeIns = idTradeIns;
    }

    @Override
    public String toString() {
        return "Sale{" +
                "idSales=" + idSales +
                ", date=" + date +
                ", newPlate='" + newPlate + '\'' +
                ", totalPrice=" + totalPrice +
                ", idCustomer=" + idCustomer +
                ", idSeller=" + idSeller +
                ", modelId=" + modelId +
                ", idTradeIns=" + idTradeIns +
                '}';
    }
}
