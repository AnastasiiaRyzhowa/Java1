package com.hospital.ekg.repository;

import com.hospital.ekg.model.PatientEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import com.hospital.ekg.model.SurveyEntity;

import java.util.List;

public interface SurveyRepository extends JpaRepository<SurveyEntity, Integer> {
    SurveyEntity findSurveyEntityById(int id);

    List<SurveyEntity> findAllByPatient(PatientEntity patient);
}
