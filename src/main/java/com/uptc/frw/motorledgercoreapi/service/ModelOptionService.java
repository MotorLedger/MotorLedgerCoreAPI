package com.uptc.frw.motorledgercoreapi.service;

import com.uptc.frw.motorledgercoreapi.model.Model;
import com.uptc.frw.motorledgercoreapi.model.ModelOption;
import com.uptc.frw.motorledgercoreapi.model.Option;
import com.uptc.frw.motorledgercoreapi.repository.ModelOptionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ModelOptionService {

    @Autowired
    private ModelOptionRepository modelOptionRepository;

    @Autowired
    private ModelService modelService;

    @Autowired
    private OptionService optionService;

    public List<ModelOption> getAllModelOptions() {
        return modelOptionRepository.findAll();
    }

    public ModelOption getModelOptionById(long id) {
        return modelOptionRepository.findById(id).orElse(null);
    }

    public ModelOption saveModelOption(ModelOption newModelOption) {
        Model model = modelService.getModelById(newModelOption.getModelId());
        newModelOption.setModel(model);
        Option option = optionService.getOptionById(newModelOption.getOptionId());
        newModelOption.setOption(option);
        return modelOptionRepository.save(newModelOption);
    }

    public ModelOption updateModelOption(ModelOption oldModelOption) {
        ModelOption newModelOption = getModelOptionById(oldModelOption.getId());
        if (newModelOption != null) {
            newModelOption.setPrice(oldModelOption.getPrice());
            return saveModelOption(newModelOption);
        } else {
            throw new RuntimeException("Model option not found");
        }
    }

    public void deleteModelOptionById(long id) {
        modelOptionRepository.deleteById(id);
    }
}





