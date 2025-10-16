package com.uptc.frw.motorledgercoreapi.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

import java.util.Date;

@Entity
@Table(name = "trade_ins")
public class TradeIn {
    @Id
    @Column(name = "trade_in_id")
    private long id;
    @Column(name = "trade_in_plate")
    private String plate;
    @Column(name = "trade_in_appraisal_price")
    private double appraisalPrice;
    @Column(name = "trade_in_cession_date")
    private Date cessionDate;
    @Column(name = "trade_in_customer_id")
    private long customerId;
    @Column(name = "trade_in_model_id")
    private long modelId;

    public TradeIn() {
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getPlate() {
        return plate;
    }

    public void setPlate(String plate) {
        this.plate = plate;
    }

    public double getAppraisalPrice() {
        return appraisalPrice;
    }

    public void setAppraisalPrice(double appraisalPrice) {
        this.appraisalPrice = appraisalPrice;
    }

    public Date getCessionDate() {
        return cessionDate;
    }

    public void setCessionDate(Date cessionDate) {
        this.cessionDate = cessionDate;
    }

    public long getCustomerId() {
        return customerId;
    }

    public void setCustomerId(long customerId) {
        this.customerId = customerId;
    }

    public long getModelId() {
        return modelId;
    }

    public void setModelId(long modelId) {
        this.modelId = modelId;
    }

    @Override
    public String toString() {
        return "TradeIn{" +
                "id=" + id +
                ", plate='" + plate + '\'' +
                ", appraisalPrice=" + appraisalPrice +
                ", cessionDate=" + cessionDate +
                ", customerId=" + customerId +
                ", modelId=" + modelId +
                '}';
    }
}
