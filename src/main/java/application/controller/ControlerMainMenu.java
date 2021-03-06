package application.controller;

import application.tool.StageManager;
import application.tool.SceneManager;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class ControlerMainMenu implements Initializable{

    @FXML
    private Button addTransactionButton;

    @FXML
    private Button disconnectButton;

    @FXML
    private Button searchButton;

    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }

    @FXML
    public void displayAddTransactionMenu(ActionEvent event){
        try{
            SceneManager.switchScene(event, "addTransactionMenu.fxml");
        }catch (IOException e){

        }
    }





}
