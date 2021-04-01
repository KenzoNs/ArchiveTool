package application.controler;

import application.entity.Client;
import application.exception.AlreadyDisconnectException;
import application.exception.PhoneNumberException;
import application.model.UtilisateurSession;
import application.service.ClientService;
import application.tool.Utils;
import application.view.ErrorMessages;
import application.view.FxmlView;
import application.view.StageManager;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

@Controller
public class ControlerAddClientMenu implements Initializable {

    private UtilisateurSession us = UtilisateurSession.getInstance();

    private StageManager sm = StageManager.getInstance();

    @Autowired
    private ClientService clientService;

    @FXML
    private Button home;

    @FXML
    private Button cancel;

    @FXML
    private TextField clientAddr;

    @FXML
    private TextField clientName;

    @FXML
    private Button validate;

    @FXML
    private TextField clientPhoneNumber;


    @FXML
    private Text fullNameText;

    @FXML
    private Button disconnectButton;

    @FXML
    private Button adminPanelButton;

    @FXML
    private Text errorText;


    @Override
    public void initialize(URL location, ResourceBundle resources) {
        if (Utils.isConnected()) {
            Utils.updateButton(adminPanelButton);
            this.fullNameText.setText(us.getUtilisateur().getNom_utilisateur() + " " + us.getUtilisateur().getPrenom_utilisateur());
        }
    }

    @FXML
    public void displayMainMenu(ActionEvent event){
        try {
            this.sm.switchScene(event, FxmlView.MAIN_MENU);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    public void disconnect(ActionEvent event) {
        try{
            Utils.disconnect();
        }catch (AlreadyDisconnectException e){
            e.getCause();
        }
    }

    @FXML
    public void addClient(ActionEvent event){

        if(this.clientName.getText().equals("") || this.clientAddr.getText().equals("") || this.clientPhoneNumber.getText().equals("")){
            Utils.displayErrorMessage(this.errorText, ErrorMessages.MISS_FIELD_ERROR);
        }
        else{
            try {
                String phoneNumber = this.clientPhoneNumber.getText();
                if (Utils.testPhoneNumberFormat(phoneNumber)) {
                    String addr = this.clientAddr.getText();
                    String name = this.clientName.getText();
                    Client c = new Client(name, phoneNumber, addr);
                    this.clientService.addOrUpdateClient(c);
                    try {
                        Utils.displayInfoWindows("Produit crée avec succès");
                        this.sm.switchScene(event, FxmlView.MAIN_MENU);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }catch (PhoneNumberException e) {
                Utils.displayErrorMessage(this.errorText, ErrorMessages.INCORRECT_PHONE_NUMBER_FORMAT_TYPE);
            }

        }

    }
}


