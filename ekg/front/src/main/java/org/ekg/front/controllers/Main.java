package org.ekg.front.controllers;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import org.ekg.front.MainApplication;
import org.ekg.front.dto.responses.PatientDTO;
import org.ekg.front.dto.responses.SurveyDTO;
import org.ekg.front.dto.responses.UserDTO;
import org.ekg.front.tableEntity.TablePatients;
import org.ekg.front.tableEntity.TableSurveys;
import org.ekg.front.utils.Requests;
import org.ekg.front.utils.URLGenerator;

import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

public class Main implements Initializable {

    private ObservableList<TablePatients> patientsData;
    private ObservableList<TableSurveys> surveysData;

    @FXML
    private Label usernameLabel;

    @FXML
    private Label currentDate;

    //Таблица Пациенты
    @FXML
    private TableView<TablePatients> patientsTable;

    @FXML
    private TableColumn<TablePatients, String> lastNameColumn;

    @FXML
    private TableColumn<TablePatients, String> firstNameColumn;

    @FXML
    private TableColumn<TablePatients, String> middleNameColumn;
    @FXML
    private TableColumn<TablePatients, String> birthDate;

    //Таблица Пациенты
    @FXML
    private TableView<TableSurveys> surveysTable;

    @FXML
    private TableColumn<TableSurveys, String> surveyDateTimeColumn;


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        //Заполнить Label
        UserDTO user = MainApplication.getContextHolder().getUser();
        usernameLabel.setText(user.getLastName() + " " + user.getFirstName());
        currentDate.setText(LocalDate.now().toString());

        //Заполнить таблицу 1
        patientsData = FXCollections.observableArrayList();
        List<TablePatients> tablePatients = new ArrayList<>();
        try {
            List<PatientDTO> patientDTOS = Requests.getPatientsRequest(URLGenerator.getAllPatients());
            assert patientDTOS != null;
            for (PatientDTO patientDTO: patientDTOS){
                TablePatients temp = new TablePatients();
                temp.setBirthDate(patientDTO.getBirthDate());
                temp.setFirstName(patientDTO.getFirstName());
                temp.setLastName(patientDTO.getLastName());
                temp.setMiddleName(patientDTO.getMiddleName());
                temp.setId(patientDTO.getId());
                tablePatients.add(temp);
            }

            patientsData.setAll(tablePatients);
            lastNameColumn.setCellValueFactory(new PropertyValueFactory<>("lastName"));
            firstNameColumn.setCellValueFactory(new PropertyValueFactory<>("firstName"));
            middleNameColumn.setCellValueFactory(new PropertyValueFactory<>("middleName"));
            birthDate.setCellValueFactory(new PropertyValueFactory<>("birthDate"));

            patientsTable.setItems(patientsData);
            surveysData = FXCollections.observableArrayList();
            surveysTable.setItems(surveysData);
            patientsTable.getSelectionModel().selectedItemProperty().addListener((obs, oldSelection, newSelection) -> {
                // Заполнение таблицы 2 при смены выбранного пациента
                if (newSelection != null) {
                    MainApplication.getContextHolder().setCurrentPatientId(newSelection.getId());
                    surveysData = FXCollections.observableArrayList();
                    List<TableSurveys> tableSurveys = new ArrayList<>();
                    try {
                        List<SurveyDTO> surveyDTOS = Requests.getSurveysRequest(URLGenerator.getAllPatientSurveysURL());
                        assert surveyDTOS != null;
                        for(SurveyDTO surveyDTO: surveyDTOS){
                            TableSurveys temp2 = new TableSurveys();
                            temp2.setId(surveyDTO.getId());
                            temp2.setDateTime(surveyDTO.getSurveyDate());
                            tableSurveys.add(temp2);
                        }
                        surveysData.setAll(tableSurveys);
                        surveyDateTimeColumn.setCellValueFactory(new PropertyValueFactory<>("dateTime"));
                        surveysTable.setItems(surveysData);
                    } catch (IOException e) {
                        throw new RuntimeException(e);
                    } catch (InterruptedException e) {
                        throw new RuntimeException(e);
                    }
                }
            });

        } catch (IOException e) {
            throw new RuntimeException(e);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

        surveysTable.setRowFactory(tv -> {
            TableRow<TableSurveys> row = new TableRow<>();
            row.setOnMouseClicked(event -> {
                if (event.getClickCount() == 2 && (! row.isEmpty()) ) {
                    TableSurveys rowData = row.getItem();
                    MainApplication.getContextHolder().setCurrentSurveyId(surveysTable.getSelectionModel().getSelectedItem().getId());
                    try {
                        MainApplication.setRoot("ekg");
                    } catch (IOException e) {
                        throw new RuntimeException(e);
                    }
                }
            });
            return row ;
        });


    }

    @FXML
    private void changePasswordClick() throws IOException {
        MainApplication.setRoot("changePassword");
    }

    @FXML
    private void aboutClick(){
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Автор");
        alert.setHeaderText("Выполнила студентка ФИО");
        alert.setContentText("Группы ");
        alert.showAndWait();
    }
}
