package com.hospital.ekg.service;

import com.hospital.ekg.model.PatientEntity;
import com.hospital.ekg.repository.PatientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PatientService {
    @Autowired
    private PatientRepository patientRepository;

    public PatientEntity createPatient(PatientEntity patient){
        patientRepository.save(patient);
        return patient;
    }
}
