package com.hospital.ekg.config;

import com.hospital.ekg.model.*;
import com.hospital.ekg.repository.DoctorRepository;
import com.hospital.ekg.repository.PatientRepository;
import com.hospital.ekg.repository.SurveyRepository;
import com.hospital.ekg.service.AuthService;
import com.hospital.ekg.service.PatientService;
import com.hospital.ekg.service.RoleService;
import com.hospital.ekg.service.SurveyService;
import com.hospital.ekg.utils.loadDB;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.io.IOException;
import java.util.List;

@Configuration
public class dbLoad {
    /**
     * Статическая константа конфигурационного класса для логгирования
     */
    private static final Logger log = LoggerFactory.getLogger(dbLoad.class);

    /**
     * Бин метод инициализации
     * @param rs - RoleService
     * @param us - UserService
     * @param ps - PointService
     * @return CommandLineRunner - Объект обработки консоли
     * @throws IOException
     */
    @Bean
    CommandLineRunner init(SurveyService surveyService, PatientService patientService, RoleService rs, DoctorRepository dr, PatientRepository pr, SurveyRepository sr, AuthService us) throws IOException {

        // Инициализация всех объектов

        List<RolesEntity> roles = loadDB.createUserRoles(rs);
        List<DoctorEntity> users = loadDB.createUsersAndRoles(rs);
        DoctorEntity doctor = users.get(0);
        List<PatientEntity> patientEntities = loadDB.createPatients(dr, doctor);
        List<SurveyEntity> surveyEntities = loadDB.loadSurveys(pr, patientEntities);
        List<EKGEntity> ekgEntities = loadDB.loadEKG(sr, surveyEntities);


        return args -> {

            log.info("Start Load Roles");
            for(RolesEntity role: roles){
                log.info("Preloading Roles: "+rs.createNewRole(role));
            }
            log.info("End Load Roles");

            log.info("Start Load Users");
            for(DoctorEntity user: users){
                log.info("Preloading USERS: "+us.loadUserDb(user));
            }
            log.info("End Load Users");

            log.info("Start Load Patients");
            for(PatientEntity patient: patientEntities){
                log.info("Preloading Patients: "+patientService.createPatient(patient));
            }
            log.info("End Load Patients");

            log.info("Start Load Surveys");
            for(SurveyEntity survey: surveyEntities){
                log.info("Preloading Surveys: "+surveyService.createSurvey(survey));
            }
            log.info("End Load Surveys");

            log.info("Start Load EKGS");
            for(EKGEntity ekg: ekgEntities){
                surveyService.createEKG(ekg);
            }
            log.info("End Load EKGS");
        };

    }
}
