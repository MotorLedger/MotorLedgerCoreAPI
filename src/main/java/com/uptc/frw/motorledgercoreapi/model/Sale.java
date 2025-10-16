package com.uptc.frw.motorledgercoreapi.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

import java.time.LocalDateTime;
import java.util.Date;

@Entity
@Table(name = "sales")
public class Sale {
    @Id
    @Column(name = "sale_id")
    private long id;
    @Column(name = "sale_date")
    private LocalDateTime date;
    @Column(name = "sale_new_plate")
    private String newPlate;
    @Column(name = "sale_total_price")
    private double totalPrice;
    @Column(name = "sale_customer_id")
    private long customerId;
    @Column(name = "sale_seller_id")
    private long sellerId;
    @Column(name = "sale_model_id")
    private long modelId;
    @Column(name = "trade_in_id")
    private long tradeInId;

    public Sale() {
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public LocalDateTime getDate() {
        return date;
    }

    public void setDate(LocalDateTime date) {
        this.date = date;
    }

    public String getNewPlate() {
        return newPlate;
    }

    public void setNewPlate(String newPlate) {
        this.newPlate = newPlate;
    }

    public double getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(double totalPrice) {
        this.totalPrice = totalPrice;
    }

    public long getCustomerId() {
        return customerId;
    }

    public void setCustomerId(long customerId) {
        this.customerId = customerId;
    }

    public long getSellerId() {
        return sellerId;
    }

    public void setSellerId(long sellerId) {
        this.sellerId = sellerId;
    }

    public long getModelId() {
        return modelId;
    }

    public void setModelId(long modelId) {
        this.modelId = modelId;
    }

    public long getTradeInId() {
        return tradeInId;
    }

    public void setTradeInId(long tradeInId) {
        this.tradeInId = tradeInId;
    }

    @Override
    public String toString() {
        return "Sale{" +
                "id=" + id +
                ", date=" + date +
                ", newPlate='" + newPlate + '\'' +
                ", totalPrice=" + totalPrice +
                ", customerId=" + customerId +
                ", sellerId=" + sellerId +
                ", modelId=" + modelId +
                ", tradeInId=" + tradeInId +
                '}';
    }
}
