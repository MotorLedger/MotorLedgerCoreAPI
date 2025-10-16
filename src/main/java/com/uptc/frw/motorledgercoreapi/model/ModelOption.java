package com.uptc.frw.motorledgercoreapi.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name="model_options")
public class ModelOption {
    @Id
    @Column(name = "model_option_id")
    private long modelOptionId;
    @Column(name = "model_price")
    private long modelPrice;
    @Column(name="model_id")
    private long modelId;
    @Column(name = "option_id")
    private long optionId;

    public ModelOption() {
    }

    public long getModelOptionId() {
        return modelOptionId;
    }

    public void setModelOptionId(long modelOptionId) {
        this.modelOptionId = modelOptionId;
    }

    public long getModelPrice() {
        return modelPrice;
    }

    public void setModelPrice(long modelPrice) {
        this.modelPrice = modelPrice;
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
                "modelOptionId=" + modelOptionId +
                ", modelPrice=" + modelPrice +
                ", modelId=" + modelId +
                ", optionId=" + optionId +
                '}';
    }
}
