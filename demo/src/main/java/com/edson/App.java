package com.edson;

import java.io.IOException;

import com.edson.model.dto.SessionDTO;
import com.edson.util.ApplicationSetup;
import com.edson.util.ViewConfigurationPathUtil;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.stage.Stage;

public class App extends Application {

    private static Scene scene;
    private SessionDTO sessionDTO;

    @Override
    public void init() {
        String[] args = getParameters().getRaw().toArray(new String[0]);
        ApplicationSetup.setApplicationContext(args);
    }

    @Override
    public void start(Stage stage) throws IOException {
        startingAppSetup();
        scene = new Scene(loadFXML(ViewConfigurationPathUtil.VIEW_PATH + "mainScreen"));
        stage.setScene(scene);
        setClosingEvent(stage);
        stage.show();
    }

    private void setClosingEvent(Stage stage) {
        stage.setOnCloseRequest(event ->{
            event.consume();
            closeRequest(stage);
        });
    }

    private void closeRequest(Stage stage){
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Fechar janela");
        alert.setContentText("Deseja finalizar a aplicação?");
        if(alert.showAndWait().get() == ButtonType.OK){
            closingAppSetup();
            System.exit(0);
        }
    }

    private void startingAppSetup() {
        ApplicationSetup.setSessionDTO(new SessionDTO());
    }

    private void closingAppSetup() {
        ApplicationSetup.getSessionDTO().save();
    }

    public static void setRoot(String fxml) throws IOException {
        scene.setRoot(loadFXML(fxml));
    }

    public static Parent loadFXML(String fxml) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource(fxml + ".fxml"));
        return fxmlLoader.load();
    }

}                                   