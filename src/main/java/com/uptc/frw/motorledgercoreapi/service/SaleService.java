package com.uptc.frw.motorledgercoreapi.service;

import com.uptc.frw.motorledgercoreapi.model.Model;
import com.uptc.frw.motorledgercoreapi.model.Sale;
import com.uptc.frw.motorledgercoreapi.model.TradeIn;
import com.uptc.frw.motorledgercoreapi.model.User;
import com.uptc.frw.motorledgercoreapi.repository.SaleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SaleService {
    @Autowired
    private SaleRepository saleRepository;

    @Autowired
    private UserService userService;

    @Autowired
    private ModelService modelService;

    @Autowired
    private TradeInService tradeInService;

    public List<Sale> getAllSales() {
        return saleRepository.findAll();
    }

    public Sale getSaleById(long id) {
        return saleRepository.findById(id).orElse(null);
    }

    public Sale saveSale(Sale newSale) {
        User customer = userService.getUserById(newSale.getCustomerId());
        newSale.setCustomer(customer);
        User seller = userService.getUserById(newSale.getSellerId());
        newSale.setSeller(seller);
        Model model = modelService.getModelById(newSale.getModelId());
        newSale.setModel(model);

        // Only look for TradeIn if tradeInId is not null
        if (newSale.getTradeInId() != null) {
            TradeIn tradeIn = tradeInService.getTradeInById(newSale.getTradeInId());
            newSale.setTradeIn(tradeIn);
        } else {
            newSale.setTradeIn(null);
        }
        return saleRepository.save(newSale);
    }

    public Sale updateSale(Sale oldSale) {
        Sale newSale = getSaleById(oldSale.getId());
        if (newSale != null) {
            newSale.setDate(oldSale.getDate());
            newSale.setNewPlate(oldSale.getNewPlate());
            newSale.setTotalPrice(oldSale.getTotalPrice());
            return saveSale(newSale);
        } else {
            throw new RuntimeException("Sale not found");
        }
    }

    public void deleteSale(long id) {
        saleRepository.deleteById(id);
    }
}
