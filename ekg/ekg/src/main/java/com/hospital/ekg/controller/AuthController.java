package com.hospital.ekg.controller;

import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
// import org.springframework.boot.autoconfigure.neo4j.Neo4jProperties.Authentication;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.hospital.ekg.dto.requests.ChangePasswordDTO;
import com.hospital.ekg.dto.requests.LoginDTO;
import com.hospital.ekg.dto.requests.SignUpDto;
import com.hospital.ekg.dto.responses.UserDTO;
import com.hospital.ekg.model.DoctorEntity;
import com.hospital.ekg.model.RolesEntity;
import com.hospital.ekg.repository.DoctorRepository;
import com.hospital.ekg.repository.RoleRepository;
import org.springframework.security.core.Authentication;

@RestController
@RequestMapping(path = "/api")
public class AuthController {
    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private DoctorRepository doctorRepository;

    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @PostMapping("/login")
    public ResponseEntity<UserDTO> postMethodName(@RequestBody LoginDTO loginDTO) {
        Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(loginDTO.getUsername(), loginDTO.getPassword()));
        SecurityContextHolder.getContext().setAuthentication(authentication);

        DoctorEntity doctor = doctorRepository.findByUsername(loginDTO.getUsername());
        UserDTO dtoAnswer = new UserDTO();
        dtoAnswer.setFirstName(doctor.getFirstName());
        dtoAnswer.setLastName(doctor.getLastName());
        dtoAnswer.setRoleName(doctor.getRoles().iterator().next().getName());
        dtoAnswer.setUserName(doctor.getUsername());
        return new ResponseEntity<>(dtoAnswer, HttpStatus.OK);
    }

    @PostMapping("/change_password")
    public ResponseEntity<UserDTO> changePassword(@RequestBody ChangePasswordDTO loginDTO) {
        Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(loginDTO.getUsername(), loginDTO.getOldPassword()));
        SecurityContextHolder.getContext().setAuthentication(authentication);

        DoctorEntity doctorEntity = doctorRepository.findByUsername(loginDTO.getUsername());
        UserDTO dtoAnswer = new UserDTO();
        dtoAnswer.setFirstName(doctorEntity.getFirstName());
        dtoAnswer.setLastName(doctorEntity.getLastName());
        dtoAnswer.setRoleName(doctorEntity.getRoles().iterator().next().getName());
        dtoAnswer.setUserName(doctorEntity.getUsername());
        doctorEntity.setPassword(passwordEncoder.encode(loginDTO.getNewPassword()));
        doctorRepository.save(doctorEntity);
        return new ResponseEntity<>(dtoAnswer, HttpStatus.OK);
    }
    
    @PostMapping("/signup")
    public ResponseEntity<?> registerManager(@RequestBody SignUpDto signUpDto) {
        
        DoctorEntity doctorEntity = doctorRepository.findByUsername(signUpDto.getUserName());
        if(doctorEntity == null){
            DoctorEntity doctorEntityToCreate = new DoctorEntity();
            doctorEntityToCreate.setFirstName(signUpDto.getFirstName());
            doctorEntityToCreate.setLastName(signUpDto.getLastName());
            doctorEntityToCreate.setUsername(signUpDto.getUserName());
            doctorEntityToCreate.setPassword(passwordEncoder.encode(signUpDto.getPassword()));
            Set<RolesEntity> roles = roleRepository.findByName(signUpDto.getRoleName());
            doctorEntityToCreate.setRoles(roles);
            doctorRepository.save(doctorEntityToCreate);
            return new ResponseEntity<>("User is registered successfully!", HttpStatus.OK);

        }
        else{
            return new ResponseEntity<>("Username already exists", HttpStatus.BAD_REQUEST);
        }
        
    }
    
}
