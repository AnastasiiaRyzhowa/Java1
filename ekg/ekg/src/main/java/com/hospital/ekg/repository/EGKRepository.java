package com.hospital.ekg.repository;

import com.hospital.ekg.model.SurveyEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import com.hospital.ekg.model.EKGEntity;

import java.util.List;

public interface EGKRepository extends JpaRepository<EKGEntity, Integer>{

    List<EKGEntity> findAllBySurvey(SurveyEntity survey);
    
}
