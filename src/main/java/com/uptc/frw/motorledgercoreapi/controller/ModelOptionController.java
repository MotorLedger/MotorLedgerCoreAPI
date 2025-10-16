package com.uptc.frw.motorledgercoreapi.controller;

import com.uptc.frw.motorledgercoreapi.model.ModelOption;
import com.uptc.frw.motorledgercoreapi.service.ModelOptionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("model-options")
public class ModelOptionController {

    @Autowired
    private ModelOptionService modelOptionService;

    @GetMapping
    public List<ModelOption> getAllModelOptions() {
        return modelOptionService.getAllModelOptions();
    }

    @GetMapping("/{id}")
    public ModelOption getModelOptionById(@PathVariable long id) {
        return modelOptionService.getModelOptionById(id);
    }
}
