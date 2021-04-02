package application.controler;

import application.entity.Client;
import application.entity.Utilisateur;
import application.model.UtilisateurSession;
import application.service.ClientService;
import application.service.ProduitService;
import application.service.UtilisateurService;
import application.tool.Utils;
import application.view.ErrorMessages;
import application.view.FxmlView;
import application.view.StageManager;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;

import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

@Controller
public class ControlerLoginMenu implements Initializable{

    private StageManager sm = StageManager.getInstance();

    @Autowired
    private UtilisateurService utilisateurService;

    private UtilisateurSession us = UtilisateurSession.getInstance();

    @FXML
    private Text textError;

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
    public void displayMainMenu(ActionEvent event) throws IOException {

        String login = this.loginField.getText();
        String password = this.passwordField.getText();

        if(!login.equals("") && !password.equals("")){
            password = Utils.getSha256(password);
            Utilisateur utilisateur = utilisateurService.authentificed(login, password);

            if(utilisateur != null) {
                us.setUtilisateur(utilisateur);
                this.sm.createStage(FxmlView.MAIN_MENU, true, true, true);
            }
            else{
                this.passwordField.clear();
                Utils.displayErrorMessage(textError, ErrorMessages.INCORRECT_CREDENTIALS);
            }
        }
        else{
            if(login.equals("") || password.equals("")){
                Utils.displayErrorMessage(textError, ErrorMessages.MISS_FIELD_ERROR);
            }
        }
    }


}
