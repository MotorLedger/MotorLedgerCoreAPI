package com.uptc.frw.motorledgercoreapi.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "model_options")
public class ModelOption {
    @Id
    @Column(name = "model_option_id")
    private long id;
    @Column(name = "model_price")
    private double price;
    @Column(name = "model_id")
    private long modelId;
    @Column(name = "option_id")
    private long optionId;

    public ModelOption() {
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public long getModelId() {
        return modelId;
    }

    public void setModelId(long modelId) {
        this.modelId = modelId;
    }

    public long getOptionId() {
        return optionId;
    }

    public void setOptionId(long optionId) {
        this.optionId = optionId;
    }

    @Override
    public String toString() {
        return "ModelOption{" +
                "id=" + id +
                ", price=" + price +
                ", modelId=" + modelId +
                ", optionId=" + optionId +
                '}';
    }
}
