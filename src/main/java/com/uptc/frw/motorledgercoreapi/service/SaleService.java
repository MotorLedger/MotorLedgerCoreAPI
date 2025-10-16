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
    public List<Sale> findAllSale(){
        return saleRepository.findAll();
    }
    public Sale getSaleById(Long id){
        Sale sale = saleRepository.findById(id).orElse(null);
        return sale;
    }
}
