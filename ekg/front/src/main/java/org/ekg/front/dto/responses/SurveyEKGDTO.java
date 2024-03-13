package org.ekg.front.dto.responses;

import java.util.List;

public class SurveyEKGDTO {

    private int surveyId;

    private List<SendEKGDTO> ekg1;
    private List<SendEKGDTO> ekg2;
    private List<SendEKGDTO> ekg3;

    public SurveyEKGDTO() {
    }

    public SurveyEKGDTO(int surveyId, List<SendEKGDTO> ekg1, List<SendEKGDTO> ekg2, List<SendEKGDTO> ekg3) {
        this.surveyId = surveyId;
        this.ekg1 = ekg1;
        this.ekg2 = ekg2;
        this.ekg3 = ekg3;
    }

    public int getSurveyId() {
        return surveyId;
    }

    public void setSurveyId(int surveyId) {
        this.surveyId = surveyId;
    }

    public List<SendEKGDTO> getEkg1() {
        return ekg1;
    }

    public void setEkg1(List<SendEKGDTO> ekg1) {
        this.ekg1 = ekg1;
    }

    public List<SendEKGDTO> getEkg2() {
        return ekg2;
    }

    public void setEkg2(List<SendEKGDTO> ekg2) {
        this.ekg2 = ekg2;
    }

    public List<SendEKGDTO> getEkg3() {
        return ekg3;
    }

    public void setEkg3(List<SendEKGDTO> ekg3) {
        this.ekg3 = ekg3;
    }
}
