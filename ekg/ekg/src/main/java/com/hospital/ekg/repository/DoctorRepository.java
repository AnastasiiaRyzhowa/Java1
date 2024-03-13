package com.hospital.ekg.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.hospital.ekg.model.DoctorEntity;


public interface DoctorRepository extends JpaRepository<DoctorEntity, Integer>{
    DoctorEntity findByUsername(String username);
}
