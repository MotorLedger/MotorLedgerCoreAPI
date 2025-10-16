package com.uptc.frw.motorledgercoreapi.service;

import com.uptc.frw.motorledgercoreapi.model.Model;
import com.uptc.frw.motorledgercoreapi.repository.ModelRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ModelService {
    @Autowired
    private ModelRepository modelRepository;

    public List<Model> getAllModels() {
        return modelRepository.findAll();
    }

    public Model getModelById(long id) {
        return modelRepository.findById(id).orElse(null);
    }
}

