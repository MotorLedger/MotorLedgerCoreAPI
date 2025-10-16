package com.uptc.frw.motorledgercoreapi.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
@Entity
@Table(name = "sale_options")
public class SaleOption {
    @Id
    @Column(name = "sale_id")
    private long idSales;
    @Column(name = "model_option_id")
    private long modelOptionId;
    @Column(name = "option_price_at_sale")
    private long priceAtSale;

    public SaleOption() {
    }

    public long getIdSales() {
        return idSales;
    }

    public void setIdSales(long idSales) {
        this.idSales = idSales;
    }

    public long getModelOptionId() {
        return modelOptionId;
    }

    public void setModelOptionId(long modelOptionId) {
        this.modelOptionId = modelOptionId;
    }

    public long getPriceAtSale() {
        return priceAtSale;
    }

    public void setPriceAtSale(long priceAtSale) {
        this.priceAtSale = priceAtSale;
    }

    @Override
    public String toString() {
        return "SaleOption{" +
                "idSales=" + idSales +
                ", modelOptionId=" + modelOptionId +
                ", priceAtSale=" + priceAtSale +
                '}';
    }
}
