package application.controller;

import application.model.UserSession;
import application.tool.StageManager;
import application.tool.SceneManager;
import application.tool.Utils;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.text.Text;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class ControlerMainMenu implements Initializable{

    private UserSession us = UserSession.getInstance();

    @FXML
    private Text fullNameText;

    @FXML
    private Button addTransactionButton;

    @FXML
    private Button disconnectButton;

    @FXML
    private Button searchButton;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        if (us == null){
            Utils.disconnect();
        }
        this.fullNameText.setText(us.getUser().getName() + " " + us.getUser().getFirstName());
    }

    @FXML
    public void displayAddTransactionMenu(ActionEvent event){
        try{
            SceneManager.switchScene(event, "addTransactionMenu.fxml");
        }catch (IOException e){

        }
    }

    @FXML
    public void disconnect(ActionEvent event){
        Utils.disconnect();
    }


}
