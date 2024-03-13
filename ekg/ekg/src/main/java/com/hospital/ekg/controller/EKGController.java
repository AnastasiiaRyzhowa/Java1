package com.hospital.ekg.controller;

import com.hospital.ekg.dto.responses.EkgDTO;
import com.hospital.ekg.dto.responses.PatientDTO;
import com.hospital.ekg.dto.responses.SurveyDTO;
import com.hospital.ekg.model.*;
import com.hospital.ekg.repository.DoctorRepository;
import com.hospital.ekg.repository.EGKRepository;
import com.hospital.ekg.repository.PatientRepository;
import com.hospital.ekg.repository.SurveyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api/{username}/ekg")
public class EKGController {

    @Autowired
    private DoctorRepository doctorRepository;

    @Autowired
    private PatientRepository patientRepository;

    @Autowired
    private SurveyRepository surveyRepository;

    @Autowired
    private EGKRepository ekgRepo;

    @GetMapping
    public ResponseEntity<List<PatientDTO>> getAllPatients(@PathVariable String username){
        DoctorEntity doctorEntity = doctorRepository.findByUsername(username);
        List<PatientEntity> pe = patientRepository.findAllByDoctor(doctorEntity);
        List<PatientDTO> patients = new ArrayList<>();
        for(PatientEntity patient: pe){
            PatientDTO temp = new PatientDTO();
            temp.setId(patient.getId());
            temp.setFirstName(patient.getFirstName());
            temp.setLastName(patient.getLastName());
            temp.setMiddleName(patient.getMiddleName());
            temp.setBirthDate(patient.getDateOfBirth().toString());
            patients.add(temp);
        }
        return new ResponseEntity<>(patients, HttpStatus.OK);

    }

    @GetMapping("/{patientId}/surveys")
    public ResponseEntity<List<SurveyDTO>> getAllPatientSurveys(@PathVariable String username, @PathVariable int patientId){
        PatientEntity patientEntity = patientRepository.findById(patientId);
        List<SurveyEntity> surveyEntities = surveyRepository.findAllByPatient(patientEntity);
        List<SurveyDTO> surveyDTOS = new ArrayList<>();
        for(SurveyEntity surveyEntity: surveyEntities){
            SurveyDTO temp = new SurveyDTO();
            temp.setId(surveyEntity.getId());
            temp.setSurveyDate(surveyEntity.getDateSurvey().toString());
            surveyDTOS.add(temp);

        }
        return new ResponseEntity<>(surveyDTOS, HttpStatus.OK);
    }

    @GetMapping("/{patientId}/surveys/{surveyId}")
    public ResponseEntity<List<EkgDTO>> getEKGByPatientSurvey(@PathVariable String username, @PathVariable int patientId, @PathVariable int surveyId){
        SurveyEntity survey = surveyRepository.findSurveyEntityById(surveyId);
        List<EKGEntity> ekgResults = ekgRepo.findAllBySurvey(survey);
        List<EkgDTO> ekgs = new ArrayList<>();

        for(EKGEntity entity: ekgResults){
            EkgDTO ekg = new EkgDTO();
            ekg.setId(entity.getId());
            ekg.setValue(entity.getVal());
            ekg.setTick(entity.getNumber());
            ekg.setType(entity.getType());
//            if(entity.getEkgType()== EKGTypesEnum.AVF){
//                ekg.setType("AVF");
//            }else if(entity.getEkgType()== EKGTypesEnum.AVL){
//                ekg.setType("AVL");
//            }else{
//                ekg.setType("AVR");
//            }
            ekgs.add(ekg);
        }
        return new ResponseEntity<>(ekgs, HttpStatus.OK);
    }
}
