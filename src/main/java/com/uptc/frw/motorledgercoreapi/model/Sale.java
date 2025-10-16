package com.uptc.frw.motorledgercoreapi.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "sales")
public class Sale {
    @Id
    @Column(name = "sale_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @Column(name = "sale_date")
    private LocalDateTime date;
    @Column(name = "sale_new_plate")
    private String newPlate;
    @Column(name = "sale_total_price")
    private double totalPrice;
    @Column(name = "sale_customer_id", insertable = false, updatable = false)
    private long customerId;
    @Column(name = "sale_seller_id", insertable = false, updatable = false)
    private long sellerId;
    @Column(name = "sale_model_id", insertable = false, updatable = false)
    private long modelId;
    @Column(name = "trade_in_id", insertable = false, updatable = false)
    private Long tradeInId;
    @ManyToOne
    @JoinColumn(name = "sale_model_id")
    private Model model;
    @ManyToOne
    @JoinColumn(name = "sale_customer_id")
    private User customer;
    @ManyToOne
    @JoinColumn(name = "sale_seller_id")
    private User seller;
    @OneToOne
    @JoinColumn(name = "trade_in_id")
    private TradeIn tradeIn;
    @JsonIgnore
    @OneToMany(mappedBy = "sale")
    private List<SaleOption> saleOptions;

    public Sale() {
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public LocalDateTime getDate() {
        return date;
    }

    public void setDate(LocalDateTime date) {
        this.date = date;
    }

    public String getNewPlate() {
        return newPlate;
    }

    public void setNewPlate(String newPlate) {
        this.newPlate = newPlate;
    }

    public double getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(double totalPrice) {
        this.totalPrice = totalPrice;
    }

    public long getCustomerId() {
        return customerId;
    }

    public void setCustomerId(long customerId) {
        this.customerId = customerId;
    }

    public long getSellerId() {
        return sellerId;
    }

    public void setSellerId(long sellerId) {
        this.sellerId = sellerId;
    }

    public long getModelId() {
        return modelId;
    }

    public void setModelId(long modelId) {
        this.modelId = modelId;
    }

    public Long getTradeInId() {
        return tradeInId;
    }

    public void setTradeInId(Long tradeInId) {
        this.tradeInId = tradeInId;
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

    public User getSeller() {
        return seller;
    }

    public void setSeller(User seller) {
        this.seller = seller;
    }

    public TradeIn getTradeIn() {
        return tradeIn;
    }

    public void setTradeIn(TradeIn tradeIn) {
        this.tradeIn = tradeIn;
    }

    public List<SaleOption> getSaleOptions() {
        return saleOptions;
    }

    public void setSaleOptions(List<SaleOption> saleOptions) {
        this.saleOptions = saleOptions;
    }

    @Override
    public String toString() {
        return "Sale{" + "id=" + id + ", date=" + date + ", newPlate='" + newPlate + '\'' + ", totalPrice=" + totalPrice + ", customerId=" + customerId + ", sellerId=" + sellerId + ", modelId=" + modelId + ", tradeInId=" + tradeInId + '}';
    }
}
