package com.uptc.frw.motorledgercoreapi.service;

import com.uptc.frw.motorledgercoreapi.model.Brand;
import com.uptc.frw.motorledgercoreapi.repository.BrandRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BrandService {

    @Autowired
    private BrandRepository brandRepository;

    public List<Brand> getAllBrands() {
        return brandRepository.findAll();
    }

    public Brand getBrandById(long id) {
        return brandRepository.findById(id).orElse(null);
    }

    public Brand saveBrand(Brand newBrand) {
        return brandRepository.save(newBrand);
    }

    public Brand updateBrand(Brand oldBrand) {
        Brand newBrand = getBrandById(oldBrand.getId());
        if (newBrand != null) {
            newBrand.setName(oldBrand.getName());
            return saveBrand(newBrand);
        } else {
            throw new RuntimeException("Brand not found");
        }
    }

    public void deleteBrand(long id) {
        brandRepository.deleteById(id);
    }
}
