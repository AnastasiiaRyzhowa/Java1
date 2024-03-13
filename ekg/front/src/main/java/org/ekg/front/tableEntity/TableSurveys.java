package org.ekg.front.tableEntity;

public class TableSurveys {

    private int id;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    private String dateTime;

    public TableSurveys() {
    }

    public String getDateTime() {
        return dateTime;
    }

    public void setDateTime(String dateTime) {
        this.dateTime = dateTime;
    }

    public TableSurveys(String dateTime) {
        this.dateTime = dateTime;
    }
}
