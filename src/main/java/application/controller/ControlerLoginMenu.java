package application.controller;

import application.JavafxApplication;
import application.model.User;
import application.service.UserService;
import application.tool.StageManager;
import application.tool.SceneManager;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

@Controller
public class ControlerLoginMenu implements Initializable{

    @Autowired
    private UserService userService;

    @FXML
    private Text connexionError;

    @FXML
    private Button connexionButton;

    @FXML
    private Button quitButton;

    @FXML
    private TextField loginField;

    @FXML
    private PasswordField passwordField;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
    }

    @FXML
    public void quit(ActionEvent event){
        Platform.exit();
    }

    @FXML
    public void displayMainMenu(ActionEvent event){

        System.out.println(loginField.getText());
        System.out.println(passwordField.getText());


        boolean rp = userService.authentificed(loginField.getText(), passwordField.getText());

        if(rp){
            try{
                StageManager.switchStage(new Stage());
                StageManager.getStage().setScene(SceneManager.createScene("mainMenu.fxml"));
                StageManager.configStage("Main menu", true, true, true);
            }catch (IOException e){
            }
        }
        else{
            connexionError.setVisible(true);
        }
    }
}
