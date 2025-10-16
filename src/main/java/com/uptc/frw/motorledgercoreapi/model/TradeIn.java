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
    private long idTradeIns;
    @Column(name = "trade_in_plate")
    private String plate;
    @Column(name = "trade_in_appraisal_price")
    private long appraisalPrice;
    @Column(name = "trade_in_cession_date")
    private Date cessionDate;
    @Column(name = "trade_in_customer_id")
    private long idCustomer;
    @Column(name = "trade_in_model_id")
    private long idModel;

    public TradeIn() {
    }

    public long getIdTradeIns() {
        return idTradeIns;
    }

    public void setIdTradeIns(long idTradeIns) {
        this.idTradeIns = idTradeIns;
    }

    public String getPlate() {
        return plate;
    }

    public void setPlate(String plate) {
        this.plate = plate;
    }

    public long getAppraisalPrice() {
        return appraisalPrice;
    }

    public void setAppraisalPrice(long appraisalPrice) {
        this.appraisalPrice = appraisalPrice;
    }

    public Date getCessionDate() {
        return cessionDate;
    }

    public void setCessionDate(Date cessionDate) {
        this.cessionDate = cessionDate;
    }

    public long getIdCustomer() {
        return idCustomer;
    }

    public void setIdCustomer(long idCustomer) {
        this.idCustomer = idCustomer;
    }

    public long getIdModel() {
        return idModel;
    }

    public void setIdModel(long idModel) {
        this.idModel = idModel;
    }

    @Override
    public String toString() {
        return "TradeIn{" +
                "idTradeIns=" + idTradeIns +
                ", plate='" + plate + '\'' +
                ", appraisalPrice=" + appraisalPrice +
                ", cessionDate=" + cessionDate +
                ", idCustomer=" + idCustomer +
                ", idModel=" + idModel +
                '}';
    }
}
