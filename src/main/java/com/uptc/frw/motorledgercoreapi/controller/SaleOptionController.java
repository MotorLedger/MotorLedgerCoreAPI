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
@RequestMapping("SaleOptions")
public class SaleOptionController {
    @Autowired
    private SaleOptionService saleOptionService;
    @GetMapping
    public List<SaleOption> getAll() {
        return saleOptionService.findAllSaleOption();
    }
    @GetMapping("/{id}")
    public SaleOption getSaleOption(@PathVariable Long id) {
        return saleOptionService.getSaleOptionById(id);
    }
}
