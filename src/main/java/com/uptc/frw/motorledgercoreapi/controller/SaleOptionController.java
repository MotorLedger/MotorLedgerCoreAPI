package com.uptc.frw.motorledgercoreapi.controller;

import com.uptc.frw.motorledgercoreapi.model.SaleOption;
import com.uptc.frw.motorledgercoreapi.service.SaleOptionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

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

    @PostMapping
    public SaleOption createSaleOption(@RequestBody SaleOption saleOption) {
        return saleOptionService.saveSaleOption(saleOption);
    }

    @PutMapping
    public SaleOption updateSaleOption(@RequestBody SaleOption saleOption) {
        return saleOptionService.updateSaleOption(saleOption);
    }

    @DeleteMapping("/{id}")
    public void deleteSaleOption(@PathVariable long id) {
        saleOptionService.deleteSaleOption(id);
    }
}
