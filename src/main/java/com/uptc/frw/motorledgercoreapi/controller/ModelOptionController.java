package com.uptc.frw.motorledgercoreapi.controller;

import com.uptc.frw.motorledgercoreapi.model.ModelOption;
import com.uptc.frw.motorledgercoreapi.service.ModelOptionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

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

    @PostMapping
    public ModelOption saveModelOption(@RequestBody ModelOption modelOption) {
        return modelOptionService.saveModelOption(modelOption);
    }

    @PutMapping
    public ModelOption updateModelOption(@RequestBody ModelOption modelOption) {
        return modelOptionService.updateModelOption(modelOption);
    }

    @DeleteMapping("/{id}")
    public void deleteModelOptionById(@PathVariable long id) {
        modelOptionService.deleteModelOptionById(id);
    }
}
