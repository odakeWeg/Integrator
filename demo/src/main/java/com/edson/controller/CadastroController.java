package com.edson.controller;

import java.io.IOException;

import com.edson.App;
import com.edson.model.dto.UserDTO;
import com.edson.util.ViewConfigurationPathUtil;

import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.TextField;

public class CadastroController {

    @FXML
    private TextField userCadastro;
    @FXML
    private TextField userPassword;
    @FXML
    private TextField perfil;
    
    @FXML
    private void cadastrar() throws IOException {
        UserDTO newUser = new UserDTO(userCadastro.getText(), userPassword.getText(), perfil.getText());
        newUser.save();
        savingMessage();
        App.setRoot(ViewConfigurationPathUtil.VIEW_PATH + "mainScreen");
    }

    private void savingMessage() {
        Alert userAlert = new Alert(AlertType.INFORMATION);
        userAlert.setTitle("Cadastro");
        userAlert.setHeaderText("Usuário salvo com sucesso");
        userAlert.setContentText("Pressione 'OK' para voltar a página inicial");
        userAlert.showAndWait();
    }

    @FXML
    private void switchToPreviousPage() throws IOException {
        App.setRoot(ViewConfigurationPathUtil.VIEW_PATH + "mainScreen");
    }
}
