package com.uptc.frw.motorledgercoreapi.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "sale_options")
public class SaleOption {
    @Id
    @Column(name = "sale_option_id")
    private long id;
    @Column(name = "option_price_at_sale")
    private double optionPriceAtSale;
    @Column(name = "sale_id")
    private long saleId;
    @Column(name = "model_option_id")
    private long modelOptionId;

    public SaleOption() {
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public double getOptionPriceAtSale() {
        return optionPriceAtSale;
    }

    public void setOptionPriceAtSale(double optionPriceAtSale) {
        this.optionPriceAtSale = optionPriceAtSale;
    }

    public long getSaleId() {
        return saleId;
    }

    public void setSaleId(long saleId) {
        this.saleId = saleId;
    }

    public long getModelOptionId() {
        return modelOptionId;
    }

    public void setModelOptionId(long modelOptionId) {
        this.modelOptionId = modelOptionId;
    }

    @Override
    public String toString() {
        return "SaleOption{" +
                "id=" + id +
                ", optionPriceAtSale=" + optionPriceAtSale +
                ", saleId=" + saleId +
                ", modelOptionId=" + modelOptionId +
                '}';
    }
}
