package application.controller;

import application.entity.User;
import application.tool.SceneManager;
import application.tool.StageManager;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.text.Text;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class ControlerMainMenu implements Initializable{

    private StageManager sm = StageManager.getInstance();

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
        User u = (User) sm.getStage().getUserData();
        this.fullNameText.setText(u.getName() + " " + u.getFirstName());
    }

    @FXML
    public void displayAddTransactionMenu(ActionEvent event){
        try{
            SceneManager.switchScene(event, "addTransactionMenu.fxml");
        }catch (IOException e){

        }
    }

}
