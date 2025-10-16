package com.uptc.frw.motorledgercoreapi.controller;

import com.uptc.frw.motorledgercoreapi.model.SaleOption;
import com.uptc.frw.motorledgercoreapi.service.SaleOptionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("sale-options")
public class SaleOptionController {
    @Autowired
    private SaleOptionService saleOptionService;

    @GetMapping
    public List<SaleOption> getAllSaleOptions() {
        return saleOptionService.getAllSaleOptions();
    }

    @GetMapping("/{id}")
    public SaleOption getSaleOptionById(@PathVariable long id) {
        return saleOptionService.getSaleOptionById(id);
    }
}
