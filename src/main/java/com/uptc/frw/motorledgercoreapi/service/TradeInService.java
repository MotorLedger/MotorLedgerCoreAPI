package com.uptc.frw.motorledgercoreapi.service;

import com.uptc.frw.motorledgercoreapi.model.Model;
import com.uptc.frw.motorledgercoreapi.model.TradeIn;
import com.uptc.frw.motorledgercoreapi.model.User;
import com.uptc.frw.motorledgercoreapi.repository.TradeInRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TradeInService {
    @Autowired
    private TradeInRepository tradeInRepository;

    @Autowired
    private ModelService modelService;

    @Autowired
    private UserService userService;

    public List<TradeIn> getAllTradeIns() {
        return tradeInRepository.findAll();
    }

    public TradeIn getTradeInById(long id) {
        return tradeInRepository.findById(id).orElse(null);
    }

    public TradeIn saveTradeIn(TradeIn newTradeIn) {
        Model model = modelService.getModelById(newTradeIn.getModelId());
        newTradeIn.setModel(model);
        User customer = userService.getUserById(newTradeIn.getCustomerId());
        newTradeIn.setCustomer(customer);
        return tradeInRepository.save(newTradeIn);
    }

    public TradeIn updateTradeIn(TradeIn oldTradeIn) {
        TradeIn newTradeIn = getTradeInById(oldTradeIn.getId());
        if (newTradeIn != null) {
            newTradeIn.setPlate(oldTradeIn.getPlate());
            newTradeIn.setAppraisalPrice(oldTradeIn.getAppraisalPrice());
            newTradeIn.setCessionDate(oldTradeIn.getCessionDate());
            return saveTradeIn(newTradeIn);
        } else {
            throw new RuntimeException("Trade in not found");
        }
    }

    public void deleteTradeIn(long id) {
        tradeInRepository.deleteById(id);
    }
}
