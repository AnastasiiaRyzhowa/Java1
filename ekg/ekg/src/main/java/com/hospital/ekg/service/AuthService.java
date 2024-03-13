package com.hospital.ekg.service;

import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.hospital.ekg.model.DoctorEntity;
import com.hospital.ekg.repository.DoctorRepository;

@Service
public class AuthService implements UserDetailsService {
    @Autowired
    private DoctorRepository doctorRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        DoctorEntity doctor = doctorRepository.findByUsername(username);
        if(doctor == null){
            throw new UsernameNotFoundException("User not exists by Username" + username);
        }

        Set<GrantedAuthority> authorities = doctor.getRoles().stream().map((role) -> new SimpleGrantedAuthority(role.getName())).collect(Collectors.toSet());
        return new org.springframework.security.core.userdetails.User(username, doctor.getPassword(), authorities);
    }
    public DoctorEntity loadUserDb(DoctorEntity user){
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        doctorRepository.save(user);
        return user;
    }
}
