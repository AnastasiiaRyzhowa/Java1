package org.ekg.front.utils;

import org.ekg.front.dto.responses.UserDTO;

public class ContextHolder {
    private UserDTO user;

    private int currentPatientId;
    private int currentSurveyId;

    public ContextHolder(UserDTO user, int currentPatientId, int currentSurveyId) {
        this.user = user;
        this.currentPatientId = currentPatientId;
        this.currentSurveyId = currentSurveyId;
    }

    public ContextHolder() {
    }

    public UserDTO getUser() {
        return user;
    }

    public void setUser(UserDTO user) {
        this.user = user;
    }

    public int getCurrentPatientId() {
        return currentPatientId;
    }

    public void setCurrentPatientId(int currentPatientId) {
        this.currentPatientId = currentPatientId;
    }

    public int getCurrentSurveyId() {
        return currentSurveyId;
    }

    public void setCurrentSurveyId(int currentSurveyId) {
        this.currentSurveyId = currentSurveyId;
    }
}
