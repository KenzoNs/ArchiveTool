package application.controler;

import application.entity.Client;
import application.exception.AlreadyDisconnectException;
import application.exception.NotConnectException;
import application.exception.PhoneNumberException;
import application.exception.ToShortException;
import application.model.UtilisateurSession;
import application.service.ClientService;
import application.tool.Utils;
import application.view.EditElement;
import application.view.ErrorMessages;
import application.view.FxmlView;
import application.view.StageManager;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.text.Text;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

@Controller
public class ControlerAddClientMenu implements Initializable {

    private UtilisateurSession utilisateurSession = UtilisateurSession.getInstance();

    private ArrayList<Button> aButton;

    private StageManager stageManager = StageManager.getInstance();

    private EditElement editElement = EditElement.getInstance();

    @Autowired
    private ClientService clientService;

    @FXML
    private Text title;

    @FXML
    private Button homeButton;

    @FXML
    private Button cancel;

    @FXML
    private TextField clientAddr;

    @FXML
    private TextField clientName;

    @FXML
    private Button validateButton;

    @FXML
    private TextField clientPhoneNumber;

    @FXML
    private Button userButton;

    @FXML
    private ImageView genderImage;

    @FXML
    private Button disconnectButton;

    @FXML
    private Button adminPanelButton;

    @FXML
    private Text errorText;


    @Override
    public void initialize(URL location, ResourceBundle resources) {
        this.aButton = new ArrayList<>();
        this.aButton.add(this.homeButton);
        this.aButton.add(this.validateButton);
        this.aButton.add(this.adminPanelButton);

        this.editElement.updateTitle(this.title);

        if (this.editElement.isConnected()){
            try{
                if (this.editElement.hasPermission()){
                    this.editElement.updateProfilButton(this.userButton, this.genderImage);
                    this.editElement.displayButton(this.aButton);
                }
                else{
                    this.editElement.hideButton(this.aButton);
                    this.editElement.disconnect();
                }
            }catch(NotConnectException | AlreadyDisconnectException e){
                this.editElement.forceDisconnection();
            }
        }
        else{
            this.editElement.forceDisconnection();
        }
    }

    @FXML
    public void displayMainMenu(ActionEvent event){
        this.stageManager.switchScene(event, FxmlView.MAIN_MENU);
    }

    @FXML
    public void disconnect(ActionEvent event) {
        try{
            this.editElement.disconnect();
        }catch (AlreadyDisconnectException e){
            this.editElement.forceDisconnection();
        }
    }

    @FXML
    public void submit(ActionEvent event){
        if(this.clientName.getText().equals("") || this.clientAddr.getText().equals("") || this.clientPhoneNumber.getText().equals("")){
            Utils.displayErrorMessage(this.errorText, ErrorMessages.MISS_FIELD_ERROR);
        }
        else{
            try {
                String phoneNumber = this.clientPhoneNumber.getText();
                if (Utils.testPhoneNumberFormat(phoneNumber)) {
                    String addr = this.clientAddr.getText();
                    String name = this.clientName.getText();
                    try{
                        if (Utils.testSizeText(addr) || Utils.testSizeText(name)) {
                            Client c = new Client(name, phoneNumber, addr);
                            this.clientService.addOrUpdateClient(c);
                            this.stageManager.switchScene(event, FxmlView.MAIN_MENU);
                            try {
                                Utils.displayInfoWindows("Client crée avec succès");
                            } catch (IOException e) {
                                e.printStackTrace();
                            }
                        }
                    }catch(ToShortException e){
                        Utils.displayErrorMessage(this.errorText, ErrorMessages.TO_SHORT);
                    }
                }
            }catch (PhoneNumberException e) {
                Utils.displayErrorMessage(this.errorText, ErrorMessages.INCORRECT_PHONE_NUMBER_FORMAT_TYPE);
            }

        }

    }
}


