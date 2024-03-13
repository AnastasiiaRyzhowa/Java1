module org.firms.front {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.net.http;

    requires com.fasterxml.jackson.databind;
    requires com.fasterxml.jackson.core;
    requires com.fasterxml.jackson.annotation;
    requires com.fasterxml.jackson.datatype.jsr310;


    opens org.ekg.front to javafx.fxml;
    opens org.ekg.front.controllers to javafx.fxml;
    opens org.ekg.front.tableEntity to javafx.fxml;


    exports org.ekg.front.tableEntity;
    exports org.ekg.front;
    exports org.ekg.front.dto.responses;
    exports org.ekg.front.dto.requests;
    exports org.ekg.front.utils;
    exports org.ekg.front.controllers;
}