package com.uptc.frw.motorledgercoreapi.service;

import com.uptc.frw.motorledgercoreapi.model.ModelOption;
import com.uptc.frw.motorledgercoreapi.repository.ModelOptionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ModelOptionService {

    @Autowired
    private ModelOptionRepository modelOptionRepository;

    public List<ModelOption> getAllModelOptions() {
        return modelOptionRepository.findAll();
    }

    public ModelOption getModelOptionById(long id) {
        return modelOptionRepository.findById(id).orElse(null);
    }
}





