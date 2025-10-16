package com.uptc.frw.motorledgercoreapi.controller;

import com.uptc.frw.motorledgercoreapi.model.Option;
import com.uptc.frw.motorledgercoreapi.service.OptionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("options")
public class OptionController {

    @Autowired
    private OptionService optionService;

    @GetMapping
    public List<Option> getAllOptions() {
        return optionService.getAllOptions();
    }

    @GetMapping("/{id}")
    public Option getOptionById(@PathVariable long id) {
        return optionService.getOptionById(id);
    }
}
