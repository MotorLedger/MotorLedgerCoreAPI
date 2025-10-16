package com.uptc.frw.motorledgercoreapi.controller;


import com.uptc.frw.motorledgercoreapi.model.Model;
import com.uptc.frw.motorledgercoreapi.service.ModelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("models")
public class ModelController {

    @Autowired
    private ModelService modelService;

    public List<Model> getAllModel(){
        return modelService.getAllModels();
    }
}
