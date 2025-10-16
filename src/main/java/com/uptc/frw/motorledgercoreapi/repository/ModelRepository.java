package com.uptc.frw.motorledgercoreapi.repository;

import com.uptc.frw.motorledgercoreapi.model.Model;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ModelRepository extends JpaRepository<Model, Long> {
}
