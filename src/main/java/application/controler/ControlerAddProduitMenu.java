package application.controler;

import application.exception.AlreadyDisconnectException;
import application.model.UtilisateurSession;
import application.tool.Utils;
import application.view.FxmlView;
import application.view.StageManager;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.text.Text;
import org.springframework.stereotype.Controller;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

@Controller
public class ControlerAddProduitMenu implements Initializable {

    private UtilisateurSession us = UtilisateurSession.getInstance();

    private StageManager sm = StageManager.getInstance();

    @FXML
    private Button home;

    @FXML
    private Text fullNameText;

    @FXML
    private Button adminPanelButton;

    @FXML
    private Button disconnectButton;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        if (Utils.isConnected()){
            Utils.updateButton(adminPanelButton);
            this.fullNameText.setText(us.getUtilisateur().getNom_utilisateur() + " " + us.getUtilisateur().getPrenom_utilisateur());
        }
    }

    @FXML
    public void displayMainMenu(ActionEvent event) throws IOException {
        this.sm.switchScene(event, FxmlView.MAIN_MENU);
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

