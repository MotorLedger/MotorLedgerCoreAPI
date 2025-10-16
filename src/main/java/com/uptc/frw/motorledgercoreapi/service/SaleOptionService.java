package com.uptc.frw.motorledgercoreapi.service;

import com.uptc.frw.motorledgercoreapi.model.SaleOption;
import com.uptc.frw.motorledgercoreapi.repository.SaleOptionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SaleOptionService {
    @Autowired
    private SaleOptionRepository saleOptionRepository;
    public List<SaleOption> findAllSaleOption(){
        return saleOptionRepository.findAll();
    }
    public SaleOption getSaleOptionById(Long id){
        SaleOption saleOption = saleOptionRepository.findById(id).orElse(null);
        return saleOption;
    }
}
