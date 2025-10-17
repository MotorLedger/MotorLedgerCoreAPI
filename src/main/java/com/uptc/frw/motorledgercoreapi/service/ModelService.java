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

    @Autowired
    private BrandService brandService;

    public List<Model> getAllModels() {
        return modelRepository.findAll();
    }

    public Model getModelById(long id) {
        return modelRepository.findById(id).orElse(null);
    }

    public Model saveModel(Model newModel) {
        newModel.setBrand(brandService.getBrandById(newModel.getBrandId()));
        return modelRepository.save(newModel);
    }

    public Model updateModel(Model oldModel) {
        Model newModel = getModelById(oldModel.getId());
        if (newModel != null) {
            newModel.setName(oldModel.getName());
            newModel.setDisplacement(oldModel.getDisplacement());
            newModel.setBasePrice(oldModel.getBasePrice());
            return saveModel(newModel);
        } else {
            throw new RuntimeException("Model not found");
        }
    }

    public void deleteModel(long id) {
        modelRepository.deleteById(id);
    }
}

