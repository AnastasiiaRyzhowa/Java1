package com.hospital.ekg.service;

import com.hospital.ekg.model.EKGEntity;
import com.hospital.ekg.model.SurveyEntity;
import com.hospital.ekg.repository.EGKRepository;
import com.hospital.ekg.repository.SurveyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SurveyService {
    @Autowired
    private SurveyRepository surveyRepository;

    @Autowired
    private EGKRepository repository;
    public SurveyEntity createSurvey(SurveyEntity survey){
        surveyRepository.save(survey);
        return survey;
    }

    public EKGEntity createEKG(EKGEntity ekg){
        repository.save(ekg);
        return ekg;
    }
}
