package com.uptc.frw.motorledgercoreapi.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name = "models")
public class Model {
    @Id
    @Column(name = "model_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @Column(name = "model_name")
    private String name;
    @Column(name = "model_displacement")
    private double displacement;
    @Column(name = "model_base_price")
    private double basePrice;
    @Column(name = "brand_id", insertable = false, updatable = false)
    private long brandId;
    @ManyToOne
    @JoinColumn(name = "brand_id")
    private Brand brand;
    @JsonIgnore
    @OneToMany(mappedBy = "model")
    private List<TradeIn> tradeIns;
    @JsonIgnore
    @OneToMany(mappedBy = "model")
    private List<ModelOption> modelOptions;
    @JsonIgnore
    @OneToMany(mappedBy = "model")
    private List<Sale> sales;

    public Model() {
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

    public double getDisplacement() {
        return displacement;
    }

    public void setDisplacement(double displacement) {
        this.displacement = displacement;
    }

    public double getBasePrice() {
        return basePrice;
    }

    public void setBasePrice(double basePrice) {
        this.basePrice = basePrice;
    }

    public long getBrandId() {
        return brandId;
    }

    public void setBrandId(long brandId) {
        this.brandId = brandId;
    }

    public Brand getBrand() {
        return brand;
    }

    public void setBrand(Brand brand) {
        this.brand = brand;
    }

    public List<TradeIn> getTradeIns() {
        return tradeIns;
    }

    public void setTradeIns(List<TradeIn> tradeIns) {
        this.tradeIns = tradeIns;
    }

    public List<ModelOption> getModelOptions() {
        return modelOptions;
    }

    public void setModelOptions(List<ModelOption> modelOptions) {
        this.modelOptions = modelOptions;
    }

    public List<Sale> getSales() {
        return sales;
    }

    public void setSales(List<Sale> sales) {
        this.sales = sales;
    }

    @Override
    public String toString() {
        return "Model{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", displacement=" + displacement +
                ", basePrice=" + basePrice +
                ", brandId=" + brandId +
                '}';
    }
}
