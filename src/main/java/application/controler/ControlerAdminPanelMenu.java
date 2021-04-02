package application.controler;

import application.exception.AlreadyDisconnectException;
import application.exception.NotConnectException;
import application.model.UtilisateurSession;
import application.view.EditElement;
import application.view.FxmlView;
import application.view.StageManager;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.image.ImageView;
import javafx.scene.text.Text;
import org.springframework.stereotype.Controller;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

@Controller
public class ControlerAdminPanelMenu implements Initializable {

    private UtilisateurSession utilisateurSession = UtilisateurSession.getInstance();

    private ArrayList<Button> aButton;

    private StageManager stageManager = StageManager.getInstance();

    private EditElement editElement = EditElement.getInstance();

    @FXML
     private Button userButton;

     @FXML
     private ImageView genderImage;

     @FXML
     private Button adminPanelButton;

     @FXML
     private Button disconnectButton;

     @FXML
     private Button homeButton;

     @FXML
     private Text title;

     @FXML
     private Button usersPanelButton;

     @FXML
     private Button transactionsPanelButton;

     @FXML
     private Button itemsPanelButton;

     @FXML
     private Button clientsPanelButton;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        this.aButton = new ArrayList<>();
        this.aButton.add(this.usersPanelButton);
        this.aButton.add(this.clientsPanelButton);
        this.aButton.add(this.itemsPanelButton);
        this.aButton.add(this.transactionsPanelButton);
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
     void disconnect(ActionEvent event) {
         try{
             this.editElement.disconnect();
         }catch (AlreadyDisconnectException e){
             this.editElement.forceDisconnection();
         }
     }

     @FXML
     void displayAdminPanelMenu(ActionEvent event) {
         this.stageManager.switchScene(event, FxmlView.ADMIN_PANEL_MENU);
     }

     @FXML
     void displayClientsPanelMenu(ActionEvent event) {

     }

     @FXML
     void displayItemsPanelMenu(ActionEvent event) {

     }

     @FXML
     void displayMainMenu(ActionEvent event) {
         this.stageManager.switchScene(event, FxmlView.MAIN_MENU);
     }

     @FXML
     void displayTransactionsPanelMenu(ActionEvent event) {

     }

     @FXML
     void displayUsersPanelMenu(ActionEvent event) {
         this.stageManager.switchScene(event, FxmlView.USERS_LIST_MENU);
     }
}
