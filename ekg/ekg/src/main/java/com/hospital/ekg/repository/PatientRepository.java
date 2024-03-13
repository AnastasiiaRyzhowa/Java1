package com.hospital.ekg.repository;

import com.hospital.ekg.model.DoctorEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import com.hospital.ekg.model.PatientEntity;

import java.util.List;

public interface PatientRepository extends JpaRepository<PatientEntity, Integer>{
    PatientEntity findById(int id);
    List<PatientEntity> findAllByDoctor(DoctorEntity doctor);
}
