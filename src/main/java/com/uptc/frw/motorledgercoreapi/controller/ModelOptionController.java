package com.uptc.frw.motorledgercoreapi.controller;

import com.uptc.frw.motorledgercoreapi.model.ModelOption;
import com.uptc.frw.motorledgercoreapi.service.ModelOptionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("modelOptions")
public class ModelOptionController {

    @Autowired
    private ModelOptionService modelOptionService;

    public List<ModelOption> getAllModelOptions(){
        return modelOptionService.getAllModelOptions();
    }

}
