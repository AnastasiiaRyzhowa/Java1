package org.ekg.front.dto.responses;

import java.time.LocalDateTime;

public class SurveyDTO {

    private int id;
    private String surveyDate;

    public SurveyDTO() {
    }

    public SurveyDTO(int id, String surveyDate) {
        this.id = id;
        this.surveyDate = surveyDate;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getSurveyDate() {
        return surveyDate;
    }

    public void setSurveyDate(String surveyDate) {
        this.surveyDate = surveyDate;
    }
}
