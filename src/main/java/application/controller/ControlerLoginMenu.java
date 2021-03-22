package application.controller;

import application.entity.User;
import application.entity.Utilisateur;
import application.service.UserService;
import application.service.UtilisateurService;
import application.tool.StageManager;
import application.tool.SceneManager;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import net.bytebuddy.implementation.bind.MethodDelegationBinder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;


import java.io.IOException;
import java.net.URL;
import java.security.MessageDigest;
import java.util.ResourceBundle;

@Controller
public class ControlerLoginMenu implements Initializable{

    StageManager sm = StageManager.getInstance();

    @Autowired
    private UtilisateurService utilisateurService;

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
            System.out.println(utilisateurService);
            password = getSha256(password);
            System.out.println("password hashé = " + password);
            Utilisateur utilisateur = utilisateurService.authentificed(login, password);
            System.out.println("user = " + utilisateur);
            if(utilisateur != null) {
                try {
                    sm.getStage().setUserData(utilisateur);
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

    public static String getSha256(String value) {
        try{
            MessageDigest md = MessageDigest.getInstance("SHA-256");
            md.update(value.getBytes());
            return bytesToHex(md.digest());
        } catch(Exception ex){
            throw new RuntimeException(ex);
        }
    }
    private static String bytesToHex(byte[] bytes) {
        StringBuffer result = new StringBuffer();
        for (byte b : bytes) result.append(Integer.toString((b & 0xff) + 0x100, 16).substring(1));
        return result.toString();
    }
}
