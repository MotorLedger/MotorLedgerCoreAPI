package com.uptc.frw.motorledgercoreapi.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;

import java.util.Date;

@Entity
@Table(name = "trade_ins")
public class TradeIn {
    @Id
    @Column(name = "trade_in_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @Column(name = "trade_in_plate")
    private String plate;
    @Column(name = "trade_in_appraisal_price")
    private double appraisalPrice;
    @Column(name = "trade_in_cession_date")
    private Date cessionDate;
    @Column(name = "trade_in_customer_id", insertable = false, updatable = false)
    private long customerId;
    @Column(name = "trade_in_model_id", insertable = false, updatable = false)
    private long modelId;
    @ManyToOne
    @JoinColumn(name = "trade_in_model_id")
    private Model model;
    @ManyToOne
    @JoinColumn(name = "trade_in_customer_id")
    private User customer;
    @JsonIgnore
    @OneToOne(mappedBy = "tradeIn")
    private Sale sale;

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

    public Model getModel() {
        return model;
    }

    public void setModel(Model model) {
        this.model = model;
    }

    public User getCustomer() {
        return customer;
    }

    public void setCustomer(User customer) {
        this.customer = customer;
    }

    public Sale getSale() {
        return sale;
    }

    public void setSale(Sale sale) {
        this.sale = sale;
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
