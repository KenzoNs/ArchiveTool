package application.controller;

import application.entity.Utilisateur;
import application.model.UtilisateurSession;
import application.service.UtilisateurService;
import application.view.FxmlView;
import application.view.StageManager;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.text.Text;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

@Controller
public class ControlerMainMenu implements Initializable{

    private StageManager sm = StageManager.getInstance();

    private UtilisateurSession us = UtilisateurSession.getInstance();

    @Autowired
    private UtilisateurService utilisateurService;

    @FXML
    private Button home;

    @FXML
    private Button fullNameText;

    @FXML
    private Button addTransaction;

    @FXML
    private Button addClient;

    @FXML
    private Button addItem;

    @FXML
    private Button disconnectButton;

    @FXML
    private Button searchButton;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        if (us == null){

        }
        this.fullNameText.setText(us.getUtilisateur().getNom_utilisateur() + " " + us.getUtilisateur().getPrenom_utilisateur());
    }


    @FXML
    public void displayMainMenu(ActionEvent event) throws IOException {
        this.sm.switchScene(event, FxmlView.MAIN_MENU);
    }


    @FXML
    public void displayAddTransactionMenu(ActionEvent event) throws IOException {
        this.sm.switchScene(event, FxmlView.ADD_TRANSACTION_MENU);
    }

    @FXML
    public void displayAddClientMenu(ActionEvent event) throws IOException {
        this.sm.switchScene(event, FxmlView.ADD_CLIENT_MENU);
    }

    @FXML
    public void displayAddItemMenu(ActionEvent event) throws IOException {
        this.sm.switchScene(event, FxmlView.ADD_ITEM_MENU);
    }

    @FXML
    public void disconnect(ActionEvent event){
        System.out.println("deco");
    }


}
