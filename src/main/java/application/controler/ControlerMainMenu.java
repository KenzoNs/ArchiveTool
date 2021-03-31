package application.controler;

import application.exception.AlreadyDisconnectException;
import application.model.UtilisateurSession;
import application.service.UtilisateurService;
import application.tool.Utils;
import application.view.FxmlView;
import application.view.StageManager;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
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
    private HBox hbox;

    @FXML
    private Button home;

    @FXML
    private Text fullNameText;

    @FXML
    private ImageView genre;

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

    @FXML
    private Button adminPanelButton;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        if (Utils.isConnected()){
            Utils.updateButton(adminPanelButton);
            Utils.updateButton(addClient);
            Utils.updateButton(addItem);
            Utils.updateButton(addTransaction);
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
    public void disconnect(ActionEvent event) {
        try{
            Utils.disconnect();
        }catch (AlreadyDisconnectException e){
            e.getCause();
        }
    }
}
