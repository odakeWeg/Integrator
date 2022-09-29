package com.edson.controller;

import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.ResourceBundle;

import com.edson.App;
import com.edson.model.dto.TestRoutine;
import com.edson.tag.BaseTag;
import com.edson.tag.TestTag;
import com.edson.util.ViewConfigurationPathUtil;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextInputDialog;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.cell.PropertyValueFactory;

public class SettingsController implements Initializable {

    @FXML
    private TableView<TestRoutine> testTable;
    @FXML
    private TableColumn<TestRoutine, Integer> idCol;
    @FXML
    private TableColumn<TestRoutine, Integer> criadorCol;
    @FXML
    private TableColumn<TestRoutine, String> produtoCol;
    @FXML
    private TableColumn<TestRoutine, Integer> etapasCol;
    @FXML
    private TableColumn<TestRoutine, String> dataCol;

    private TableColumn<TestRoutine, List<BaseTag>> tagList;

    @Override
    public void initialize(URL arg0, ResourceBundle arg1) {
        idCol.setCellValueFactory(new PropertyValueFactory<>("id"));
        criadorCol.setCellValueFactory(new PropertyValueFactory<>("criador"));
        produtoCol.setCellValueFactory(new PropertyValueFactory<>("produto"));
        etapasCol.setCellValueFactory(new PropertyValueFactory<>("etapas"));
        dataCol.setCellValueFactory(new PropertyValueFactory<>("data"));

        testTable.setItems(nameList());

        for (int i = 0; i < testTable.getItems().size(); i++ ) {
            //testTable.getItems().get(i).tagList;
        }
        
    }

    //@TestingCode
    private ObservableList<TestRoutine> nameList() {
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("uuuu/MM/dd");
        LocalDate localDate = LocalDate.now();
        String dataNow  = dtf.format(localDate);
        return FXCollections.observableArrayList(
            new TestRoutine(0, 7881, "SSW900", 8, dataNow, null),
            new TestRoutine(1, 7881, "SSW07", 6, dataNow, null),
            new TestRoutine(2, 7881, "SSW08", 6, dataNow, null),
            new TestRoutine(3, 7881, "SSW900", 5, dataNow, null)
        );
    }
    
    @FXML
    private void addTest() throws IOException {
        String name = requestName();
        if(name.equals("")) {
            Alert userAlert = new Alert(AlertType.ERROR);
			userAlert.setTitle("Validação de entrada");
			userAlert.setHeaderText("Nenhum nome inserido");
			userAlert.setContentText("Favor inserir valor para adição de teste novo");
			userAlert.showAndWait();
        } else {
            List<BaseTag> tagList = new ArrayList<>();
            DateTimeFormatter dtf = DateTimeFormatter.ofPattern("uuuu/MM/dd");
            LocalDate localDate = LocalDate.now();
            String dataNow  = dtf.format(localDate);
            TestRoutine testRoutine = new TestRoutine(testTable.getItems().size(), 22, name, 22, dataNow, tagList);
            testTable.getItems().add(testRoutine);
        }
    }

    private String requestName() {
        TextInputDialog nameRequest = new TextInputDialog();
		nameRequest.setTitle("Test Name");
		nameRequest.setHeaderText("Digite nome do produto:");
		nameRequest.setContentText("Produto:");
		Optional<String> name = nameRequest.showAndWait();
        if(name.isPresent()){
            return name.get();
        } else {
            return "";
        }
        
    }

    @FXML
    private void switchToEditForm() throws IOException {
        App.setRoot(ViewConfigurationPathUtil.VIEW_PATH + "mainScreen");
    }

    @FXML
    private void removeSelectedItem() throws IOException {
        ObservableList<TestRoutine> testRoutineList =  testTable.getItems();
        int removedId = testTable.getSelectionModel().getSelectedItem().getId();
        testRoutineList.size();
        for (int i =0; i < testRoutineList.size(); i++) {
            if(removedId < testRoutineList.get(i).getId()) {
                testRoutineList.get(i).setId(testRoutineList.get(i).getId()-1);
            }
        }
        testTable.getItems().removeAll(testTable.getSelectionModel().getSelectedItems());
    }

    @FXML
    private void switchToPreviousPage() throws IOException {
        App.setRoot(ViewConfigurationPathUtil.VIEW_PATH + "mainScreen");
    }

}
