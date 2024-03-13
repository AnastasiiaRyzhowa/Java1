package org.ekg.front.utils;

import org.ekg.front.MainApplication;
import org.ekg.front.controllers.Main;

public class URLGenerator {
    private static final String BASE_URL = "http://localhost:8080/api";

    public static String getAuthURL(){
        return BASE_URL + "/login";
    }

    public static String getSignUpURL(){
        return BASE_URL + "/signup";
    }
    public static String getChangePasswordURL(){
        return BASE_URL + "/change_password";
    }

    public static String getAllPatients(){
        return BASE_URL + "/" + MainApplication.getContextHolder().getUser().getUserName() + "/ekg";
    }

    public static String getAllPatientSurveysURL(){
        return getAllPatients() + "/" + MainApplication.getContextHolder().getCurrentPatientId() + "/surveys";
    }

    public static String getEKGBySurveyURL(){
        return getAllPatientSurveysURL() + "/" + MainApplication.getContextHolder().getCurrentSurveyId();
    }

}
