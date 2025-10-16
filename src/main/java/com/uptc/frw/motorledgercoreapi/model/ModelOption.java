package com.uptc.frw.motorledgercoreapi.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name = "model_options")
public class ModelOption {
    @Id
    @Column(name = "model_option_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @Column(name = "model_price")
    private double price;
    @Column(name = "model_id", insertable = false, updatable = false)
    private long modelId;
    @Column(name = "option_id", insertable = false, updatable = false)
    private long optionId;
    @ManyToOne
    @JoinColumn(name = "model_id")
    private Model model;
    @ManyToOne
    @JoinColumn(name = "option_id")
    private Option option;
    @JsonIgnore
    @OneToMany(mappedBy = "modelOption")
    private List<SaleOption> saleOptions;

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

    public Model getModel() {
        return model;
    }

    public void setModel(Model model) {
        this.model = model;
    }

    public Option getOption() {
        return option;
    }

    public void setOption(Option option) {
        this.option = option;
    }

    public List<SaleOption> getSaleOptions() {
        return saleOptions;
    }

    public void setSaleOptions(List<SaleOption> saleOptions) {
        this.saleOptions = saleOptions;
    }

    @Override
    public String toString() {
        return "ModelOption{" + "id=" + id + ", price=" + price + ", modelId=" + modelId + ", optionId=" + optionId + '}';
    }
}
