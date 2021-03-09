package application.controller;

import application.entity.User;
import application.model.UserSession;
import application.service.UserService;
import application.tool.StageManager;
import application.tool.SceneManager;
import javafx.animation.TranslateTransition;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.util.Duration;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

@Controller
public class ControlerLoginMenu implements Initializable{

    StageManager sm = StageManager.getInstance();

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
        String login = this.loginField.getText();
        String password = this.passwordField.getText();

        if(!login.equals("") && !password.equals("")){
            System.out.println(userService);
            User user = userService.authentificed(login, password);
            if(user != null && user.getUserLogin().equals(login) && user.getPassword().equals(password)) {
                try {
                    UserSession.getInstance().setUser(user);
                    this.sm.switchStage(new Stage());
                    this.sm.getStage().setScene(SceneManager.createScene("mainMenu.fxml"));
                    this.sm.configStage("Main menu", true, true, true);
                } catch (IOException e) {
                }
            }
            else{
                this.passwordField.clear();
                connexionError.setVisible(true);
            }
        }
        else{
            if(login.equals("")){
                this.loginField.setStyle("-fx-border-color: -red-color");
            }
            if(password.equals("")){
                this.passwordField.setStyle("-fx-border-color: -red-color");
            }
        }
    }
}
