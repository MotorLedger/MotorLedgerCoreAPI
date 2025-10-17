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

    public Option saveOption(Option newOption) {
        return optionRepository.save(newOption);
    }

    public Option updateOption(Option oldOption) {
        Option newOption = getOptionById(oldOption.getId());
        if (newOption != null) {
            newOption.setName(oldOption.getName());
            newOption.setDescription(oldOption.getDescription());
            return saveOption(newOption);
        } else {
            throw new RuntimeException("Option not found");
        }
    }

    public void deleteOption(long id) {
        optionRepository.deleteById(id);
    }
}
