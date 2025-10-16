package com.uptc.frw.motorledgercoreapi.service;

import com.uptc.frw.motorledgercoreapi.model.TradeIn;
import com.uptc.frw.motorledgercoreapi.repository.TradeInRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TradeInService {
    @Autowired
    private TradeInRepository tradeInRepository;
    public List<TradeIn> findAllTradeIn(){
        return tradeInRepository.findAll();
    }
    public TradeIn getTradeInById(Long id){
        TradeIn tradeIn = tradeInRepository.findById(id).orElse(null);
        return tradeIn;
    }
}
