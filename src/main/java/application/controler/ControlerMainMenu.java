package application.controler;

import application.exception.AlreadyDisconnectException;
import application.exception.NotConnectException;
import application.model.UtilisateurSession;
import application.service.UtilisateurService;
import application.tool.Utils;
import application.view.EditElement;
import application.view.FxmlView;
import application.view.StageManager;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.image.ImageView;
import javafx.scene.text.Text;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

@Controller
public class ControlerMainMenu implements Initializable{

    private StageManager stageManager = StageManager.getInstance();

    private ArrayList<Button> aButton;

    private UtilisateurSession utilisateurSession = UtilisateurSession.getInstance();

    private EditElement editElement = EditElement.getInstance();

    @Autowired
    private UtilisateurService utilisateurService;

    @FXML
    private Text title;

    @FXML
    private Button homeButton;

    @FXML
        private ImageView genderImage;

    @FXML
    private Button addTransaction;

    @FXML
    private Button adminPanelButton;

    @FXML
    private Button addClient;

    @FXML
    private Button addItem;

    @FXML
    private Button disconnectButton;

    @FXML
    private Button userButton;

    @FXML
    private Button searchButton;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        this.aButton = new ArrayList<>();
        this.aButton.add(this.addItem);
        this.aButton.add(this.addTransaction);
        this.aButton.add(this.adminPanelButton);
        this.aButton.add(this.addClient);

        this.editElement.updateTitle(this.title);

        if (this.editElement.isConnected()){
            try{
                this.editElement.updateProfilButton(this.userButton, this.genderImage);
                if(this.editElement.hasPermission()){
                    this.editElement.displayButton(this.aButton);
                }
                else{
                    this.editElement.hideButton(this.aButton);
                }
            }catch(NotConnectException e){
                this.editElement.forceDisconnection();
            }

        }
        else{
            this.editElement.forceDisconnection();
        }

    }

    @FXML
    public void displayMainMenu(ActionEvent event) {
        this.stageManager.switchScene(event, FxmlView.MAIN_MENU);
    }

    @FXML
    void displayAdminPanelMenu(ActionEvent event){
        this.stageManager.switchScene(event, FxmlView.ADMIN_PANEL_MENU);
    }

    @FXML
    public void displayAddTransactionMenu(ActionEvent event){
        this.stageManager.switchScene(event, FxmlView.ADD_TRANSACTION_MENU);
    }

    @FXML
    public void displayAddClientMenu(ActionEvent event){
        this.stageManager.switchScene(event, FxmlView.ADD_CLIENT_MENU);
    }

    @FXML
    public void displayAddItemMenu(ActionEvent event){
        this.stageManager.switchScene(event, FxmlView.ADD_ITEM_MENU);
    }

    @FXML
    public void disconnect(ActionEvent event){
        try{
            this.editElement.disconnect();
        }catch (AlreadyDisconnectException e){
            this.editElement.forceDisconnection();
        }
    }
}
