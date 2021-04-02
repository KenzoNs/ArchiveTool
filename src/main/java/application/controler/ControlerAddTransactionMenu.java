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
public class ControlerAddTransactionMenu implements Initializable {

    private UtilisateurSession utilisateurSession = UtilisateurSession.getInstance();

    private EditElement editElement = EditElement.getInstance();

    private ArrayList<Button> aButton;

    private StageManager stageManager = StageManager.getInstance();

    @FXML
    private Button homeButton;

    @FXML
    private Text title;

    @FXML
    private ImageView imageGender;

    @FXML
    private Button adminPanelButton;

    @FXML
    private Button disconnectButton;

    @FXML
    private Button validateButton;

    @FXML
    private Button userButton;

    @FXML
    private ImageView genderImage;

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
    public void displayMainMenu(ActionEvent event) throws IOException {
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

}
