package com.uptc.frw.motorledgercoreapi.model;

import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name = "options")
public class Option {
    @Id
    @Column(name = "option_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @Column(name = "option_name")
    private String name;
    @Column(name = "option_description")
    private String description;
    @OneToMany(mappedBy = "option")
    private List<ModelOption> modelOptions;

    public Option() {
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

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public List<ModelOption> getModelOptions() {
        return modelOptions;
    }

    public void setModelOptions(List<ModelOption> modelOptions) {
        this.modelOptions = modelOptions;
    }

    @Override
    public String toString() {
        return "Option{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                '}';
    }
}
