package com.hospital.ekg.service;

import com.hospital.ekg.model.RolesEntity;
import com.hospital.ekg.repository.RoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;

@Service
public class RoleService {
    @Autowired
    private RoleRepository roleRepository;

    public List<RolesEntity> loadRoles() {
        return roleRepository.findAll();
    }

    public Set<RolesEntity> loadRolesByName(String name) {
        return roleRepository.findByName(name);
    }

    public RolesEntity createNewRole(RolesEntity role) {
        roleRepository.save(role);
        return role;

    }
}