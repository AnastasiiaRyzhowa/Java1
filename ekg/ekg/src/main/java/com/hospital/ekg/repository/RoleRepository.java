package com.hospital.ekg.repository;

import java.util.Set;

import org.springframework.data.jpa.repository.JpaRepository;

import com.hospital.ekg.model.RolesEntity;

public interface RoleRepository extends JpaRepository<RolesEntity, Integer>{
    Set<RolesEntity> findByName(String name);
    
}
