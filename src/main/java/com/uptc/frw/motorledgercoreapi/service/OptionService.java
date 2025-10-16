package com.uptc.frw.motorledgercoreapi.service;

import com.uptc.frw.motorledgercoreapi.model.Option;
import com.uptc.frw.motorledgercoreapi.repository.OptionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OptionService {
    @Autowired
    private OptionRepository optionRepository;

    public List<Option> getAllOptions() {
        return optionRepository.findAll();
    }

    public Option getOptionById(long id) {
        return optionRepository.findById(id).orElse(null);
    }
}
