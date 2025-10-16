package com.uptc.frw.motorledgercoreapi.service;

import com.uptc.frw.motorledgercoreapi.model.Sale;
import com.uptc.frw.motorledgercoreapi.repository.SaleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SaleService {
    @Autowired
    private SaleRepository saleRepository;

    public List<Sale> getAllSales() {
        return saleRepository.findAll();
    }

    public Sale getSaleById(long id) {
        return saleRepository.findById(id).orElse(null);
    }
}
