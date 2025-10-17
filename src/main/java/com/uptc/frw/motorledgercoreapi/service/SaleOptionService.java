package com.uptc.frw.motorledgercoreapi.service;

import com.uptc.frw.motorledgercoreapi.model.ModelOption;
import com.uptc.frw.motorledgercoreapi.model.Sale;
import com.uptc.frw.motorledgercoreapi.model.SaleOption;
import com.uptc.frw.motorledgercoreapi.repository.SaleOptionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SaleOptionService {
    @Autowired
    private SaleOptionRepository saleOptionRepository;

    @Autowired
    private ModelOptionService modelOptionService;

    @Autowired
    private SaleService saleService;

    public List<SaleOption> getAllSaleOptions() {
        return saleOptionRepository.findAll();
    }

    public SaleOption getSaleOptionById(long id) {
        return saleOptionRepository.findById(id).orElse(null);
    }

    public SaleOption saveSaleOption(SaleOption newSaleOption) {
        Sale sale = saleService.getSaleById(newSaleOption.getSaleId());
        newSaleOption.setSale(sale);
        ModelOption modelOption = modelOptionService.getModelOptionById(newSaleOption.getModelOptionId());
        newSaleOption.setModelOption(modelOption);
        return saleOptionRepository.save(newSaleOption);
    }

    public SaleOption updateSaleOption(SaleOption oldSaleOption) {
        SaleOption newSaleOption = getSaleOptionById(oldSaleOption.getId());
        if (newSaleOption != null) {
            newSaleOption.setOptionPriceAtSale(oldSaleOption.getOptionPriceAtSale());
            return saveSaleOption(newSaleOption);
        } else {
            throw new RuntimeException("SaleOption not found");
        }
    }

    public void deleteSaleOption(long id) {
        saleOptionRepository.deleteById(id);
    }
}
