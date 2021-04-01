package application.controler;

import application.entity.Produit;
import application.exception.AlreadyDisconnectException;
import application.model.UtilisateurSession;
import application.service.ProduitService;
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
import javafx.stage.Stage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

@Controller
public class ControlerAddProduitMenu implements Initializable {

    private UtilisateurSession us = UtilisateurSession.getInstance();

    private StageManager sm = StageManager.getInstance();

    @Autowired
    private ProduitService ps;

    @FXML
    private Button validateButton;

    @FXML
    private Button cancelButton;
    @FXML
    private Button home;

    @FXML
    private Text fullNameText;

    @FXML
    private Button adminPanelButton;

    @FXML
    private Button disconnectButton;

    @FXML
    private TextField productNameTextField;

    @FXML
    private TextField priceTextField;

    @FXML
    private Text errorText;

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
    void submit(ActionEvent event) {
        if (this.priceTextField.getText().equals("") || this.productNameTextField.getText().equals("")){
            Utils.displayErrorMessage(this.errorText, ErrorMessages.MISS_FIELD_ERROR);
        }
        else{
            float fprice;
            String price = this.priceTextField.getText();

            try{
                fprice = Float.parseFloat(price);
                String productName = this.productNameTextField.getText();
                Produit p = new Produit(productName, Float.parseFloat(price));
                ps.addOrUpdateProduct(p);
                try{
                    Utils.displayInfoWindows("Produit crée avec succès");
                    sm.switchScene(event, FxmlView.MAIN_MENU);

                }catch(IOException e){
                    e.getMessage();
                }
            }catch(NumberFormatException e){
                Utils.displayErrorMessage(this.errorText, ErrorMessages.INCORRECT_FLOAT_TYPE);
            }
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
}

