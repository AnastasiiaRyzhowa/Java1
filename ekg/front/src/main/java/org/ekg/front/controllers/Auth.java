package org.ekg.front.controllers;

import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import org.ekg.front.MainApplication;
import org.ekg.front.dto.requests.LoginDTO;
import org.ekg.front.utils.Requests;
import org.ekg.front.utils.URLGenerator;

import java.io.IOException;

public class Auth {
    @FXML
    private TextField loginField;

    @FXML
    private PasswordField passwordField;

    @FXML
    private void authClick() throws IOException, InterruptedException {
        if(loginField.getText().isBlank() || passwordField.getText().isBlank()){
            String text = loginField.getText().isBlank() ? "Логин" : " Пароль";

            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Ошибка");
            alert.setHeaderText("Не все поля заполнены");
            alert.setContentText("Вы не заполнили поле '" + text + "'");
            alert.showAndWait();
            return;
        }

        LoginDTO loginRequest = new LoginDTO();
        loginRequest.setUsername(loginField.getText());
        loginRequest.setPassword(passwordField.getText());
        boolean res = Requests.authRequest(URLGenerator.getAuthURL(),loginRequest);
        if(res){
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Авторизация прошла успешно");
            alert.setHeaderText("Вы авторизованы");
            alert.showAndWait();
            MainApplication.setRoot("mainWindow");
        }
        else {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Ошибка авторизации");
            alert.setHeaderText("Неверный логин или пароль");
            alert.showAndWait();
        }

    }
}
