package com.uptc.frw.motorledgercoreapi.controller;

import com.uptc.frw.motorledgercoreapi.model.TradeIn;
import com.uptc.frw.motorledgercoreapi.service.TradeInService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("TradeIns")
public class TradeInController {
    @Autowired
    private TradeInService tradeInService;
    @GetMapping
    public List<TradeIn> getAll() {
        return tradeInService.findAllTradeIn();
    }
    @GetMapping("/{id}")
    public TradeIn getTradeIn(@PathVariable Long id) {
        return tradeInService.getTradeInById(id);
    }
}
