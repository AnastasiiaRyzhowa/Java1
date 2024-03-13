package org.ekg.front.controllers;

import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.PasswordField;
import org.ekg.front.MainApplication;
import org.ekg.front.dto.requests.ChangePasswordDTO;
import org.ekg.front.utils.Requests;
import org.ekg.front.utils.URLGenerator;

import java.io.IOException;

public class ChangePass {

    @FXML
    private PasswordField oldPassword;
    @FXML
    private PasswordField newPassword;
    @FXML
    private PasswordField newPassword2;

    @FXML
    private void continueClick() throws IOException, InterruptedException {
        if(!newPassword.getText().equals(newPassword2.getText())){
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Ошибка");
            alert.setHeaderText("Пароли не совпадают");
            alert.showAndWait();
            return;
        }
        ChangePasswordDTO changePasswordDTO = new ChangePasswordDTO();
        changePasswordDTO.setOldPassword(oldPassword.getText());
        changePasswordDTO.setNewPassword(newPassword.getText());
        Requests.changePasswordRequest(URLGenerator.getChangePasswordURL(), changePasswordDTO);
        MainApplication.setRoot("mainWindow");
    }

    @FXML
    private void cancelClick() throws IOException {
        MainApplication.setRoot("mainWindow");
    }
}
