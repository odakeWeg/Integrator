package com.edson.controller;

import java.io.IOException;

import com.edson.App;
import com.edson.model.dto.UserDTO;
import com.edson.persistence.model.User;
import com.edson.util.ViewConfigurationPathUtil;

import javafx.fxml.FXML;
import javafx.scene.control.TextField;

public class LoginController {
    
    @FXML
    private TextField userCadastro;
    @FXML
    private TextField userPassword;

    @FXML   
    private void login() throws IOException {
        UserDTO loginUser = new UserDTO(userCadastro.getText(), userPassword.getText());
        if(loginUser.getUserRepository().existsById(loginUser.getCadastro())) {
            System.out.println("ValueFound");
        } else {
            System.out.println("nullValueFound");
        }
        User authenticUser = loginUser.getUserRepository().findById(loginUser.getCadastro()).get();
        if(loginUser.isUserAuthenticated(authenticUser)) {
            //TODO Redirect
            App.setRoot(ViewConfigurationPathUtil.VIEW_PATH + "mainScreen");
            System.out.println("Autentificado");
        } else {
            //TODO put message error bellow
            System.out.println("Não autenficicado");
        }
    }
}
