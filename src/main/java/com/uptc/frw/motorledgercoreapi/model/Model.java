package com.uptc.frw.motorledgercoreapi.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "models")
public class Model {
    @Id
    @Column(name = "model_id")
    private long modelId;
    @Column(name = "model_name")
    private String modelName;
    @Column(name = "model_displacement")
    private long modelDisplacement;
    @Column(name = "model_base_price")
    private long modelBasePrice;
    @Column(name = "brand_id")
    private long brandId;

    public Model() {
    }

    public long getModelId() {
        return modelId;
    }

    public void setModelId(long modelId) {
        this.modelId = modelId;
    }

    public String getModelName() {
        return modelName;
    }

    public void setModelName(String modelName) {
        this.modelName = modelName;
    }

    public long getModelDisplacement() {
        return modelDisplacement;
    }

    public void setModelDisplacement(long modelDisplacement) {
        this.modelDisplacement = modelDisplacement;
    }

    public long getModelBasePrice() {
        return modelBasePrice;
    }

    public void setModelBasePrice(long modelBasePrice) {
        this.modelBasePrice = modelBasePrice;
    }

    public long getBrandId() {
        return brandId;
    }

    public void setBrandId(long brandId) {
        this.brandId = brandId;
    }

    @Override
    public String toString() {
        return "Model{" +
                "modelId=" + modelId +
                ", modelName='" + modelName + '\'' +
                ", modelDisplacement=" + modelDisplacement +
                ", modelBasePrice=" + modelBasePrice +
                ", brandId=" + brandId +
                '}';
    }
}
