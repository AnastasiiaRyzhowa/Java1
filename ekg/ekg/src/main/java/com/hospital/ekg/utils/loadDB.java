package com.hospital.ekg.utils;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.hospital.ekg.model.*;
import com.hospital.ekg.repository.DoctorRepository;
import com.hospital.ekg.repository.PatientRepository;
import com.hospital.ekg.repository.SurveyRepository;
import com.hospital.ekg.service.RoleService;

import java.io.File;
import java.io.IOException;
import java.time.LocalDate;
import java.time.Month;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.concurrent.ThreadLocalRandom;

public class loadDB {

    public static LocalDate between(LocalDate startInclusive, LocalDate endExclusive) {
        long startEpochDay = startInclusive.toEpochDay();
        long endEpochDay = endExclusive.toEpochDay();
        long randomDay = ThreadLocalRandom
                .current()
                .nextLong(startEpochDay, endEpochDay);

        return LocalDate.ofEpochDay(randomDay);
    }

    public static List<RolesEntity> createUserRoles(RoleService rs){
        List<RolesEntity> roles = new ArrayList<>();
        RolesEntity r1 = new RolesEntity();
        r1.setName("ADMIN");
        RolesEntity r2 = new RolesEntity();
        r2.setName("USER");

        roles.add(rs.createNewRole(r1));
        roles.add(rs.createNewRole(r2));


        return roles;
    }
    public static List<DoctorEntity> createUsersAndRoles(RoleService rs){
        DoctorEntity userAdmin = new DoctorEntity();
        userAdmin.setUsername("admin");
        userAdmin.setFirstName("admin");
        userAdmin.setLastName("admin");
        userAdmin.setRoles(rs.loadRolesByName("ADMIN"));
        userAdmin.setPassword("admin");

        List<DoctorEntity> users = new ArrayList<>();
        users.add(userAdmin);
        return users;
    }
    public static List<PatientEntity> createPatients(DoctorRepository dr, DoctorEntity doctor){
        System.out.println(doctor);
        assert doctor!=null;
        List<PatientEntity> patients = new ArrayList<>();
        for(String patientName: new String[]{"Иванов Иван Иванович", "Петров Петр Петрович", "Галямов Сергей Константинович"}){
            PatientEntity patientEntity = new PatientEntity();
            String[] pp = patientName.split(" ");
            patientEntity.setFirstName(pp[1]);
            patientEntity.setMiddleName(pp[2]);
            patientEntity.setLastName(pp[0]);
            patientEntity.setDateOfBirth(between(LocalDate.of(1949, Month.OCTOBER, 14), LocalDate.of(1999, Month.OCTOBER, 14)));
            patientEntity.setDoctor(doctor);
            patients.add(patientEntity);
        }



        return patients;
    }

    public static List<SurveyEntity> loadSurveys(PatientRepository patientRepository, List<PatientEntity> patientEntities){
        List<SurveyEntity> surveyEntities = new ArrayList<>();
        for(PatientEntity patient: patientEntities){
            for (int i = 0; i < 4; i++) {
                SurveyEntity temp = new SurveyEntity();
                temp.setDateSurvey(between(LocalDate.of(2022, Month.OCTOBER, 14), LocalDate.now()));
                temp.setPatient(patient);
                surveyEntities.add(temp);
            }
        }
        return surveyEntities;


    }

    public static List<EKGEntity> loadEKG(SurveyRepository sr, List<SurveyEntity> surveyEntities) throws IOException {
        List<EKGEntity> enitites = new ArrayList<>();
        for (int i = 1; i <= surveyEntities.size(); i++) {
            ObjectMapper om =new ObjectMapper();
            File file = new File(Objects.requireNonNull(loadDB.class.getClassLoader().getResource(i+".json")).getFile());
            List<List<EKGJson>> ekgs  =om.readValue(file, new TypeReference<>(){});
            for (int j = 0; j < ekgs.size(); j++) {
                for (EKGJson ekgJson: ekgs.get(j)){
                    EKGEntity temp = new EKGEntity();
                    temp.setNumber(ekgJson.getTick());
                    temp.setVal(ekgJson.getValue());
                    temp.setSurvey(surveyEntities.get(i-1));
                    switch (ekgJson.getType()){
                        case 0:
                            temp.setType("AVF");
                            break;
                        case 1:
                            temp.setType("AVL");
                            break;
                        case 2:
                            temp.setType("AVR");
                            break;
                    }
                    enitites.add(temp);
                }

            }
        }
        return enitites;

    }
}
