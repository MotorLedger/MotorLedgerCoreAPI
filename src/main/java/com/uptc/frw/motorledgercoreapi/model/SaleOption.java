package com.uptc.frw.motorledgercoreapi.model;

import jakarta.persistence.*;

@Entity
@Table(name = "sale_options")
public class SaleOption {
    @Id
    @Column(name = "sale_option_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @Column(name = "option_price_at_sale")
    private double optionPriceAtSale;
    @Column(name = "sale_id", insertable = false, updatable = false)
    private long saleId;
    @Column(name = "model_option_id", insertable = false, updatable = false)
    private long modelOptionId;
    @ManyToOne
    @JoinColumn(name = "model_option_id")
    private ModelOption modelOption;
    @ManyToOne
    @JoinColumn(name = "sale_id")
    private Sale sale;

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

    public ModelOption getModelOption() {
        return modelOption;
    }

    public void setModelOption(ModelOption modelOption) {
        this.modelOption = modelOption;
    }

    public Sale getSale() {
        return sale;
    }

    public void setSale(Sale sale) {
        this.sale = sale;
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
