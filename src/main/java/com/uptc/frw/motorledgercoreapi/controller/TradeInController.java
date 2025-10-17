package com.uptc.frw.motorledgercoreapi.controller;

import com.uptc.frw.motorledgercoreapi.model.TradeIn;
import com.uptc.frw.motorledgercoreapi.service.TradeInService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("trade-ins")
public class TradeInController {
    @Autowired
    private TradeInService tradeInService;

    @GetMapping
    public List<TradeIn> getAll() {
        return tradeInService.getAllTradeIns();
    }

    @GetMapping("/{id}")
    public TradeIn getTradeInById(@PathVariable long id) {
        return tradeInService.getTradeInById(id);
    }

    @PostMapping
    public TradeIn saveTradeIn(@RequestBody TradeIn tradeIn) {
        return tradeInService.saveTradeIn(tradeIn);
    }

    @PutMapping
    public TradeIn updateTradeIn(@RequestBody TradeIn tradeIn) {
        return tradeInService.updateTradeIn(tradeIn);
    }

    @DeleteMapping("/{id}")
    public void deleteTradeIn(@PathVariable long id) {
        tradeInService.deleteTradeIn(id);
    }
}
